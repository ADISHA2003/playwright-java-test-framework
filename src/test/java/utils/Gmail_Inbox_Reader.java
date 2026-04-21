package utils;

import Constants.FilePath;
import Utilities.PropertyFileReaderService;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;

public class Gmail_Inbox_Reader {

    private static final String IMAP_SERVER = "imap.gmail.com";

    /**
     * Waits for the latest email matching a keyword that arrived after a specific time,
     * then extracts its S3 URL and Subject. This ensures we get the email for the current test run.
     *
     * @param expectedKeyword The keyword to find in the email subject.
     * @param afterTimestamp The timestamp (epoch millis) after which the email should have been sent.
     */
    public static Map<String, String> waitForExportEmailAndExtractS3Url(String expectedKeyword, int maxWaitSeconds, int pollIntervalSeconds, long afterTimestamp) {
        String emailAccount = PropertyFileReaderService.readProperty(FilePath.EXECUTION_CONFIG, "gmail.username");
        String appPassword = PropertyFileReaderService.readProperty(FilePath.EXECUTION_CONFIG, "gmail.app.password");

        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");

        long maxWaitMillis = TimeUnit.SECONDS.toMillis(maxWaitSeconds);
        long startTime = System.currentTimeMillis();

        while ((System.currentTimeMillis() - startTime) < maxWaitMillis) {
            try {
                Session session = Session.getDefaultInstance(props, null);
                Store store = session.getStore("imaps");
                store.connect(IMAP_SERVER, emailAccount, appPassword);

                Folder inbox = store.getFolder("INBOX");
                inbox.open(Folder.READ_ONLY);

                int messageCount = inbox.getMessageCount(); 
                // Search the last 10 messages to ensure we find the email even if other emails arrived after.
                int searchDepth = Math.min(messageCount, 10);

                // Loop backwards from the most recent message to find the latest matching one first.
                for (int i = messageCount; i > messageCount - searchDepth; i--) {
                    if (i <= 0) continue;
                    Message message = inbox.getMessage(i);
                    java.util.Date sentDate = message.getSentDate();
                    String subject = message.getSubject();

                    // Check if subject matches AND the email was sent after the action was triggered.
                    if (subject != null && subject.contains(expectedKeyword) && sentDate != null && sentDate.getTime() >= afterTimestamp) {
                        String body = getTextFromMessage(message);
                        String s3Url = extractS3Url(body);

                        inbox.close(false);
                        store.close();

                        Map<String, String> result = new HashMap<>();
                        result.put("body", body);
                        result.put("subject", subject);
                        result.put("s3Url", s3Url); // s3Url can be null if not found in body

                        return result; // Found it, return immediately
                    }
                }

                inbox.close(false);
                store.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(pollIntervalSeconds * 1000); // wait before checking again
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                return null; // Exit if interrupted
            }
        }

        return null; // Return null if email is not found within the time limit
    }

    /** Extract plain text from email */
    private static String getTextFromMessage(Message message) throws MessagingException, IOException {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            return getTextFromMimeMultipart(mimeMultipart);
        }
        return "";
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();

        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);

            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent());
            } else if (bodyPart.isMimeType("text/html")) {
                result.append(bodyPart.getContent());
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }
        return result.toString();
    }

    /** Extract S3 URL from email body */
    private static String extractS3Url(String text) {
        if (text == null) return null;

        // This regex handles both direct .xlsx links and pre-signed URLs with query parameters.
        // It looks for the base S3 URL and then matches any following characters
        // that are not whitespace, quotes, or angle brackets, which typically delimit a URL in an email.
        Pattern pattern = Pattern.compile("https://s3\\.ap-southeast-1\\.amazonaws\\.com/files\\.vccedge\\.com/[^\\s\"<>]+");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}

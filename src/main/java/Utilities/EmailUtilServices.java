package Utilities;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;

public class EmailUtilServices {


    public static void main(String arg[]){
        sendEmail("ayush.gaur@hindustantime.com", "Test Email", "This is a test email", "D:\\Repository\\VCC_Edge_Automation\\target\\extent_report\\extentReport.html");
    }
        public static void sendEmail(String to, String subject, String body, String attachmentPath) {
            final String username = "ayush.gaur@hindustantimes.com";
            final String password = "*********";

            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp-mail.outlook.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true"); //TLS

            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("from-email@gmail.com"));
                message.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(to)
                );
                message.setSubject(subject);

                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(body, "text/html");

                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                attachmentBodyPart.attachFile(attachmentPath);

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);
                multipart.addBodyPart(attachmentBodyPart);

                message.setContent(multipart);

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException | IOException e) {
                e.printStackTrace();
            }
        }

}

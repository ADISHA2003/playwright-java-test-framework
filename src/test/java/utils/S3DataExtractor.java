package utils;

import java.io.*;
import java.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import PlaywrightPageObject.Pojo.CompanyScreener.Companies;
import PlaywrightPageObject.Pojo.CompanyScreener.LinkedDeals;
import PlaywrightPageObject.Pojo.CompanyScreener.LinkedInvestors;
import PlaywrightPageObject.Pojo.CompanyScreener.LinkedProfessionals;

public class S3DataExtractor {

    // Static flag to control debug output
    private static boolean DEBUG_ENABLED = true;

    // Guard to ensure debug info prints only once per JVM lifecycle (prevents duplicate printing)
    private static volatile boolean DEBUG_PRINTED = false;

    public static void setDebugEnabled(boolean enabled) {
        DEBUG_ENABLED = enabled;
    }

    // Backwards-compatible method
    public List<Companies> getCompaniesFromS3Url(String s3Url, int limit) {
        DataExport export = getDataExportFromS3Url(s3Url, limit);
        if (export == null) return null;
        return export.getCompanies();
    }

    // New: returns DataExport with all sheets parsed (companies + linked tabs)
    public DataExport getDataExportFromS3Url(String s3Url, int limit) {
        try {
            String pythonCommand = System.getProperty("os.name").toLowerCase().contains("win") ? "python" : "python3";
            List<String> command = Arrays.asList(
                pythonCommand,
                System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"utils"+File.separator+"s3url_company_tabs_data_extractor.py",
                s3Url,
                "--limit",
                String.valueOf(limit)
            );

            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true); // merge stderr → stdout
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            
            String jsonOutput = output.toString().trim();
            if (jsonOutput.isEmpty()) {
                System.err.println("Python returned no output.");
                return null;
            }
            
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Python script exited with code: " + exitCode);
                System.err.println("Python output:\n" + jsonOutput);
            }
            
            // Sanitize invalid numbers like NaN or Infinity before deserialization
            jsonOutput = jsonOutput.replaceAll(": NaN", ": null")
                                   .replaceAll(": Infinity", ": null")
                                   .replaceAll(": -Infinity", ": null");

            ObjectMapper mapper = JsonMapper.builder()
                    .enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS)
                    .build();

            JsonNode root = mapper.readTree(jsonOutput);
            DataExport export = new DataExport();

            // Collect raw sheet names and keep original nodes for printing
            Iterator<String> fnIter = root.fieldNames();
            List<String> sheetNames = new ArrayList<>();
            fnIter.forEachRemaining(sheetNames::add);
            System.out.println("S3 sheets found: " + sheetNames);

            // keep nodes map to print original S3 columns only
            Map<String, JsonNode> sheetNodes = new LinkedHashMap<>();

            // Iterate using collected names to avoid exhausting iterator twice
            for (String rawName : sheetNames) {
                String key = rawName == null ? "" : rawName.trim().toLowerCase();
                JsonNode node = root.get(rawName);
                if (node == null || !node.isArray()) continue;

                // store node for debug printing later
                sheetNodes.put(rawName, node);

                if (key.contains("companies")) {
                    List<Companies> companies = mapper.convertValue(node, new TypeReference<List<Companies>>() {});
                    export.setCompanies(companies);
                } else if (key.contains("investor")) { // covers "linked investors"
                    List<LinkedInvestors> investors = mapper.convertValue(node, new TypeReference<List<LinkedInvestors>>() {});
                    export.setLinkedInvestors(investors);
                } else if (key.contains("deal")) { // covers "linked deals"
                    List<LinkedDeals> deals = mapper.convertValue(node, new TypeReference<List<LinkedDeals>>() {});
                    export.setLinkedDeals(deals);
                } else if (key.contains("professional")) { // covers "linked professionals"
                    List<LinkedProfessionals> pros = mapper.convertValue(node, new TypeReference<List<LinkedProfessionals>>() {});
                    export.setLinkedProfessionals(pros);
                } else {
                    System.out.println("Ignoring unknown sheet: " + rawName);
                }
            }

            // Debug logging: print counts and some samples (pretty JSON) for easier inspection
            if (DEBUG_ENABLED && !DEBUG_PRINTED) {
                printDebugInfo(export, mapper, sheetNodes, limit);
                DEBUG_PRINTED = true;
            }

            return export;

        } catch (JsonProcessingException je) {
            System.err.println("JSON parsing error while reading S3 export: " + je.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Error while extracting data from S3: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // now accept sheetNodes and print original JSON rows (only S3 columns)
    private void printDebugInfo(DataExport export, ObjectMapper mapper, Map<String, JsonNode> sheetNodes, int limit) throws JsonProcessingException {
        System.out.println("Successfully parsed "
                + "Companies: " + export.getCompanies().size()
                + ", Linked Investors: " + export.getLinkedInvestors().size()
                + ", Linked Deals: " + export.getLinkedDeals().size()
                + ", Linked Professionals: " + export.getLinkedProfessionals().size());

        int sampleCount = (limit > 0) ? limit : 2;
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

        // Print original JSON nodes so only S3 sheet columns are printed
        JsonNode companiesNode = sheetNodes.getOrDefault("Companies", sheetNodes.get("companies"));
        if (companiesNode != null && companiesNode.isArray() && companiesNode.size() > 0) {
            System.out.println("S3 sample companies (up to " + sampleCount + "):");
            for (int i = 0; i < Math.min(sampleCount, companiesNode.size()); i++) {
                System.out.println(writer.writeValueAsString(companiesNode.get(i)));
            }
        }

        JsonNode invNode = sheetNodes.getOrDefault("Linked Investors", sheetNodes.get("linked investors"));
        if (invNode == null) invNode = sheetNodes.getOrDefault("LinkedInvestors", sheetNodes.get("linkedinvestors"));
        if (invNode != null && invNode.isArray() && invNode.size() > 0) {
            System.out.println("\n[S3 EXTRACTION] LinkedInvestors - Sample " + sampleCount + " rows:");
            for (int i = 0; i < Math.min(sampleCount, invNode.size()); i++) {
                System.out.println(writer.writeValueAsString(invNode.get(i)));
            }
        }

        JsonNode dealsNode = sheetNodes.getOrDefault("Linked Deals", sheetNodes.get("linked deals"));
        if (dealsNode == null) dealsNode = sheetNodes.getOrDefault("LinkedDeals", sheetNodes.get("linkeddeals"));
        if (dealsNode != null && dealsNode.isArray() && dealsNode.size() > 0) {
            System.out.println("\n[S3 EXTRACTION] LinkedDeals - Sample " + sampleCount + " rows:");
            for (int i = 0; i < Math.min(sampleCount, dealsNode.size()); i++) {
                System.out.println(writer.writeValueAsString(dealsNode.get(i)));
            }
        }

        JsonNode prosNode = sheetNodes.getOrDefault("Linked Professionals", sheetNodes.get("linked professionals"));
        if (prosNode == null) prosNode = sheetNodes.getOrDefault("LinkedProfessionals", sheetNodes.get("linkedprofessionals"));
        if (prosNode != null && prosNode.isArray() && prosNode.size() > 0) {
            System.out.println("\n[S3 EXTRACTION] LinkedProfessionals - Sample " + sampleCount + " rows:");
            for (int i = 0; i < Math.min(sampleCount, prosNode.size()); i++) {
                System.out.println(writer.writeValueAsString(prosNode.get(i)));
            }
        }
    }

    public int getBulkExportCount(String s3Url) {
        try {
            String pythonCommand = System.getProperty("os.name").toLowerCase().contains("win") ? "python" : "python3";
            List<String> command = Arrays.asList(
                    pythonCommand,
                    System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "utils" + File.separator + "s3url_company_tabs_data_extractor.py",
                    s3Url,
                    "--limit", "0"
            );

            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            process.waitFor();

            String jsonOutput = output.toString().trim();
            if (jsonOutput.isEmpty()) return -1;

            ObjectMapper mapper = JsonMapper.builder()
                    .enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS)
                    .build();

            JsonNode root = mapper.readTree(jsonOutput);

            if (root.has("CompanyBulkExport")) {
                return root.get("CompanyBulkExport").size();
            }
            return 0;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}

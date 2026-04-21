package utils;

import PlaywrightPageObject.Pojo.CompanyScreener.Companies;
import PlaywrightPageObject.Pojo.CompanyScreener.LinkedDeals;
import PlaywrightPageObject.Pojo.CompanyScreener.LinkedInvestors;
import PlaywrightPageObject.Pojo.CompanyScreener.LinkedProfessionals;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Utility class to compare UI (DataTable) vs S3 (Excel) extracted POJOs.
 */
public class DataComparator {

    /**
     * Compares two lists of Companies objects field by field.
     *
     * @param uiCompanies  list of Companies extracted from UI
     * @param s3Companies  list of Companies extracted from S3 export
     */
    public static List<String> compareCompanies(List<Companies> uiCompanies, List<Companies> s3Companies) {
        List<String> allMismatches = new ArrayList<>();
        if (uiCompanies == null || s3Companies == null) {
            allMismatches.add("One or both company lists are null!");
            return allMismatches;
        }

        if (uiCompanies.isEmpty() || s3Companies.isEmpty()) {
            allMismatches.add("One or both company lists are empty!");
            return allMismatches;
        }

        // Sort both lists by Company Name for consistent comparison
        uiCompanies.sort(Comparator.comparing(Companies::getCompanyName, Comparator.nullsLast(String::compareToIgnoreCase)));
        s3Companies.sort(Comparator.comparing(Companies::getCompanyName, Comparator.nullsLast(String::compareToIgnoreCase)));

        int totalCompared = 0;

        // Fields to ignore during comparison
        Set<String> ignoredFields = Set.of();

        // Compare based on company name match
        for (Companies uiCompany : uiCompanies) {
            Optional<Companies> matchOpt = s3Companies.stream()
                    .filter(c -> Objects.equals(
                            normalize(c.getCompanyName()), 
                            normalize(uiCompany.getCompanyName())))
                    .findFirst();

            if (matchOpt.isEmpty()) {
                String error = "Missing company in S3 export: " + uiCompany.getCompanyName();
                System.err.println(error);
                allMismatches.add(error);
                continue;
            }

            Companies s3Company = matchOpt.get();
            totalCompared++;

            // Compare all fields via reflection
            for (Field field : Companies.class.getDeclaredFields()) {
                field.setAccessible(true);

                // Skip ignored fields
                if (ignoredFields.contains(field.getName())) {
                    continue;
                }

                try {
                    Object uiValue = field.get(uiCompany);
                    Object s3Value = field.get(s3Company);

                    if (!areEqual(uiValue, s3Value)) {
                        String error = "Mismatch for [" + uiCompany.getCompanyName() + "] → Field: "
                            + field.getName() + 
                            " | UI: " + uiValue + 
                            " | S3: " + s3Value;
                        System.err.println(error);
                        allMismatches.add(error);
                    } else {
                        System.out.println("Match for [" + uiCompany.getCompanyName() + "] → Field: "
                            + field.getName() +
                            " | UI: " + uiValue + " | S3: " + s3Value);
                    }

                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Reflection error comparing field: " + field.getName(), e);
                }
            }
        }
        if (allMismatches.isEmpty() && totalCompared > 0) {
            System.out.println("✅ Data comparison passed! All " + totalCompared + " companies match perfectly.");
        }
        return allMismatches;
    }

    /**
     * Compares two lists of LinkedInvestors objects.
     *
     * @param uiInvestors  list of investors from UI
     * @param s3Investors  list of investors from S3
     */
    public static List<String> compareLinkedInvestors(List<LinkedInvestors> uiInvestors, List<LinkedInvestors> s3Investors) {
        if (uiInvestors == null || s3Investors == null) {
            System.err.println("Warning: One or both LinkedInvestors lists are null. Skipping comparison.");
            return Collections.singletonList("One or both LinkedInvestors lists are null.");
        }
        // Sort by Investor Name
        uiInvestors.sort(Comparator.comparing(LinkedInvestors::getInvestorName, Comparator.nullsLast(String::compareToIgnoreCase)));
        s3Investors.sort(Comparator.comparing(LinkedInvestors::getInvestorName, Comparator.nullsLast(String::compareToIgnoreCase)));

        return compareLists(uiInvestors, s3Investors, LinkedInvestors.class, "Investor Name", "getInvestorName");
    }

    /**
     * Compares two lists of LinkedDeals objects.
     *
     * @param uiDeals  list of deals from UI
     * @param s3Deals  list of deals from S3
     */
    public static List<String> compareLinkedDeals(List<LinkedDeals> uiDeals, List<LinkedDeals> s3Deals) {
        if (uiDeals == null || s3Deals == null) {
            System.err.println("Warning: One or both LinkedDeals lists are null. Skipping comparison.");
            return Collections.singletonList("One or both LinkedDeals lists are null.");
        }
        // Sort by a composite key: Deal Date + Deal Type
        Comparator<LinkedDeals> dealComparator = Comparator
                .comparing(LinkedDeals::getDealDate, Comparator.nullsLast(String::compareToIgnoreCase))
                .thenComparing(LinkedDeals::getDealType, Comparator.nullsLast(String::compareToIgnoreCase));
        uiDeals.sort(dealComparator);
        s3Deals.sort(dealComparator);

        return compareLists(uiDeals, s3Deals, LinkedDeals.class, "Deal Date + Deal Type", "getDealDate");
    }

    /**
     * Compares two lists of LinkedProfessionals objects.
     *
     * @param uiProfessionals  list of professionals from UI
     * @param s3Professionals  list of professionals from S3
     */
    public static List<String> compareLinkedProfessionals(List<LinkedProfessionals> uiProfessionals, List<LinkedProfessionals> s3Professionals) {
        if (uiProfessionals == null || s3Professionals == null) {
            System.err.println("Warning: One or both LinkedProfessionals lists are null. Skipping comparison.");
            return Collections.singletonList("One or both LinkedProfessionals lists are null.");
        }
        // Sort by Professionals Name
        uiProfessionals.sort(Comparator.comparing(LinkedProfessionals::getProfessionalsName, Comparator.nullsLast(String::compareToIgnoreCase)));
        s3Professionals.sort(Comparator.comparing(LinkedProfessionals::getProfessionalsName, Comparator.nullsLast(String::compareToIgnoreCase)));

        return compareLists(uiProfessionals, s3Professionals, LinkedProfessionals.class, "Professionals Name", "getProfessionalsName");
    }

    /**
     * Generic method to compare two lists of objects of the same type.
     */
    private static <T> List<String> compareLists(List<T> uiList, List<T> s3List, Class<T> clazz, String idFieldName, String idGetterName) {
        List<String> allMismatches = new ArrayList<>();
        int totalCompared = 0;
        String className = clazz.getSimpleName();

        if (uiList.isEmpty() && s3List.isEmpty()) {
            System.out.println("✅ Data comparison passed for " + className + "! Both lists are empty.");
            return allMismatches;
        }

        for (T uiItem : uiList) {
            Object uiKey;
            try {
                uiKey = clazz.getMethod(idGetterName).invoke(uiItem);
            } catch (Exception e) {
                throw new RuntimeException("Could not invoke getter " + idGetterName + " on " + className, e);
            }

            Optional<T> matchOpt = s3List.stream()
                    .filter(s3Item -> {
                        try {
                            Object s3Key = clazz.getMethod(idGetterName).invoke(s3Item);
                            // Use areEqual to handle normalization and minor differences if keys are strings
                            return areEqual(uiKey, s3Key);
                        } catch (Exception e) {
                            return false;
                        }
                    })
                    .findFirst();

            if (matchOpt.isEmpty()) {
                String error = "Missing " + className + " in S3 export, identified by " + idFieldName + ": " + uiKey;
                System.err.println(error);
                allMismatches.add(error);
                continue;
            }

            T s3Item = matchOpt.get();
            totalCompared++;

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                // Add any fields to ignore for this specific type here if needed
                // if (field.getName().equals("someFieldToIgnore")) continue;

                try {
                    Object uiValue = field.get(uiItem);
                    Object s3Value = field.get(s3Item);

                    if (!areEqual(uiValue, s3Value)) {
                        String error = "Mismatch for " + className + " [" + uiKey + "] → Field: "
                            + field.getName() +
                            " | UI: " + uiValue +
                            " | S3: " + s3Value;
                        System.err.println(error);
                        allMismatches.add(error);
                    } else {
                        System.out.println("Match for " + className + " [" + uiKey + "] → Field: "
                            + field.getName() +
                            " | UI: " + uiValue + " | S3: " + s3Value);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Reflection error on " + className + " for field: " + field.getName(), e);
                }
            }
        }
        if (allMismatches.isEmpty() && totalCompared > 0) {
            System.out.println("✅ Data comparison passed! All " + totalCompared + " " + className + " items match.");
        } else if (uiList.size() > 0) {
            System.err.println("Warning: No matching " + className + " items were found to compare.");
        } else {
             System.out.println("✅ Data comparison passed for " + className + "! UI list was empty.");
        }
        return allMismatches;
    }

    // ---------- Helper Methods ----------

    private static boolean areEqual(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a instanceof Number && b instanceof Number) {
            double diff = Math.abs(((Number) a).doubleValue() - ((Number) b).doubleValue());
            return diff < 0.02; // allow small floating diff for rounding
        }
        String aStr = normalize(a.toString());
        String bStr = normalize(b.toString());

        // If one value is truncated with "..." but starts with same prefix
        if (aStr.endsWith("...") && bStr.toLowerCase().startsWith(aStr.replace("...", "").toLowerCase())) {
            return true;
        }
        if (bStr.endsWith("...") && aStr.toLowerCase().startsWith(bStr.replace("...", "").toLowerCase())) {
            return true;
        }

        // Handle Aggregated Values (Value+N) e.g. "Zomato+1"
        if (aStr.matches(".*\\+\\d+$")) {
            int lastPlus = aStr.lastIndexOf('+');
            if (lastPlus > 0) {
                String base = aStr.substring(0, lastPlus).trim();
                if (!base.isEmpty() && bStr.toLowerCase().contains(base.toLowerCase())) {
                    System.out.println("   -> [Aggregation Skipped] UI: '" + aStr + "' contains base '" + base + "' found in S3: '" + bStr + "'");
                    return true;
                }
            }
        }

        return aStr.equalsIgnoreCase(bStr);
    }

    private static String normalize(String s) {
        return s == null ? "" : s.trim().replaceAll("\\s+", " ");
    }
}

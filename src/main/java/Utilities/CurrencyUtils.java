package Utilities;

public final class CurrencyUtils {

    // Prevent instantiation
    private CurrencyUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Resolves currency symbol from UI-visible currency text.
     *
     * Examples:
     *  "₹ INR Crore"   -> "₹"
     *  "$ USD Million" -> "$"
     *  "£ GBP Million" -> "£"
     *  "€ EUR Million" -> "€"
     *
     * @param currencyText UI-visible currency text
     * @return currency symbol
     */
    public static String resolveCurrencySymbol(String currencyText) {

        if (currencyText == null || currencyText.isBlank()) {
            throw new IllegalArgumentException("Currency text cannot be null or empty");
        }

        String trimmed = currencyText.trim();
        String firstChar = trimmed.substring(0, 1);

        switch (firstChar) {
            case "₹":
                return "₹";
            case "$":
                return "$";
            case "£":
                return "£";
            case "€":
                return "€";
            default:
                throw new IllegalArgumentException(
                        "Unsupported currency format: " + currencyText
                );
        }
    }
}

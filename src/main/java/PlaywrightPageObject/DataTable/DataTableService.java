package PlaywrightPageObject.DataTable;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

public class DataTableService {
    Page page;
    public DataTableService(Page page) {
        this.page = page;
    }



    public List<List<String>> parseTable(String tableHtml) {

        // 1. Create a Document from the HTML snippet
        // Note: The Jsoup.parseBodyFragment is ideal for HTML fragments like a table
        Document doc = Jsoup.parseBodyFragment(tableHtml);

        // 2. Select the <tbody> element (or the main <table> if the fragment only contains the table)
        Element table = doc.selectFirst("table"); // Select the root <table> element in the fragment
        if (table == null) {
            System.err.println("Error: Could not find table element in HTML fragment.");
            return new ArrayList<>();
        }

        List<List<String>> tableData = new ArrayList<>();

        // 3. Iterate through all <tr> (row) elements
        Elements rows = table.select("tr");

        for (Element row : rows) {
            List<String> rowData = new ArrayList<>();

            // 4. Select all <th> (header) and <td> (data) cells in the row
            Elements cells = row.select("th, td");

            // 5. Extract the text from each cell
            for (Element cell : cells) {
                rowData.add(cell.text().trim());
            }

            tableData.add(rowData);
        }

        return tableData;
    }

    public List<String> getVisibleColumnsName(boolean normalize,
                                              boolean removeCurrency,
                                              boolean removeYearNumbers) {

        Locator headerCells = page.locator("table thead tr th");

        // fallback if table headers are not present
        if (headerCells.count() == 0) {
            headerCells = page.locator("div.flex-align-right.flex .flex button.button");
        }

        List<String> headers = headerCells.allTextContents().stream()
                .filter(name -> !name.equalsIgnoreCase("Deal Details"))
                .collect(Collectors.toList());

        if (normalize) {
            return headers.stream()
                    .map(h -> normalizeText(h, removeCurrency, removeYearNumbers))
                    .collect(Collectors.toList());
        }

        return headers; // raw text
    }


    /**
     * Normalize header/filter text for reliable comparison
     * - strips invisible characters
     * - removes currency symbols
     * - removes sort icons/arrows
     * - normalizes parentheses spacing
     * - collapses whitespace
     * - lowercases for case-insensitive match
     */
    public String normalizeText(String raw) {
        if (raw == null) return "";

        String cleaned = raw;

        // remove common invisible characters
        cleaned = cleaned.replace("\uFEFF", "")   // BOM
                .replace("\u200B", "")   // zero-width space
                .replace("\u00A0", " "); // non-breaking space -> normal space

        // remove currency symbols (add more symbols if needed)
        cleaned = cleaned.replaceAll("[₹$€£¥₩₽₺₴*]", "");

        // remove arrow / caret / sort icons
        cleaned = cleaned.replaceAll("[\\u25B2\\u25BC\\u25B3\\u25BD▴▾▲▼△▽]", "");

        // 🚀 remove any text inside parentheses including the parentheses
        // e.g., "(2021 to 2024)" → ""
        cleaned = cleaned.replaceAll("\\(.*?\\)", "");

        // collapse multiple spaces to single space and trim
        cleaned = cleaned.replaceAll("\\s+", " ").trim();

        // lowercase for case-insensitive comparison
        cleaned = cleaned.toLowerCase(Locale.ROOT);

        return cleaned;
    }

    /**
     * Normalize header/filter text for reliable comparison.
     *
     * @param raw                original UI text
     * @param removeCurrency     whether currency symbols should be removed
     * @param removeYearNumbers  whether years / year ranges should be removed
     *
     * Behavior:
     * - strips invisible characters
     * - optionally removes currency symbols
     * - removes sort icons/arrows
     * - optionally removes years / year ranges (2021–2025, FY 2024, etc.)
     * - removes text inside parentheses
     * - collapses whitespace
     * - lowercases for case-insensitive match
     */
    public String normalizeText(String raw,
                                 boolean removeCurrency,
                                 boolean removeYearNumbers) {

        if (raw == null) return "";

        String cleaned = raw;

        // remove invisible characters
        cleaned = cleaned.replace("\uFEFF", "")   // BOM
                .replace("\u200B", "")            // zero-width space
                .replace("\u00A0", " ");          // non-breaking space → normal space

        // optionally remove currency symbols
        if (removeCurrency) {
            cleaned = cleaned.replaceAll("[₹$€£¥₩₽₺₴*]", "");
        }

        // remove arrow / caret / sort icons
        cleaned = cleaned.replaceAll("[\\u25B2\\u25BC\\u25B3\\u25BD▴▾▲▼△▽]", "");

        // remove text inside parentheses
        cleaned = cleaned.replaceAll("\\(.*?\\)", "");

        // optionally remove years / year ranges
        if (removeYearNumbers) {
            // removes:
            // 2021
            // 2021-2025
            // 2021 to 2025
            // FY 2024
            // FY2024
            cleaned = cleaned.replaceAll(
                    "(?i)\\bFY\\s*\\d{4}\\b", ""
            ).replaceAll(
                    "\\b\\d{4}\\s*(to|-|–|—)\\s*\\d{4}\\b", ""
            ).replaceAll(
                    "\\b\\d{4}\\b", ""
            );
        }

        // collapse whitespace and trim
        cleaned = cleaned.replaceAll("\\s+", " ").trim();

        // lowercase for case-insensitive comparison
        cleaned = cleaned.toLowerCase(Locale.ROOT);

        return cleaned;
    }

}

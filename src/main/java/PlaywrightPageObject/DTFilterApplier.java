package PlaywrightPageObject;

import Constants.ColumnType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public class DTFilterApplier {

    private final Page page;

    public DTFilterApplier(Page page) {
        this.page = page;
    }

    /**
     * Apply filter from the Filters popup.
     *
     * @param columnName visible column name (e.g. "Company Type", "Edge Score")
     * @param type       STRING / NUMBER / DATE
     * @param operator   e.g. "in", "not in", "between", "greater than", "on or after" ...    1 or 2 values depending on operator
     */
    public List<String>  applyFilter(String columnName,
                            ColumnType type,
                            String operator
                            ) {
        String op = normalizeOp(operator);
        Locator popup = openFilterPopup();

        selectColumn(popup, columnName);

        selectOperator(popup, type, op);

        List<String> filterValues = fillValues(popup, type);

        clickApply(popup);

        return filterValues;
    }

    public List<String>  applyFilter(String columnName,
                                     ColumnType type,
                                     String operator,
                                     String... values) {

        String op = normalizeOp(operator);
        validateValues(type, op, values);

        Locator popup = openFilterPopup();

        selectColumn(popup, columnName);

        selectOperator(popup, type, op);

        List<String> filterValues = fillValues(popup, type, op, values);

        clickApply(popup);

        return filterValues;
    }

    private Locator openFilterPopup() {
        // Top toolbar "Filter" button
        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(Pattern.compile("^Filter(\\s*\\d*)?$"))
        ).click();

        // Filter panel – the white box that shows "Filters (0)"
        Locator popup = page.locator(".MuiPaper-root.MuiPaper-elevation");
        popup.waitFor();
        return popup;
    }

  /* ================================
     1) Open popup
     ================================ */

    private void selectColumn(Locator popup, String columnName) {
        // The first dropdown in WHERE row (shows "Select Column")
        Locator columnDropdown = popup.locator(".filter-criteria .shrink-0 select").first();
        columnDropdown.click();


        // Dropdown list with column names (Company Name, City, Edge Score, ...)
        // Adjust this locator if needed
        columnDropdown.selectOption(new SelectOption().setLabel(columnName));
    }

  /* ================================
     2) Select column
     ================================ */

    private void selectOperator(Locator popup, ColumnType type, String op) {
        // Second dropdown in WHERE row (In / Not In / Between / etc.)
        Locator operatorDropdown = popup.locator(".filter-criteria .shrink-0 select").nth(1);
        operatorDropdown.click();
        // If above feels flaky you can also just take the second dropdown in the WHERE row

        String visibleText = mapOperatorToUiLabel(type, op);
        operatorDropdown.selectOption(visibleText);
//        columnDropdown.selectOption(new SelectOption().setLabel(visibleText));
    }

  /* ================================
     3) Select operator
     ================================ */

    private String mapOperatorToUiLabel(ColumnType type, String op) {
        // Map normalized operator -> EXACT text/symbol you see in dropdown
        switch (type) {
            case STRING:
                switch (op) {
                    case "IN":
                        return "In";
                    case "NOT_IN":
                        return "Not In";
                    default:
                        throw new IllegalArgumentException("Unsupported string op: " + op);
                }

            case NUMBER:
                switch (op) {
                    case "BETWEEN":
                        return "Between";
                    case "NOT_BETWEEN":
                        return "Not Between";
                    case "GT":
                        return ">";
                    case "GTE":
                        return ">=";
                    case "LT":
                        return "<";
                    case "LTE":
                        return "<=";
                    case "EQUALS":
                        return "=";
                    case "NOT_EQUALS":
                        return "!=";
                    default:
                        throw new IllegalArgumentException("Unsupported number op: " + op);
                }

            case DATE:
                switch (op) {
                    case "BETWEEN":
                        return "IS_BETWEEN";
                    case "NOT_BETWEEN":
                        return "IS_NOT_BETWEEN";

                    case "ON_OR_AFTER":
                        return ">=";
                    case "AFTER":
                        return ">";
                    case "ON_OR_BEFORE":
                        return "<=";
                    case "BEFORE":
                        return "<";

                    case "EQUALS":
                        return "=";
                    case "NOT_EQUALS":
                        return "!=";

                    default:
                        throw new IllegalArgumentException("Unsupported date operator: " + op);
                }
        }
        throw new IllegalArgumentException("Unsupported column type");
    }

    private List<String> fillValues(Locator popup,
                            ColumnType type
    ) {

        switch (type) {
            case STRING:
                return fillStringValuesRandom(popup, 1);
//            case NUMBER:
//                fillNumberValues(popup, op, values);
//                break;
//            case DATE:
//                fillDateValues(popup, op, values);
//                break;
        }
        return null;
    }

    private List<String> fillValues(Locator popup,
                                    ColumnType type,
                                    String op,
                                    String... values) {

        switch (type) {
            case STRING:
                fillStringValues(popup, op, values);
                break;
            case NUMBER:
                fillNumberValues(popup, op, values);
                break;
            case DATE:
                fillDateValues(popup, op, values);
                break;
        }
        return null;
    }

  /* ================================
     4) Fill values for each type
     ================================ */

    /**
     * STRING: In / Not In with multi-select dropdown
     */
    private void fillStringValues(Locator popup, String op, String... values) {
        // The 3rd control in WHERE row: "Select <Column Name>"
        Locator valueDropdown = popup.locator("div:has-text(\"Select \")").last();
        valueDropdown.click();

        // Popup that has checkbox list + Search
        for (String v : values) {
            // Type in search box to narrow down
            Locator search = page.locator(".MuiPaper-root.MuiPaper-elevation .search-input-wrapper.sm.mb-8 input");
            search.fill(v);

            Locator optionRow = page.locator(".MuiPaper-root.MuiPaper-elevation label.custom-checkbox").filter(
                    new Locator.FilterOptions().setHasText(v)
            );

            // Tick the checkbox
            page.waitForTimeout(1000);
            optionRow.first().click();
        }

        // Click outside to close dropdown (small click on Filters title area)
        popup.getByText("Filters").click();
    }

    /**
     * Open the values dropdown and select `count` random options.
     * If there are fewer than `count` available, selects all available options.
     *
     * @param popup   the filter popup locator
     * @param count   how many random options to pick
     */
    private List<String>  fillStringValuesRandom(Locator popup, int count) {
        Locator valueDropdown = popup.locator("div:has-text(\"Select \")").last();
        valueDropdown.click();

        Locator wrapper = page.locator(".MuiPaper-root.MuiPaper-elevation .max-h-190");
        if (wrapper.count() == 0) {
            wrapper = page.locator(".MuiPaper-root.MuiPaper-elevation .dropdown-card .max-h-190");
        }

        Locator optionLabels = wrapper.locator("label.custom-checkbox");
        // try to load more by scrolling (same as you had)
        int maxScrollAttempts = 40;
        int attempts = 0;
        while (attempts < maxScrollAttempts) {
            // if we already have plenty of items, break early
            if (optionLabels.count() >= Math.max(1, count)) break;
            try {
                wrapper.evaluate("el => { el.scrollTop = el.scrollTop + el.offsetHeight; }");
            } catch (Exception e) {
                break;
            }
            page.waitForTimeout(200);
            attempts++;
        }

        // snapshot the visible option texts (stable list)
        List<String> texts = optionLabels.count() > 0
                ? optionLabels.allInnerTexts()
                : page.locator(".MuiPaper-root.MuiPaper-elevation label.custom-checkbox").allInnerTexts();

        // If still empty, fail
        if (texts == null || texts.isEmpty()) {
            throw new RuntimeException("No selectable options found in dropdown.");
        }

        System.out.println("Total options (snapshot) : " + texts.size());

        int pickCount = Math.min(count, texts.size());
        Random rnd = ThreadLocalRandom.current();

        // Choose unique random indices from the snapshot
        Set<Integer> chosenIndices = new LinkedHashSet<>();
        while (chosenIndices.size() < pickCount) {
            chosenIndices.add(rnd.nextInt(texts.size()));
        }

        System.out.println("Chosen indices: " + chosenIndices);

        // Convert snapshot to mutable list so we can remove items once selected
        List<String> mutableTexts = new ArrayList<>(texts);
        List<String> selectedTexts = new ArrayList<>();

        System.out.print("options are : ");
        for (Integer idx : chosenIndices) {
            // If we've removed earlier items, index could be off; safer: use the original snapshot value
            String optionText = texts.get(idx).trim();

            System.out.print(optionText + ", ");

            // Click by text — find the first visible label that contains the optionText
//            Locator toClick = wrapper.locator("label.custom-checkbox").filter(new Locator.FilterOptions().setHasText(optionText)).first();

            Locator toClick = findExactLabel(wrapper, optionText);

            // If not found inside wrapper (virtualization changed), fallback to global selector
            if (toClick == null || toClick.count() == 0) {
                System.out.println("To click count is 0");
                toClick = findExactLabel(page.locator(".MuiPaper-root.MuiPaper-elevation label.custom-checkbox"), optionText);

//                page.locator(".MuiPaper-root.MuiPaper-elevation label.custom-checkbox").filter(new Locator.FilterOptions().setHasText(optionText)).first();
            }

            if (toClick.count() == 0) {
                // As a last resort, try clicking the checkbox input by matching exact innerText via JS
                System.out.println("Warning: option not found in DOM by text: \"" + optionText + "\" — skipping");
                continue;
            }

            try {
                toClick.scrollIntoViewIfNeeded();
                selectedTexts.add(toClick.innerText());
                toClick.click();
            } catch (Exception clickEx) {
                // fallback to clicking inner input
                Locator input = toClick.locator("input[type='checkbox']");
                if (input.count() > 0 && input.first().isVisible()) {
                    input.first().click();
                } else {
                    System.out.println("Warning: failed to click option '" + optionText + "': " + clickEx.getMessage());
                }
            }

            // Remove the first occurrence of this text from mutableTexts so duplicates won't be selected again
            mutableTexts.remove(optionText);

            page.waitForTimeout(1500);
        }

        System.out.println();
        System.out.println("Selected Texts : " + selectedTexts);
        // close dropdown
        popup.getByText("Filters").click();

        return selectedTexts;
    }


    private Locator findExactLabel(Locator wrapper, String optionText) {

        int maxScrollAttempts = 40;

        // DIRECT SEARCH FIRST (no scroll)
        Locator found = searchExact(wrapper, optionText);
        if (found != null) return found;

        Locator scrollArea = wrapper; // the scroll container

        // 🔽 SCROLL DOWN & SEARCH
        for (int i = 0; i < maxScrollAttempts; i++) {
            try {
                scrollArea.evaluate("el => el.scrollTop = el.scrollTop + el.offsetHeight");
            } catch (Exception e) {
                break; // no more scrolling possible
            }
            page.waitForTimeout(200);

            found = searchExact(wrapper, optionText);
            if (found != null) return found;
        }

        // 🔼 SCROLL UP & SEARCH
        for (int i = 0; i < maxScrollAttempts; i++) {
            try {
                scrollArea.evaluate("el => el.scrollTop = el.scrollTop - el.offsetHeight");
            } catch (Exception e) {
                break;
            }
            page.waitForTimeout(200);

            found = searchExact(wrapper, optionText);
            if (found != null) return found;
        }

        return null;  // Not found anywhere
    }

    private Locator searchExact(Locator wrapper, String optionText) {
        Locator labels = wrapper.locator("label.custom-checkbox");
        int count = labels.count();

        for (int i = 0; i < count; i++) {
            Locator lbl = labels.nth(i);
            String text = lbl.innerText().trim();

            if (text.equals(optionText)) {
                return lbl;
            }
        }

        return null;
    }


    /**
     * NUMBER: between/not between OR single-value comparisons
     */
    private void fillNumberValues(Locator popup, String op, String... values) {
        if ("BETWEEN".equals(op) || "NOT_BETWEEN".equals(op)) {
            // Min / Max inputs
            Locator minInput = popup.getByPlaceholder("Min").first();
            Locator maxInput = popup.getByPlaceholder("Max").first();
            minInput.fill(values[0]);
            maxInput.fill(values[1]);
        } else {
            // single numeric value; in your UI it's usually the "Min" field or a field with only one input
            Locator singleInput = popup.locator("input[type='number']").first();
            if (singleInput.count() == 0) {
                singleInput = popup.getByPlaceholder("Enter Value");  // fallback
            }
            singleInput.fill(values[0]);
        }
    }

    /**
     * DATE: uses the date range input like "dd/MM/yyyy – dd/MM/yyyy"
     */

    private void fillDateValues(Locator popup, String op, String... values) {
        // Open the date picker by clicking the input once

        if ("BETWEEN".equals(op) || "NOT_BETWEEN".equals(op)) {
            Locator dateInput = popup.locator("input[placeholder*='DD/MM/YYYY - DD/MM/YYYY']").first();
            dateInput.click();
            // Range: select start date then end date
            selectDateFromCalendar(values[0], true);  // from
            selectDateFromCalendar(values[1], false);  // to
        } else {
            Locator dateInput = popup.locator("input[placeholder*='DD/MM/YYYY']").first();
            dateInput.click();
            // Single date: ON_OR_AFTER / BEFORE / EQUALS / etc.
            selectDateFromCalendar(values[0], null);
        }

        // Click OK on the calendar if present
        Locator okBtn = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("OK"));
        if (okBtn.count() > 0) {
            okBtn.click();
        }
    }


    private void selectDateFromCalendar(String dateStr, Boolean isStartRange) {
        // dateStr is dd/MM/yyyy
        DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate target = LocalDate.parse(dateStr, inputFmt);

        String yearStr      = String.valueOf(target.getYear());
        int targetYear      = target.getYear();
        String monthShort   = target.format(DateTimeFormatter.ofPattern("MMM"));       // "Nov"
        String dayAriaLabel = target.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));// "24 Nov 2025"

        // --- choose the correct calendar root ---
        Locator calendarRoot;
        Locator startCal = page.locator("div[data-testid='calendar-start']");
        Locator endCal   = page.locator("div[data-testid='calendar-end']");

        if (isStartRange != null && startCal.count() > 0 && endCal.count() > 0) {
            calendarRoot = isStartRange ? startCal.first() : endCal.first();
        } else {
            // single-date picker or fallback
            calendarRoot = page.locator("div[data-testid='calendar']").last();
        }

        calendarRoot.waitFor();

        // --- 1) Open month/year dropdown from this calendar’s header ---
        Locator headerBtn = calendarRoot.locator("button[aria-label='Select month']").first();
        Locator monthDropdown = calendarRoot.locator(".rs-calendar-month-dropdown.show");

        if (monthDropdown.count() == 0) {
            headerBtn.click();
            monthDropdown = calendarRoot.locator(".rs-calendar-month-dropdown.show");
            monthDropdown.waitFor();
        }

        // --- 2) Scroll virtualized year list until desired year appears ---
        Locator scrollWrapper = monthDropdown.locator(".rs-calendar-month-dropdown-row-wrapper");

        // active year for direction hint
        Locator activeYearEl = monthDropdown.locator(".rs-calendar-month-dropdown-year-active").first();
        int currentYear = Integer.parseInt(activeYearEl.innerText().trim());
        boolean scrollDown = targetYear > currentYear;

        Locator yearRow = monthDropdown.locator(".rs-calendar-month-dropdown-row")
                .filter(new Locator.FilterOptions().setHasText(yearStr));

        int maxScrollAttempts = 40;
        for (int i = 0; i < maxScrollAttempts && yearRow.count() == 0; i++) {
            if (scrollDown) {
                scrollWrapper.evaluate("el => el.scrollTop = el.scrollTop + 200");
            } else {
                scrollWrapper.evaluate("el => el.scrollTop = el.scrollTop - 200");
            }
            page.waitForTimeout(100);
            yearRow = monthDropdown.locator(".rs-calendar-month-dropdown-row")
                    .filter(new Locator.FilterOptions().setHasText(yearStr));
        }

        if (yearRow.count() == 0) {
            throw new RuntimeException("Could not find year row for year: " + yearStr);
        }

        yearRow = yearRow.first();
        yearRow.scrollIntoViewIfNeeded();

        // --- 3) Click month inside that year row ---
        Locator monthCell = yearRow.locator(".rs-calendar-month-dropdown-cell")
                .filter(new Locator.FilterOptions().setHasText(monthShort))
                .first();
        monthCell.click();   // dropdown closes, month grid for that month/year shows

        // --- 4) Click correct day in THIS calendar’s grid ---
        Locator dayCell = calendarRoot
                .locator(".rs-calendar-table-cell[aria-label='" + dayAriaLabel + "']")
                .first();
        dayCell.click();
    }

    private void clickApply(Locator popup) {
        popup.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Apply")).click();
    }

  /* ================================
     5) Click Apply
     ================================ */

    private String normalizeOp(String op) {
        return op.trim()
                .toUpperCase(Locale.ENGLISH)
                .replace(" ", "_");
    }

  /* ================================
     Helpers
     ================================ */

    private void validateValues(ColumnType type, String op, String... values) {
        int n = values == null ? 0 : values.length;
        boolean between = "BETWEEN".equals(op) || "NOT_BETWEEN".equals(op);

        if (between && n != 2) {
            throw new IllegalArgumentException("Operator " + op + " needs 2 values (from, to). Got " + n);
        }

        if (!between) {
            if (type == ColumnType.STRING) {
                if (n < 1) {
                    throw new IllegalArgumentException("String " + op + " needs at least 1 value.");
                }
            } else {
                if (n != 1) {
                    throw new IllegalArgumentException("Operator " + op + " needs exactly 1 value. Got " + n);
                }
            }
        }
    }

}

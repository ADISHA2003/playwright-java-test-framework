package PlaywrightPageObject.PojoClasses;

import java.util.List;

public class ScreenerTab {
    private String tabName;
    private List<DTFilter> filters;

    public String getTabName() { return tabName; }
    public List<DTFilter> getFilters() { return filters; }
}

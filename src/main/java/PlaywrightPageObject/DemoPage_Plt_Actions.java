package PlaywrightPageObject;

import com.microsoft.playwright.Page;

public class DemoPage_Plt_Actions extends BasePageActions {

    Page page;
    public DemoPage_Plt_Actions(Page page) {
        super(page);
        this.page = page;
    }
}

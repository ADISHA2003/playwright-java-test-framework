package PlaywrightPageObject;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CustomQuery_Plt extends BasePageActions{

    Page page;
    public CustomQuery_Plt(Page page) {
        super(page);
        this.page = page;
    }

    public void CustomQuery(){



    }
}

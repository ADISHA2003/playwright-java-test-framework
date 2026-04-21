package PlaywrightPageObject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.Arrays;
import java.util.LinkedList;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ScreenerDataTableActions extends BasePageActions {


    Locator dataTableTabs = page.locator("xpath=//div[@class=\"data-table-tabs\"]");


    LinkedList<String> companiesScreenerTabs = new LinkedList(Arrays.asList("Companies","Linked Investors","Linked Deals","Linked Professionals","Linked Charges"));
    LinkedList<String> assetManagerScreenerTabs = new LinkedList(Arrays.asList("Investors","Linked Companies","Linked Deals","Linked Professionals"));
    LinkedList<String> fundsScreenerTabs = new LinkedList(Arrays.asList("Funds","Linked Companies","Linked Deals"));
    LinkedList<String> limitedPartnerScreenerTabs = new LinkedList(Arrays.asList("Limited Partner","Linked Funds","Linked Companies","Linked Professionals"));
    LinkedList<String> familyOfficeScreenerTabs = new LinkedList(Arrays.asList("Investors","Linked Companies","Linked Deals","Linked Professionals"));
    LinkedList<String> allDealsTabsScreenerTabs = new LinkedList(Arrays.asList("Deals","Linked Companies","Linked Investors","Linked Professionals"));
    LinkedList<String> privateEquityInvestmentScreenerTabs = new LinkedList(Arrays.asList("Deals","Linked Companies","Linked Investors","Linked Professionals"));
    LinkedList<String> mergerAndAcquisitionScreenerTabs = new LinkedList(Arrays.asList("Deals","Linked Companies","Linked Investors","Linked Professionals"));
    LinkedList<String> privateEquityExitsScreenerTabs = new LinkedList(Arrays.asList("Deals","Linked Companies","Linked Investors","Linked Professionals"));
    LinkedList<String> equityCapitalMarketScreenerTabs = new LinkedList(Arrays.asList("Deals","Linked Companies","Linked Investors","Linked Professionals"));
    LinkedList<String> debtTransactionScreenerTabs = new LinkedList(Arrays.asList("Deals","Linked Companies","Linked Investors","Linked Professionals"));


    public ScreenerDataTableActions(Page page) {
        super(page);
    }


    public void verifyTableLayout(String screener){
        page.waitForTimeout(10000);
        LinkedList<String> screenerTabLayout = null;
        System.out.println(screener.toLowerCase().replace(" ","_"));
        switch (screener.toLowerCase().replace(" ","_")){
            case "all":
            case "company":
            case "companies": screenerTabLayout = companiesScreenerTabs; break;
            case "asset_manager": screenerTabLayout = assetManagerScreenerTabs; break;
            case "fund": screenerTabLayout = fundsScreenerTabs; break;
            case "limited_partner": screenerTabLayout = limitedPartnerScreenerTabs; break;
            case "family_office": screenerTabLayout = familyOfficeScreenerTabs; break;
            case "all_deals": screenerTabLayout = allDealsTabsScreenerTabs; break;
            case "private_equity_investment": screenerTabLayout = privateEquityInvestmentScreenerTabs; break;
            case "merger_and_acquisition": screenerTabLayout = mergerAndAcquisitionScreenerTabs; break;
            case "private_equity_exits": screenerTabLayout = privateEquityExitsScreenerTabs; break;
            case "equity_capital_market": screenerTabLayout = equityCapitalMarketScreenerTabs; break;
            case "debt_transaction": screenerTabLayout = debtTransactionScreenerTabs; break;
        }


        int i=0;
        for(String tab : screenerTabLayout){
            assertThat(dataTableTabs.locator("//span[text()='"+tab+"']")).isVisible();
        }


    }




}

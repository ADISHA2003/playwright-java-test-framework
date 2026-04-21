package PlaywrightPageObject.EntityDetailsStructure;
import org.apache.commons.collections4.map.LinkedMap;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class CompanyStructure {

    public LinkedHashMap<String,String> companyDetailsMainTab = new LinkedHashMap<>();
    public LinkedHashMap<String, LinkedHashMap<String,String>> companyDetailsSubTabs = new LinkedHashMap<>();

    public CompanyStructure(){
        //main tab details
        companyDetailsMainTab.put("Overview","overview");
        companyDetailsMainTab.put("Investors & Shareholding","investors-shareholding");
        companyDetailsMainTab.put("Transactions","transaction");
        companyDetailsMainTab.put("Financials","financials");
        companyDetailsMainTab.put("Team","team-background");
        companyDetailsMainTab.put("Key Developments","key-developments");
        companyDetailsMainTab.put("Subsidiaries & Affiliates","subsidiaries-affiliates");
        companyDetailsMainTab.put("Reports & Docs","reports-document");
        companyDetailsMainTab.put("Service Providers","service-providers");
        companyDetailsMainTab.put("Comparison Tool","comparison-tool");

        //sub tab details
        LinkedHashMap<String,String> financialsSubTabs = new LinkedHashMap<>();
        financialsSubTabs.put("Profit & Loss","profit-loss");
        financialsSubTabs.put("Balance Sheet","balance-sheet");
        financialsSubTabs.put("Cash Flow","cash-flow");
        financialsSubTabs.put("Ratios","ratios");
        financialsSubTabs.put("Index Of Charges","index-of-charges");
        financialsSubTabs.put("Shareholder Returns","shareholder-returns");
        financialsSubTabs.put("Credit Ratings","credit-ratings");
        financialsSubTabs.put("Overview","overview");
        companyDetailsSubTabs.put("financials",financialsSubTabs);


        LinkedHashMap<String,String> investorShareholdingSubtabs = new LinkedHashMap<>();
        investorShareholdingSubtabs.put("Current Investors","current-investors");
        investorShareholdingSubtabs.put("Prior Investors","prior-investors");
        investorShareholdingSubtabs.put("Shareholding","shareholding");
        companyDetailsSubTabs.put("investors-shareholding",investorShareholdingSubtabs);

        LinkedHashMap<String,String> transactionSubTabs = new LinkedHashMap<>();
        transactionSubTabs.put("As Target Company","as-target-company");
        transactionSubTabs.put("As Seller/Borrower","as-seller-borrower");
        transactionSubTabs.put("As Buyer/Lender Holdings","as-buyer-lender");
        transactionSubTabs.put("As Advisor","as-advisor");
        companyDetailsSubTabs.put("transaction",transactionSubTabs);

        LinkedHashMap<String,String> teamsSubtabs = new LinkedHashMap<>();
        teamsSubtabs.put("Management","management");
        teamsSubtabs.put("Board Of Directors","board-of-directors");
        teamsSubtabs.put("Prior Professionals","prior-professionals");
        companyDetailsSubTabs.put("team-background",teamsSubtabs);

        LinkedHashMap<String,String> subsidiariesAffiliatesSubtabs = new LinkedHashMap<>();
        subsidiariesAffiliatesSubtabs.put("Current Subsidiaries and Affiliates","current");
        subsidiariesAffiliatesSubtabs.put("Prior Subsidiaries and Affiliates","prior");
        companyDetailsSubTabs.put("subsidiaries-affiliates",subsidiariesAffiliatesSubtabs);

        LinkedHashMap<String,String> reportsAndDocs = new LinkedHashMap<>();
        reportsAndDocs.put("Documents","documents");
        reportsAndDocs.put("Reports","reports");
        companyDetailsSubTabs.put("reports-document",reportsAndDocs);

        LinkedHashMap<String,String> serviceProvidersSubtabs = new LinkedHashMap<>();
        serviceProvidersSubtabs.put("Deal Advisors","deal-advisors");
        serviceProvidersSubtabs.put("Auditors","auditors");
        companyDetailsSubTabs.put("service-providers",serviceProvidersSubtabs);

    }

    public void getCompanyPageViseAPIEndPoint(){
        LinkedHashMap<String,LinkedList<String>> companyPageAPIs = new LinkedHashMap<>();

        LinkedList<String> overviewApis = new LinkedList<>();
        overviewApis.add("businessModels");
        overviewApis.add("valuation-graph");
        overviewApis.add("coverage");
        overviewApis.add("yoy-revenue-growth");
        overviewApis.add("annual-revenue");
        overviewApis.add("ebidta-growth");
        overviewApis.add("ebidta-margin");
        overviewApis.add("net-profit");
        overviewApis.add("shareholding");
    }

}

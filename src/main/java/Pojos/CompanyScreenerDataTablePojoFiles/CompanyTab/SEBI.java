package Pojos.CompanyScreenerDataTablePojoFiles.CompanyTab;

public class SEBI {
    private String sector;
    private String industry;
    private String macroEconomicIndicator;
    private String basicIndCode;
    private String basicIndustry;
    private boolean nodeEnabled;
    private boolean mobileAppFirst;
    private String uniqueString;

    // Getters and setters
    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getMacroEconomicIndicator() {
        return macroEconomicIndicator;
    }

    public void setMacroEconomicIndicator(String macroEconomicIndicator) {
        this.macroEconomicIndicator = macroEconomicIndicator;
    }

    public String getBasicIndCode() {
        return basicIndCode;
    }

    public void setBasicIndCode(String basicIndCode) {
        this.basicIndCode = basicIndCode;
    }

    public String getBasicIndustry() {
        return basicIndustry;
    }

    public void setBasicIndustry(String basicIndustry) {
        this.basicIndustry = basicIndustry;
    }

    public boolean isNodeEnabled() {
        return nodeEnabled;
    }

    public void setNodeEnabled(boolean nodeEnabled) {
        this.nodeEnabled = nodeEnabled;
    }

    public boolean isMobileAppFirst() {
        return mobileAppFirst;
    }

    public void setMobileAppFirst(boolean mobileAppFirst) {
        this.mobileAppFirst = mobileAppFirst;
    }

    public String getUniqueString() {
        return uniqueString;
    }

    public void setUniqueString(String uniqueString) {
        this.uniqueString = uniqueString;
    }
}

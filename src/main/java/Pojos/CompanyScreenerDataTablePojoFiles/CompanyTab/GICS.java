package Pojos.CompanyScreenerDataTablePojoFiles.CompanyTab;

public class GICS {
    private String sector;
    private String industry;
    private String subIndustry;
    private String subIndustryCode;
    private String industryGroup;
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

    public String getSubIndustry() {
        return subIndustry;
    }

    public void setSubIndustry(String subIndustry) {
        this.subIndustry = subIndustry;
    }

    public String getSubIndustryCode() {
        return subIndustryCode;
    }

    public void setSubIndustryCode(String subIndustryCode) {
        this.subIndustryCode = subIndustryCode;
    }

    public String getIndustryGroup() {
        return industryGroup;
    }

    public void setIndustryGroup(String industryGroup) {
        this.industryGroup = industryGroup;
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

package Pojos.CompanyScreenerDataTablePojoFiles.CompanyTab;

import java.util.List;

public class Taxonomy {
    private String businessModelId;
    private String sector;
    private String industry;
    private String subIndustry;
    private String category;
    private List<String> competingBusinessModels;
    private boolean nodeEnabled;
    private Levels levels;
    private boolean mobileAppFirst;
    private boolean isPrimary;
    private String uniqueString;

    // Getters and setters
    public String getBusinessModelId() {
        return businessModelId;
    }

    public void setBusinessModelId(String businessModelId) {
        this.businessModelId = businessModelId;
    }

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getCompetingBusinessModels() {
        return competingBusinessModels;
    }

    public void setCompetingBusinessModels(List<String> competingBusinessModels) {
        this.competingBusinessModels = competingBusinessModels;
    }

    public boolean isNodeEnabled() {
        return nodeEnabled;
    }

    public void setNodeEnabled(boolean nodeEnabled) {
        this.nodeEnabled = nodeEnabled;
    }

    public Levels getLevels() {
        return levels;
    }

    public void setLevels(Levels levels) {
        this.levels = levels;
    }

    public boolean isMobileAppFirst() {
        return mobileAppFirst;
    }

    public void setMobileAppFirst(boolean mobileAppFirst) {
        this.mobileAppFirst = mobileAppFirst;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public String getUniqueString() {
        return uniqueString;
    }

    public void setUniqueString(String uniqueString) {
        this.uniqueString = uniqueString;
    }
}

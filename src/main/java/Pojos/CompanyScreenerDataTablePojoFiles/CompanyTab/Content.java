package Pojos.CompanyScreenerDataTablePojoFiles.CompanyTab;

import java.util.List;

import Pojos.entityMongo.Taxonomy;

public class Content {

    private String id;
    private int companyId;
    private String companyName;
    private String companyType;
    private Integer foundedYear;
    private String dba;
    private String website;
    private String logo;
    private String businessDescription;
    private Integer employeeCount;
    private String companyStatus;
    private String companyStage;
    private String valuationClass;
    private List<Taxonomy> taxonomy;
    private List<InvestorInfo> investorInfo;
    private Contact contact;
    private LatestFinancialData latestFinancialData;
    private Double totalFunding;
    private Double lastEquityFundingRaised;
    private Double postMoneyValuation;
    private FinancialData latestMarketCap;
    private Double overallScore;
    private String roundOfInvestment;
    private Integer currentInvestorCount;
    private List<String> investorName;
    private boolean isBookmarked;
    private String currencyType;
    private String numberScale;
    private String entityId;
    private List<GICS> GICS;
    private List<SEBI> SEBI;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public Integer getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(Integer foundedYear) {
        this.foundedYear = foundedYear;
    }

    public String getDba() {
        return dba;
    }

    public void setDba(String dba) {
        this.dba = dba;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBusinessDescription() {
        return businessDescription;
    }

    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getCompanyStage() {
        return companyStage;
    }

    public void setCompanyStage(String companyStage) {
        this.companyStage = companyStage;
    }

    public String getValuationClass() {
        return valuationClass;
    }

    public void setValuationClass(String valuationClass) {
        this.valuationClass = valuationClass;
    }

    public List<Taxonomy> getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(List<Taxonomy> taxonomy) {
        this.taxonomy = taxonomy;
    }

    public List<InvestorInfo> getInvestorInfo() {
        return investorInfo;
    }

    public void setInvestorInfo(List<InvestorInfo> investorInfo) {
        this.investorInfo = investorInfo;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public LatestFinancialData getLatestFinancialData() {
        return latestFinancialData;
    }

    public void setLatestFinancialData(LatestFinancialData latestFinancialData) {
        this.latestFinancialData = latestFinancialData;
    }

    public Double getTotalFunding() {
        return totalFunding;
    }

    public void setTotalFunding(Double totalFunding) {
        this.totalFunding = totalFunding;
    }

    public Double getLastEquityFundingRaised() {
        return lastEquityFundingRaised;
    }

    public void setLastEquityFundingRaised(Double lastEquityFundingRaised) {
        this.lastEquityFundingRaised = lastEquityFundingRaised;
    }

    public Double getPostMoneyValuation() {
        return postMoneyValuation;
    }

    public void setPostMoneyValuation(Double postMoneyValuation) {
        this.postMoneyValuation = postMoneyValuation;
    }

    public FinancialData getLatestMarketCap() {
        return latestMarketCap;
    }

    public void setLatestMarketCap(FinancialData latestMarketCap) {
        this.latestMarketCap = latestMarketCap;
    }

    public Double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Double overallScore) {
        this.overallScore = overallScore;
    }

    public String getRoundOfInvestment() {
        return roundOfInvestment;
    }

    public void setRoundOfInvestment(String roundOfInvestment) {
        this.roundOfInvestment = roundOfInvestment;
    }

    public Integer getCurrentInvestorCount() {
        return currentInvestorCount;
    }

    public void setCurrentInvestorCount(Integer currentInvestorCount) {
        this.currentInvestorCount = currentInvestorCount;
    }

    public List<String> getInvestorName() {
        return investorName;
    }

    public void setInvestorName(List<String> investorName) {
        this.investorName = investorName;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getNumberScale() {
        return numberScale;
    }

    public void setNumberScale(String numberScale) {
        this.numberScale = numberScale;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public List<GICS> getGICS() {
        return GICS;
    }

    public void setGICS(List<GICS> GICS) {
        this.GICS = GICS;
    }

    public List<SEBI> getSEBI() {
        return SEBI;
    }

    public void setSEBI(List<SEBI> SEBI) {
        this.SEBI = SEBI;
    }
}

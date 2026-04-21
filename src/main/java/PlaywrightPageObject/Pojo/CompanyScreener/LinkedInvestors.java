package PlaywrightPageObject.Pojo.CompanyScreener;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkedInvestors {
    @JsonProperty("Investor Name")
    private String investorName;

    @JsonProperty("Investments In")
    private String investmentsIn;

    @JsonProperty("Fund Type")
    private String fundType;

    @JsonProperty("Location")
    private String location;

    @JsonProperty("Asset Under Management (AUM) $mn")
    private Double aumUsdMn;

    @JsonProperty("Area of Interest")
    private String areaOfInterest;

    @JsonProperty("Current Investments")
    private Double currentInvestments;

    @JsonProperty("Deals in last 12 months")
    private Double dealsInLast12Months;

    @JsonProperty("Asset Manager")
    private String assetManager;

    @JsonProperty("Fund Size ₹cr")
    private Double fundSizeInCr;

    @JsonProperty("Geographical Preference")
    private String geographicalPreference;

    @JsonProperty("Number of Deals")
    private Double numberOfDeals;

    @JsonProperty("Fund Status")
    private String fundStatus;

    @JsonProperty("Launched Date")
    private String launchedDate;

    @JsonProperty("Amount Raised ₹cr")
    private Double amountRaised;

    @JsonProperty("Investment Type")
    private String investmentType;

    @JsonProperty("Investment Size (Min) $mn")
    private Double investmentSizeMin;

    @JsonProperty("Investment Size (Max) $mn")
    private Double investmentSizeMax;

    @JsonProperty("Number of Exits")
    private Double numberOfExits;

    @JsonProperty("Deal Type")
    private String dealType;

    @JsonProperty("Total Investment (All Deals Amount) ₹cr")
    private Double totalInvestmentAllDeals;

    public LinkedInvestors() {}

    // Getters and setters (concise)
    public String getInvestorName() { return investorName; }
    public void setInvestorName(String investorName) { this.investorName = investorName; }

    public String getInvestmentsIn() { return investmentsIn; }
    public void setInvestmentsIn(String investmentsIn) { this.investmentsIn = investmentsIn; }

    public String getFundType() { return fundType; }
    public void setFundType(String fundType) { this.fundType = fundType; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Double getAumUsdMn() { return aumUsdMn; }
    public void setAumUsdMn(Double aumUsdMn) { this.aumUsdMn = aumUsdMn; }

    public String getAreaOfInterest() { return areaOfInterest; }
    public void setAreaOfInterest(String areaOfInterest) { this.areaOfInterest = areaOfInterest; }

    public Double getCurrentInvestments() { return currentInvestments; }
    public void setCurrentInvestments(Double currentInvestments) { this.currentInvestments = currentInvestments; }

    public Double getDealsInLast12Months() { return dealsInLast12Months; }
    public void setDealsInLast12Months(Double dealsInLast12Months) { this.dealsInLast12Months = dealsInLast12Months; }

    public String getAssetManager() { return assetManager; }
    public void setAssetManager(String assetManager) { this.assetManager = assetManager; }

    public Double getFundSizeInCr() { return fundSizeInCr; }
    public void setFundSizeInCr(Double fundSizeInCr) { this.fundSizeInCr = fundSizeInCr; }

    public String getGeographicalPreference() { return geographicalPreference; }
    public void setGeographicalPreference(String geographicalPreference) { this.geographicalPreference = geographicalPreference; }

    public Double getNumberOfDeals() { return numberOfDeals; }
    public void setNumberOfDeals(Double numberOfDeals) { this.numberOfDeals = numberOfDeals; }

    public String getFundStatus() { return fundStatus; }
    public void setFundStatus(String fundStatus) { this.fundStatus = fundStatus; }

    public String getLaunchedDate() { return launchedDate; }
    public void setLaunchedDate(String launchedDate) { this.launchedDate = launchedDate; }

    public Double getAmountRaised() { return amountRaised; }
    public void setAmountRaised(Double amountRaised) { this.amountRaised = amountRaised; }

    public String getInvestmentType() { return investmentType; }
    public void setInvestmentType(String investmentType) { this.investmentType = investmentType; }

    public Double getInvestmentSizeMin() { return investmentSizeMin; }
    public void setInvestmentSizeMin(Double investmentSizeMin) { this.investmentSizeMin = investmentSizeMin; }

    public Double getInvestmentSizeMax() { return investmentSizeMax; }
    public void setInvestmentSizeMax(Double investmentSizeMax) { this.investmentSizeMax = investmentSizeMax; }

    public Double getNumberOfExits() { return numberOfExits; }
    public void setNumberOfExits(Double numberOfExits) { this.numberOfExits = numberOfExits; }

    public String getDealType() { return dealType; }
    public void setDealType(String dealType) { this.dealType = dealType; }

    public Double getTotalInvestmentAllDeals() { return totalInvestmentAllDeals; }
    public void setTotalInvestmentAllDeals(Double totalInvestmentAllDeals) { this.totalInvestmentAllDeals = totalInvestmentAllDeals; }
}

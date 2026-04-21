package PlaywrightPageObject.Pojo.CompanyScreener;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkedDeals {
    @JsonProperty("Target Company")
    private String targetCompany;

    @JsonProperty("Deal Date")
    private String dealDate;

    @JsonProperty("Deal Description")
    private String dealDescription;

    @JsonProperty("Deal Type")
    private String dealType;

    @JsonProperty("Buyer/Lender")
    private String buyerOrLender;

    @JsonProperty("Seller/Borrower")
    private String sellerOrBorrower;

    @JsonProperty("Deal Value ₹cr")
    private Double dealValue;

    @JsonProperty("Deal Subtype")
    private String dealSubtype;

    @JsonProperty("Deal Feature")
    private String dealFeature;

    @JsonProperty("Deal Status")
    private String dealStatus;

    @JsonProperty("Transaction Announced Date")
    private String transactionAnnouncedDate;

    @JsonProperty("Transaction Closing Date")
    private String transactionClosingDate;

    @JsonProperty("Cancelled Date")
    private String cancelledDate;

    @JsonProperty("Deal Stage")
    private String dealStage;

    @JsonProperty("Change in Control")
    private String changeInControl;

    @JsonProperty("% Sought")
    private Double percentSought;

    @JsonProperty("EV/Revenue")
    private Double evRevenue;

    @JsonProperty("EV/EBITDA")
    private Double evEbitda;

    @JsonProperty("EV/PAT")
    private Double evPat;

    @JsonProperty("Deal Details")
    private String dealDetails;

    public LinkedDeals() {}

    // Getters and setters (only a few shown, add rest similarly)
    public String getTargetCompany() { return targetCompany; }
    public void setTargetCompany(String targetCompany) { this.targetCompany = targetCompany; }

    public String getDealDate() { return dealDate; }
    public void setDealDate(String dealDate) { this.dealDate = dealDate; }

    public String getDealDescription() { return dealDescription; }
    public void setDealDescription(String dealDescription) { this.dealDescription = dealDescription; }

    public String getDealType() { return dealType; }
    public void setDealType(String dealType) { this.dealType = dealType; }

    public String getBuyerOrLender() { return buyerOrLender; }
    public void setBuyerOrLender(String buyerOrLender) { this.buyerOrLender = buyerOrLender; }

    public String getSellerOrBorrower() { return sellerOrBorrower; }
    public void setSellerOrBorrower(String sellerOrBorrower) { this.sellerOrBorrower = sellerOrBorrower; }

    public Double getDealValue() { return dealValue; }
    public void setDealValue(Double dealValue) { this.dealValue = dealValue; }

    public String getDealSubtype() { return dealSubtype; }
    public void setDealSubtype(String dealSubtype) { this.dealSubtype = dealSubtype; }

    public String getDealFeature() { return dealFeature; }
    public void setDealFeature(String dealFeature) { this.dealFeature = dealFeature; }

    public String getDealStatus() { return dealStatus; }
    public void setDealStatus(String dealStatus) { this.dealStatus = dealStatus; }

    public String getTransactionAnnouncedDate() { return transactionAnnouncedDate; }
    public void setTransactionAnnouncedDate(String transactionAnnouncedDate) { this.transactionAnnouncedDate = transactionAnnouncedDate; }

    public String getTransactionClosingDate() { return transactionClosingDate; }
    public void setTransactionClosingDate(String transactionClosingDate) { this.transactionClosingDate = transactionClosingDate; }

    public String getCancelledDate() { return cancelledDate; }
    public void setCancelledDate(String cancelledDate) { this.cancelledDate = cancelledDate; }

    public String getDealStage() { return dealStage; }
    public void setDealStage(String dealStage) { this.dealStage = dealStage; }

    public String getChangeInControl() { return changeInControl; }
    public void setChangeInControl(String changeInControl) { this.changeInControl = changeInControl; }

    public Double getPercentSought() { return percentSought; }
    public void setPercentSought(Double percentSought) { this.percentSought = percentSought; }

    public Double getEvRevenue() { return evRevenue; }
    public void setEvRevenue(Double evRevenue) { this.evRevenue = evRevenue; }

    public Double getEvEbitda() { return evEbitda; }
    public void setEvEbitda(Double evEbitda) { this.evEbitda = evEbitda; }

    public Double getEvPat() { return evPat; }
    public void setEvPat(Double evPat) { this.evPat = evPat; }

    public String getDealDetails() { return dealDetails; }
    public void setDealDetails(String dealDetails) { this.dealDetails = dealDetails; }
}

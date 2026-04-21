package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
@Table(name = "ipo")
public class IPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ipoid")
    private Integer ipoId;

    @Column(name = "primary_feature")
    private Integer primaryFeature;

    @Column(name = "iposize")
    private Double ipoSize;

    @Column(name = "iposize_inr")
    private Double ipoSizeInINR;

    @Column(name = "security")
    private Integer security;

    @Column(name = "outstanding_shares")
    private Double outstandingShares;

    @Column(name = "po_outstanding_shares")
    private Double poOutstandingShares;

    @Column(name = "minimumprice")
    private Integer minimumPrice;

    @Column(name = "maximumprice")
    private Integer maximumPrice;

    @Column(name = "sharesbeingofferedforsale")
    private Integer sharesBeingOfferedForSale;

    @Column(name = "primary_shares")
    private Double primaryShares;

    @Column(name = "secondary_shares")
    private Double secondaryShares;

    @Column(name = "facevalueofshares")
    private Double faceValueOfShares;

    @Column(name = "methodofipo")
    private Integer methodOfIPO;

    @Column(name = "transaction_status")
    private String transactionStatus;

    @Column(name = "openingdate")
    private String openingDate;

    @Column(name = "closingdate")
    private String closingDate;

    @Column(name = "cancellationdate")
    private String cancellationDate;

    @Column(name = "expected_listing_date")
    private String expectedListingDate;

    @Column(name = "transaction_listing_date")
    private String transactionListingDate;

    @Column(name = "issue_opening_date")
    private String issueOpeningDate;

    @Column(name = "issue_closing_date")
    private String issueClosingDate;

    @Column(name = "listing_date")
    private String listingDate;

    @Column(name = "prospectus_date")
    private String prospectusDate;

    @Column(name = "red_herring_date")
    private String redHerringDate;

    @Column(name = "draft_red_herring_date")
    private String draftRedHerringDate;

    @Column(name = "listing_low_price")
    private Double listingLowPrice;

    @Column(name = "prospectus_low_price")
    private Double prospectusLowPrice;

    @Column(name = "red_herring_low_price")
    private Double redHerringLowPrice;

    @Column(name = "red_herring_low_price2")
    private Double redHerringLowPrice2;

    @Column(name = "draft_red_herring_low_price")
    private Double draftRedHerringLowPrice;

    @Column(name = "listing_high_price")
    private Double listingHighPrice;

    @Column(name = "prospectus_high_price")
    private Double prospectusHighPrice;

    @Column(name = "red_herring_high_price")
    private Double redHerringHighPrice;

    @Column(name = "draft_red_herring_high_price")
    private Double draftRedHerringHighPrice;

    @Column(name = "listing_issuesize")
    private Double listingIssueSize;

    @Column(name = "prospectus_issuesize")
    private Double prospectusIssueSize;

    @Column(name = "red_herring_issuesize")
    private Double redHerringIssueSize;

    @Column(name = "draft_red_herring_issuesize")
    private Double draftRedHerringIssueSize;

    @Column(name = "listing_link")
    private String listingLink;

    @Column(name = "prospectus_link")
    private String prospectusLink;

    @Column(name = "red_herring_link")
    private String redHerringLink;

    @Column(name = "draft_red_herring_link")
    private String draftRedHerringLink;

    @Column(name = "expenses_value")
    private Double expensesValue;

    @Column(name = "commision_value")
    private Double commissionValue;

    @Column(name = "net_proceeds_value")
    private Double netProceedsValue;

    @Column(name = "expenses_issuesize")
    private Double expensesIssueSize;

    @Column(name = "commision_issuesize")
    private Double commissionIssueSize;

    @Column(name = "netproceeds_issuesize")
    private Double netProceedsIssueSize;

    @Column(name = "overallotment_option")
    private Double overallotmentOption;

    @Column(name = "overallotment_exercised")
    private Double overallotmentExercised;

    @Column(name = "overallotment_option_dealsize")
    private Double overallotmentOptionDealSize;

    @Column(name = "overallotment_exercised_dealsize")
    private Double overallotmentExercisedDealSize;

    @Column(name = "ipo_grading")
    private String ipoGrading;

    @Column(name = "proceeds_use", columnDefinition = "longtext")
    private String proceedsUse;

    @Column(name = "anchor_investors_shares")
    private Double anchorInvestorsShares;

    @Column(name = "retail_investors_shares")
    private Double retailInvestorsShares;

    @Column(name = "non_institutional_investors_shares")
    private Double nonInstitutionalInvestorsShares;

    @Column(name = "qualified_investors_shares")
    private Double qualifiedInvestorsShares;

    @Column(name = "employees_shares")
    private Double employeesShares;

    @Column(name = "anchor_investors_subscription")
    private Double anchorInvestorsSubscription;

    @Column(name = "retail_investors_subscription")
    private Double retailInvestorsSubscription;

    @Column(name = "non_institutional_investors_subscription")
    private Double nonInstitutionalInvestorsSubscription;

    @Column(name = "qualified_investors_subscription")
    private Double qualifiedInvestorsSubscription;

    @Column(name = "employees_subscription")
    private Double employeesSubscription;

    @Column(name = "primary_exchange")
    private Integer primaryExchange;

    @Column(name = "other_exchange1")
    private Integer otherExchange1;

    @Column(name = "other_exchange2")
    private Integer otherExchange2;

    @Column(name = "ticker1")
    private String ticker1;

    @Column(name = "ticker2")
    private String ticker2;

    @Column(name = "ticker3")
    private String ticker3;

    @Column(name = "deal_features")
    private String dealFeatures;

    @Column(name = "stakebeingdivested")
    private Double stakeBeingDivested;

    @Column(name = "usageofproceeds", columnDefinition = "longtext")
    private String usageOfProceeds;

    @Column(name = "retail")
    private String retail;

    @Column(name = "hni")
    private Integer hni;

    @Column(name = "qfi")
    private Integer qfi;

    @Column(name = "dealsize_prop")
    private Integer dealSizeProp;

    @Column(name = "ipo_exit_value")
    private Double ipoExitValue;

    @Column(name = "ipo_exit_percentage")
    private Double ipoExitPercentage;

    @Column(name = "updated_at", columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private String updatedAt;

    @Column(name = "created_at", columnDefinition = "timestamp default current_timestamp")
    private String createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;
}
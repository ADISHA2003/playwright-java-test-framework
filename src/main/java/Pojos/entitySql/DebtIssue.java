package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;



@Entity
@Data
@Table(name = "debt_issue")
public class DebtIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "debt_issue_id")
    private Integer debtIssueId;

    @Column(name = "primary_feature")
    private Integer primaryFeature;

    @Column(name = "deal_size")
    private Double dealSize;

    @Column(name = "currency", columnDefinition = "varchar(255)")
    private String currency;

    @Column(name = "offering_size")
    private Double offeringSize;

    @Column(name = "net_offering_size")
    private Double netOfferingSize;

    @Column(name = "base_issue_size")
    private Double baseIssueSize;

    @Column(name = "over_subscription_size")
    private Double overSubscriptionSize;

    @Column(name = "announcementdate")
    private String announcementDate;

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

    @Column(name = "transactionstatus")
    private Integer transactionStatus;

    @Column(name = "transactiondescription", columnDefinition = "longtext")
    private String transactionDescription;

    @Column(name = "useofproceeds", columnDefinition = "text")
    private String useOfProceeds;

    @Column(name = "dealfeatureid")
    private String dealFeatureId;

    @Column(name = "dealconditionid")
    private String dealConditionId;

    @Column(name = "dealsize_prop")
    private Integer dealSizeProp;

    @Column(name = "advisory_fees_amount")
    private Double advisoryFeesAmount;

    @Column(name = "advisory_fees_issue_size")
    private Double advisoryFeesIssueSize;

    @Column(name = "registrar_fees_amount")
    private Double registrarFeesAmount;

    @Column(name = "registrar_fees_issue_size")
    private Double registrarFeesIssueSize;

    @Column(name = "lead_managers_underwritin_commission_amount")
    private Double leadManagersUnderwritingCommissionAmount;

    @Column(name = "lead_managers_underwritin_commission_issue_size")
    private Double leadManagersUnderwritingCommissionIssueSize;

    @Column(name = "brokerage_selling_commision_amount")
    private Double brokerageSellingCommissionAmount;

    @Column(name = "brokerage_selling_commision_issue_size")
    private Double brokerageSellingCommissionIssueSize;

    @Column(name = "bond_trustee_fees_amount")
    private Double bondTrusteeFeesAmount;

    @Column(name = "bond_trustee_fees_issue_size")
    private Double bondTrusteeFeesIssueSize;

    @Column(name = "advertising_marketing_expenses_amount")
    private Double advertisingMarketingExpensesAmount;

    @Column(name = "advertising_marketing_expenses_issue_size")
    private Double advertisingMarketingExpensesIssueSize;

    @Column(name = "printing_stationery_amount")
    private Double printingStationeryAmount;

    @Column(name = "printing_stationery_issue_size")
    private Double printingStationeryIssueSize;

    @Column(name = "others_amount")
    private Double othersAmount;

    @Column(name = "others_issue_size")
    private Double othersIssueSize;

    @Column(name = "total_expenses_amount")
    private Double totalExpensesAmount;

    @Column(name = "cat_name_1")
    private String categoryName1;

    @Column(name = "cat_1_allotment")
    private Double category1Allotment;

    @Column(name = "cat_1_subscription")
    private Double category1Subscription;

    @Column(name = "cat_name_2")
    private String categoryName2;

    @Column(name = "cat_2_allotment")
    private Double category2Allotment;

    @Column(name = "cat_2_subscription")
    private Double category2Subscription;

    @Column(name = "cat_name_3")
    private String categoryName3;

    @Column(name = "cat_3_allotment")
    private Double category3Allotment;

    @Column(name = "cat_3_subscription")
    private Double category3Subscription;

    @Column(name = "cat_name_4")
    private String categoryName4;

    @Column(name = "cat_4_allotment")
    private Double category4Allotment;

    @Column(name = "cat_4_subscription")
    private Double category4Subscription;

    @Column(name = "cat_name_5")
    private String categoryName5;

    @Column(name = "cat_5_allotment")
    private Double category5Allotment;

    @Column(name = "cat_5_subscription")
    private Double category5Subscription;

    @Column(name = "cat_name_6")
    private String categoryName6;

    @Column(name = "cat_6_allotment")
    private Double category6Allotment;

    @Column(name = "cat_6_subscription")
    private Double category6Subscription;

    @Column(name = "cat_name_7")
    private String categoryName7;

    @Column(name = "cat_7_allotment")
    private Double category7Allotment;

    @Column(name = "cat_7_subscription")
    private Double category7Subscription;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "created_at")
    private String createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;


    // Getters and setters
}

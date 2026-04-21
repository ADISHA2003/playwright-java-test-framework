package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "ecm")
public class Ecm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ecm_deal_id")
    private Integer ecmDealId;

    @Column(name = "primary_feature")
    private Integer primaryFeature;

    @Column(name = "currency", columnDefinition = "varchar(255)")
    private String currency;

    @Column(name = "issue_size")
    private Double issueSize;

    @Column(name = "issue_size_inr")
    private Double issueSizeInr;

    @Column(name = "transaction_status")
    private Integer transactionStatus;

    @Column(name = "announced_date")
    private String announcedDate;

    @Column(name = "closed_date")
    private String closedDate;

    @Column(name = "cancelled_date")
    private String cancelledDate;

    @Column(name = "board_resolution_date")
    private String boardResolutionDate;

    @Column(name = "shareholders_resolution_date")
    private String shareholdersResolutionDate;

    @Column(name = "issue_opening_date")
    private String issueOpeningDate;

    @Column(name = "issue_closing_date")
    private String issueClosingDate;

    @Column(name = "final_date")
    private String finalDate;

    @Column(name = "pricing_date")
    private String pricingDate;

    @Column(name = "preliminary_date")
    private String preliminaryDate;

    @Column(name = "preliminary_low_price")
    private Double preliminaryLowPrice;

    @Column(name = "preliminary_high_price")
    private Double preliminaryHighPrice;

    @Column(name = "final_price")
    private Double finalPrice;

    @Column(name = "pricing_low_price")
    private Double pricingLowPrice;

    @Column(name = "pricing_high_price")
    private Double pricingHighPrice;

    @Column(name = "final_issue_size")
    private Double finalIssueSize;

    @Column(name = "pricing_issue_size")
    private Double pricingIssueSize;

    @Column(name = "preliminary_issue_size")
    private Double preliminaryIssueSize;

    @Column(name = "final_shares_offered")
    private Double finalSharesOffered;

    @Column(name = "pricing_shares_offered")
    private Double pricingSharesOffered;

    @Column(name = "preliminary_shares_offered")
    private Double preliminarySharesOffered;

    @Column(name = "expenses_value")
    private Double expensesValue;

    @Column(name = "net_proceeds_value")
    private Double netProceedsValue;

    @Column(name = "expenses_percent")
    private Double expensesPercent;

    @Column(name = "net_proceeds_percent")
    private Double netProceedsPercent;

    @Column(name = "transaction_description", columnDefinition = "longtext")
    private String transactionDescription;

    @Column(name = "retail_investors_shares")
    private Double retailInvestorsShares;

    @Column(name = "non_retail_investors_shares")
    private Double nonRetailInvestorsShares;

    @Column(name = "deal_features", columnDefinition = "varchar(255)")
    private String dealFeatures;

    @Column(name = "use_of_proceeds", columnDefinition = "longtext")
    private String useOfProceeds;

    @Column(name = "ecm_exit_value")
    private Double ecmExitValue;

    @Column(name = "ecm_exit_percentage")
    private Double ecmExitPercentage;

    @Column(name = "issue_listing_date")
    private String issueListingDate;

    @Column(name = "pe_deal_value")
    private Double peDealValue;

    @Column(name = "pe_percentage_sought")
    private Double pePercentageSought;

    @Column(name = "dealsize_prop")
    private Integer dealsizeProp;

    @Column(name = "updated_at", columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private String updatedAt;

    @Column(name = "created_at", columnDefinition = "timestamp default current_timestamp")
    private String createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    // Getters and setters
}

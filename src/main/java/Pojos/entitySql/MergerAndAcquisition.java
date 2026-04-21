package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
@Table(name = "mergeracquisitiondetails")
public class MergerAndAcquisition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mergeracquisitionid")
    private Integer mergerAcquisitionId;

    @Column(name = "announcementdate")
    private String announcementDate;

    @Column(name = "closingdate")
    private String closingDate;

    @Column(name = "cancellationdate")
    private String cancellationDate;

    @Column(name = "percentagesought")
    private Double percentageSought;

    @Column(name = "transactionstatus")
    private Integer transactionStatus;

    @Column(name = "transactiondescription", columnDefinition = "longtext")
    private String transactionDescription;

    @Column(name = "changeincontrol")
    private Integer changeInControl;

    @Column(name = "dealfeatureids", columnDefinition = "varchar(255)")
    private String dealFeatureIds;

    @Column(name = "dealconditionids", columnDefinition = "varchar(255)")
    private String dealConditionIds;

    @Column(name = "considerationtoshareholders")
    private Double considerationToShareholders;

    @Column(name = "assumptionofdebt")
    private Double assumptionOfDebt;

    @Column(name = "otherconsiderations")
    private Double otherConsiderations;

    @Column(name = "price_per_share")
    private Double pricePerShare;

    @Column(name = "impliedequityvalue")
    private Double impliedEquityValue;

    @Column(name = "impliedenterprisevalue")
    private Double impliedEnterpriseValue;

    @Column(name = "implied_equity_value_inr")
    private Double impliedEquityValueInr;

    @Column(name = "implied_enterprise_value_inr")
    private Double impliedEnterpriseValueInr;

    @Column(name = "offerpershare")
    private Double offerPerShare;

    @Column(name = "cash")
    private Double cash;

    @Column(name = "stock")
    private Double stock;

    @Column(name = "options")
    private Double options;

    @Column(name = "earnout")
    private Double earnout;

    @Column(name = "debt")
    private Double debt;

    @Column(name = "biddate")
    private String bidDate;

    @Column(name = "loidate")
    private String loiDate;

    @Column(name = "definitiveaggrementdate")
    private String definitiveAgreementDate;

    @Column(name = "total_deal_value")
    private Double totalDealValue;

    @Column(name = "dealsize_prop")
    private Integer dealSizeProp;

    @Column(name = "mna_exit_value")
    private Double mnaExitValue;

    @Column(name = "mna_exit_percentage")
    private Double mnaExitPercentage;

    @Column(name = "outstanding_shares")
    private Double outstandingShares;

    @Column(name = "mna_type")
    private Integer mnaType;

    @Column(name = "new_money")
    private Double newMoney;

    @Column(name = "stake_type")
    private Integer stakeType;

    @Column(name = "rumored_total_deal_value")
    private Double rumoredTotalDealValue;

    @Column(name = "rumored_percentage_sought")
    private Double rumoredPercentageSought;

    @Column(name = "rumored_mna_exit_value")
    private Double rumoredMnaExitValue;

    @Column(name = "rumored_mna_exit_percentage")
    private Double rumoredMnaExitPercentage;

    @Column(name = "use_of_proceeds", columnDefinition = "longtext")
    private String useOfProceeds;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "created_at")
    private String createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    // Getters and setters
}

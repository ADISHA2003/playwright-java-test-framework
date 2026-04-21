package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
@Table(name = "privateplacement")
public class PrivatePlacement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "privateplacementid")
    private Integer privatePlacementId;

    @Column(name = "announcementdate")
    private String announcementDate;

    @Column(name = "closingdate")
    private String closingDate;

    @Column(name = "cancellationdate")
    private String cancellationDate;

    @Column(name = "transactionstatus")
    private Integer transactionStatus;

    @Column(name = "transactiondescription", columnDefinition = "longtext")
    private String transactionDescription;

    @Column(name = "dealfeatureid", columnDefinition = "varchar(255)")
    private String dealFeatureId;


    @Column(name = "dealconditionid", columnDefinition = "varchar(255)")
    private String dealConditionId;

    @Column(name = "percentagesought")
    private Double percentageSought;

    @Column(name = "changeincontrol")
    private boolean changeInControl;
   // changeInControl dealFeatureId

    @Column(name = "price_per_share")
    private Double pricePerShare;

    @Column(name = "implied_equity_value")
    private Double impliedEquityValue;

    @Column(name = "implied_equity_value_ev")
    private Double impliedEquityValueEv;

    @Column(name = "implied_equity_value_inr")
    private Double impliedEquityValueInr;

    @Column(name = "implied_equity_value_ev_inr")
    private Double impliedEquityValueEvInr;

    @Column(name = "valuation", columnDefinition = "varchar(255)")
    private String valuation;

    @Column(name = "revenue", columnDefinition = "varchar(255)")
    private String revenue;

    @Column(name = "netprofit", columnDefinition = "varchar(255)")
    private String netProfit;

    @Column(name = "stage_investment")
    private Integer stageInvestment;

    @Column(name = "roundofinvestment")
    private Integer roundOfInvestment;

    @Column(name = "amountbeinginvested")
    private Double amountBeingInvested;

    @Column(name = "basecapitalpriortoinvestment")
    private Double baseCapitalPriorToInvestment;

    @Column(name = "basecapitalafterinvestment")
    private Double baseCapitalAfterInvestment;

    @Column(name = "dealsize_prop")
    private Integer dealSizeProp;

    @Column(name = "pe_exit_value")
    private Double peExitValue;

    @Column(name = "co_investment_value")
    private Integer coInvestmentValue;

    @Column(name = "pe_exit_percentage")
    private Double peExitPercentage;

    @Column(name = "outstanding_shares")
    private Double outstandingShares;

    @Column(name = "new_money")
    private Double newMoney;

    @Column(name = "rumored_deal_value")
    private Double rumoredDealValue;

    @Column(name = "rumored_percentage_sought")
    private Double rumoredPercentageSought;

    @Column(name = "rumored_pe_exit_value")
    private Double rumoredPeExitValue;

    @Column(name = "rumored_pe_exit_percentage")
    private Double rumoredPeExitPercentage;

    @Column(name = "use_of_proceeds", columnDefinition = "longtext")
    private String useOfProceeds;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    // Getters and setters
}

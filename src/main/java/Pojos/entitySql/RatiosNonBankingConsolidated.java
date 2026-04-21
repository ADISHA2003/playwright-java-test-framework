package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "ratios_non_banking_consolidated")
public class RatiosNonBankingConsolidated {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "company_code")
    private Double companyCode;

    @Column(name = "year_ending", length = 20)
    private String yearEnding;

    @Column(name = "months")
    private Integer months;

    @Column(name = "type", length = 5)
    private String type;

    @Column(name = "face_value")
    private Double faceValue;

    @Column(name = "basic_epc")
    private Double basicEpc;

    @Column(name = "diluted_epc")
    private Double dilutedEpc;

    @Column(name = "cash_epc")
    private Double cashEpc;

    @Column(name = "bv_per_share_excl_reval_reserve")
    private Double bvPerShareExclRevalReserve;

    @Column(name = "bv_per_share_incl_reval_reserve")
    private Double bvPerShareInclRevalReserve;

    @Column(name = "operating_revenue_per_share")
    private Double operatingRevenuePerShare;

    @Column(name = "pbdit_per_share")
    private Double pbditPerShare;

    @Column(name = "pbit_per_share")
    private Double pbitPerShare;

    @Column(name = "pbt_per_share")
    private Double pbtPerShare;

    @Column(name = "np_per_share")
    private Double npPerShare;

    @Column(name = "np_after_mi_per_share")
    private Double npAfterMiPerShare;

    @Column(name = "pbdit_margin")
    private Double pbditMargin;

    @Column(name = "pbit_margin")
    private Double pbitMargin;

    @Column(name = "pbt_margin")
    private Double pbtMargin;

    @Column(name = "np_margin")
    private Double npMargin;

    @Column(name = "np_after_mi_margin")
    private Double npAfterMiMargin;

    @Column(name = "ronw")
    private Double ronw;

    @Column(name = "roce")
    private Double roce;

    @Column(name = "return_on_assets")
    private Double returnOnAssets;

    @Column(name = "long_term_debt_equity")
    private Double longTermDebtEquity;

    @Column(name = "debt_equity")
    private Double debtEquity;

    @Column(name = "asset_turn_over")
    private Double assetTurnOver;

    @Column(name = "current_ratio")
    private Double currentRatio;

    @Column(name = "quick_ratio")
    private Double quickRatio;

    @Column(name = "inventory_ternover_ratio")
    private Double inventoryTernoverRatio;

    @Column(name = "dividend_payout_np")
    private Double dividendPayoutNp;

    @Column(name = "dividend_payout_cp")
    private Double dividendPayoutCp;

    @Column(name = "earning_retention")
    private Double earningRetention;

    @Column(name = "cash_earning_retention")
    private Double cashEarningRetention;

    @Column(name = "interest_coverage")
    private Double interestCoverage;

    @Column(name = "interest_coverage_post_tax")
    private Double interestCoveragePostTax;

    @Column(name = "enterprise_value")
    private Double enterpriseValue;

    @Column(name = "ev_per_net_sale")
    private Double evPerNetSale;

    @Column(name = "ev_per_ebitda")
    private Double evPerEbitda;

    @Column(name = "market_cap_per_sales")
    private Double marketCapPerSales;

    @Column(name = "retention_ratio")
    private Double retentionRatio;

    @Column(name = "price_to_bv")
    private Double priceToBv;

    @Column(name = "price_to_sale")
    private Double priceToSale;

    @Column(name = "earnings_yield")
    private Double earningsYield;

    @Column(name = "modified_date", length = 20)
    private String modifiedDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

}

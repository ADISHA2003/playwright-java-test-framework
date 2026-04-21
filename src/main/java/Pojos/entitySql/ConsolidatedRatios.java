package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "consolidated_ratios")
public class ConsolidatedRatios {

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

    @Column(name = "adjusted_eps")
    private Double adjustedEps;

    @Column(name = "adjusted_cash_eps")
    private Double adjustedCashEps;

    @Column(name = "return_on_assets_excl")
    private Double returnOnAssetsExcl;

    @Column(name = "return_on_assets_incl")
    private Double returnOnAssetsIncl;

    @Column(name = "devidend_per_share")
    private Double devidendPerShare;

    @Column(name = "reported_eps")
    private Double reportedEps;

    @Column(name = "reported_cash_eps")
    private Double reportedCashEps;

    @Column(name = "on_profit_per_share")
    private Double onProfitPerShare;

    @Column(name = "net_operating_income_per_share")
    private Double netOperatingIncomePerShare;

    @Column(name = "free_reserves_per_share")
    private Double freeReservesPerShare;

    @Column(name = "operating_margin")
    private Double operatingMargin;

    @Column(name = "reported_return_on_net_worth")
    private Double reportedReturnOnNetWorth;

    @Column(name = "adjusted_return_on_net_worth")
    private Double adjustedReturnOnNetWorth;

    @Column(name = "adjusted_cash_margin")
    private Double adjustedCashMargin;

    @Column(name = "return_on_long_term_fund")
    private Double returnOnLongTermFund;

    @Column(name = "current_ratio")
    private Double currentRatio;

    @Column(name = "quick_ratio")
    private Double quickRatio;

    @Column(name = "average_raw_mat_holding")
    private Double averageRawMatHolding;

    @Column(name = "average_finished_goods_hold")
    private Double averageFinishedGoodsHold;

    @Column(name = "no_of_daysin_working_cap")
    private Double noOfDaysInWorkingCap;

    @Column(name = "long_term_debt_equity")
    private Double longTermDebtEquity;

    @Column(name = "owners_fund")
    private Double ownersFund;

    @Column(name = "total_debt_to_owners_fund")
    private Double totalDebtToOwnersFund;

    @Column(name = "current_ratio_incl_short_term_loans")
    private Double currentRatioInclShortTermLoans;

    @Column(name = "asset_turn_over_ratio")
    private Double assetTurnOverRatio;

    @Column(name = "long_term_assets")
    private Double longTermAssets;

    @Column(name = "financial_charges_coverage_ratio")
    private Double financialChargesCoverageRatio;

    @Column(name = "financial_charges_coverage_ratio_post_tax")
    private Double financialChargesCoverageRatioPostTax;

    @Column(name = "devidend_payout_ratio_np")
    private Double devidendPayoutRatioNp;

    @Column(name = "devidend_payout_ratio_cp")
    private Double devidendPayoutRatioCp;

    @Column(name = "earning_retention_ratio")
    private Double earningRetentionRatio;

    @Column(name = "cash_earning_retention_ratio")
    private Double cashEarningRetentionRatio;

    @Column(name = "material_cost_composition")
    private Double materialCostComposition;

    @Column(name = "shell_distribut_cost_composition")
    private Double shellDistributCostComposition;

    @Column(name = "investment_turn_ratio")
    private Double investmentTurnRatio;

    @Column(name = "adjusted_cash_flow_times")
    private Double adjustedCashFlowTimes;

    @Column(name = "imported_comp_of_raw_material_consumed")
    private Double importedCompOfRawMaterialConsumed;

    @Column(name = "exp_as_total_sales")
    private Double expAsTotalSales;

    @Column(name = "interest_spread")
    private Double interestSpread;

    @Column(name = "operating_exp_by_op_income")
    private Double operatingExpByOpIncome;

    @Column(name = "np_by_operating_income")
    private Double npByOperatingIncome;

    @Column(name = "np_by_capital_employed")
    private Double npByCapitalEmployed;

    @Column(name = "average_cost_of_funds")
    private Double averageCostOfFunds;

    @Column(name = "average_yield_on_assets")
    private Double averageYieldOnAssets;

    @Column(name = "average_return_on_investments")
    private Double averageReturnOnInvestments;

    @Column(name = "gross_profit_margin")
    private Double grossProfitMargin;

    @Column(name = "net_profit_margin")
    private Double netProfitMargin;

    @Column(name = "bonus_in_equity_capital")
    private Double bonusInEquityCapital;

    @Column(name = "capital_adequacy_ratio")
    private Double capitalAdequacyRatio;

    @Column(name = "modified_date", length = 20)
    private String modifiedDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

}

package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "ratios_banking_standalone")
public class RatiosBankingStandalone {

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

    @Column(name = "basic_eps")
    private Double basicEps;

    @Column(name = "diluted_eps")
    private Double dilutedEps;

    @Column(name = "cash_eps")
    private Double cashEps;

    @Column(name = "bv_per_share_excl_reval_reserve")
    private Double bvPerShareExclRevalReserve;

    @Column(name = "bv_per_share_incl_reval_reserve")
    private Double bvPerShareInclRevalReserve;

    @Column(name = "devidend_per_share")
    private Double devidendPerShare;

    @Column(name = "operating_revenue_per_share")
    private Double operatingRevenuePerShare;

    @Column(name = "np_per_share")
    private Double npPerShare;

    @Column(name = "interest_income_per_employee")
    private Double interestIncomePerEmployee;

    @Column(name = "np_per_employee")
    private Double npPerEmployee;

    @Column(name = "business_per_employee")
    private Double businessPerEmployee;

    @Column(name = "interest_income_per_branch")
    private Double interestIncomePerBranch;

    @Column(name = "np_per_branches")
    private Double npPerBranches;

    @Column(name = "business_per_branches")
    private Double businessPerBranches;

    @Column(name = "npm")
    private Double npm;

    @Column(name = "opm")
    private Double opm;

    @Column(name = "roa")
    private Double roa;

    @Column(name = "roe")
    private Double roe;

    @Column(name = "nim")
    private Double nim;

    @Column(name = "cost_to_income")
    private Double costToIncome;

    @Column(name = "interest_income_by_earning_assets")
    private Double interestIncomeByEarningAssets;

    @Column(name = "non_interest_income_by_earning_assets")
    private Double nonInterestIncomeByEarningAssets;

    @Column(name = "operating_profit_by_earning_assets")
    private Double operatingProfitByEarningAssets;

    @Column(name = "operating_expenses_by_earning_assets")
    private Double operatingExpensesByEarningAssets;

    @Column(name = "interest_expenses_by_earning_assets")
    private Double interestExpensesByEarningAssets;

    @Column(name = "enterprice_values")
    private Double enterpriceValues;

    @Column(name = "ev_per_net_sale")
    private Double evPerNetSale;

    @Column(name = "price_to_bv")
    private Double priceToBv;

    @Column(name = "price_to_sales")
    private Double priceToSales;

    @Column(name = "retention_ratio")
    private Double retentionRatio;

    @Column(name = "earnings_yield")
    private Double earningsYield;

    @Column(name = "modified_date", length = 20)
    private String modifiedDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

}


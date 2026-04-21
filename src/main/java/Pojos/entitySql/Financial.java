package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "financial")
public class Financial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "vcc_company_id")
    private Integer CompanyId;

    @Column(name = "company_code", nullable = false)
    private Double companyCode;

    @Column(name = "year1")
    private Integer year1;

    @Column(name = "year_ending", nullable = false)
    private String yearEnding;

    @Column(name = "months", nullable = false)
    private byte months;

    @Column(name = "operating_income", nullable = false)
    private Double operatingIncome;

    @Column(name = "total_income")
    private Double totalIncome;

    @Column(name = "cost_of_sales")
    private Double costOfSales;

    @Column(name = "adjusted_pbdit", nullable = false)
    private Double adjustedPbdit;

    @Column(name = "ebit")
    private Double ebit;

    @Column(name = "adjusted_pbt", nullable = false)
    private Double adjustedPbt;

    @Column(name = "adjusted_pat", nullable = false)
    private Double adjustedPat;

    @Column(name = "equity_capital", nullable = false)
    private Double equityCapital;

    @Column(name = "res_and_surplus", nullable = false)
    private Double resAndSurplus;

    @Column(name = "net_worth")
    private Double netWorth;

    @Column(name = "total_debt")
    private Double totalDebt;

    @Column(name = "gross_block", nullable = false)
    private Double grossBlock;

    @Column(name = "depreciation")
    private Double depreciation;

    @Column(name = "inventories", nullable = false)
    private Double inventories;

    @Column(name = "current_assets", nullable = false)
    private Double currentAssets;

    @Column(name = "investments", nullable = false)
    private Double investments;

    @Column(name = "cash_and_bank_balance", nullable = false)
    private Double cashAndBankBalance;

    @Column(name = "net_current_assets", nullable = false)
    private Double netCurrentAssets;

    @Column(name = "total_current_liabilities", nullable = false)
    private Double totalCurrentLiabilities;

    @Column(name = "total_current_assets", nullable = false)
    private Double totalCurrentAssets;

    @Column(name = "secured_loans", nullable = false)
    private Double securedLoans;

    @Column(name = "unsecured_loans", nullable = false)
    private Double unsecuredLoans;

    @Column(name = "net_block", nullable = false)
    private Double netBlock;

    @Column(name = "TotalAssets")
    private Double totalAssets;

    @Column(name = "ebita_margin")
    private Double ebitaMargin;

    @Column(name = "closingprice")
    private Double closingPrice;

    @Column(name = "Market_Capitalization")
    private Double marketCapitalization;

    @Column(name = "equitydividend")
    private Double equityDividend;

    @Column(name = "EnterpriseValue")
    private Double enterpriseValue;

    @Column(name = "cfyearending")
    private String cfYearEnding;

    @Column(name = "net_c_flow_op")
    private Double netCFlowOp;

    @Column(name = "nt_csh_in_i_a")
    private Double ntCshInIA;

    @Column(name = "nt_csh_usd_f_a")
    private Double ntCshUsdFA;

    @Column(name = "ryearending")
    private String rYearEnding;

    @Column(name = "total_debt_to_ownersfund")
    private Double totalDebtToOwnersFund;

    @Column(name = "current_ratio")
    private Double currentRatio;

    @Column(name = "np_by_capital_employed")
    private Double npByCapitalEmployed;

    @Column(name = "reported_return_on_net_worth")
    private Double reportedReturnOnNetWorth;

    @Column(name = "net_profit_margin")
    private Double netProfitMargin;

    @Column(name = "dividend_payout_ratio_cp")
    private Double dividendPayoutRatioCp;

    @Column(name = "reported_eps")
    private Double reportedEps;

    @Column(name = "price_to_book_value")
    private Double priceToBookValue;

    @Column(name = "reported_cash_eps")
    private Double reportedCashEps;

    @Column(name = "div_yield_per")
    private Double divYieldPer;

    @Column(name = "operating_margin", nullable = false)
    private Double operatingMargin;

    @Column(name = "return_on_assets", nullable = false)
    private Double returnOnAssets;

    @Column(name = "cash_ratio", nullable = false)
    private Double cashRatio;

    @Column(name = "inventory_turnover_ratio", nullable = false)
    private Double inventoryTurnoverRatio;

    @Column(name = "working_capital_turnover_ratio", nullable = false)
    private Double workingCapitalTurnoverRatio;

    @Column(name = "debt_to_asset_ratio", nullable = false)
    private Double debtToAssetRatio;

    @Column(name = "quick_ratio", nullable = false)
    private Double quickRatio;

    @Column(name = "financial_type", nullable = false, length = 155)
    private String financialType;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private String updatedAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

   

}

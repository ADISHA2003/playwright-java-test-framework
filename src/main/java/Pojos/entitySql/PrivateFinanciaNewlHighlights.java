package Pojos.entitySql;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "private_financial_highlights")
public class PrivateFinanciaNewlHighlights {

    @Id
    @Column(name = "companyid")
    private Integer companyId;

    @Column(name = "updated_on")
    private Integer updatedOn;

    @Column(name = "from_date")
    private Integer fromDate;

    @Column(name = "to_date")
    private Integer toDate;

    @Column(name = "reporting_standard")
    private String reportingStandard;

    @Column(name = "conversion_factor")
    private String conversionFactor;

    @Column(name = "yearend")
    private String yearEnd;

    @Column(name = "financial_format")
    private String financialFormat;

    @Column(name = "net_sales")
    private double netSales;

    @Column(name = "other_income")
    private double otherIncome;

    @Column(name = "total_income")
    private double totalIncome;

    @Column(name = "total_expenditure_pl")
    private double totalExpenditurePL;

    @Column(name = "depreciation")
    private double depreciation;

    @Column(name = "interest")
    private double interest;

    @Column(name = "total_expenditure")
    private double totalExpenditure;

    @Column(name = "ebitda")
    private double ebitda;

    @Column(name = "ebit")
    private double ebit;

    @Column(name = "pbt")
    private double pbt;

    @Column(name = "tax")
    private double tax;

    @Column(name = "pat")
    private double pat;

    @Column(name = "equity_paid_up")
    private double equityPaidUp;

    @Column(name = "reserves_and_surplus_reported")
    private double reservesAndSurplusReported;

    @Column(name = "share_appl_pending_allot")
    private double shareApplPendingAllot;

    @Column(name = "reserves_surplus")
    private double reservesSurplus;

    @Column(name = "net_worth")
    private double netWorth;

    @Column(name = "unsecured_loan")
    private double unsecuredLoan;

    @Column(name = "secured_loan")
    private double securedLoan;

    @Column(name = "total_debt")
    private double totalDebt;

    @Column(name = "gross_block")
    private double grossBlock;

    @Column(name = "net_block")
    private double netBlock;

    @Column(name = "def_tax_liabilities")
    private double defTaxLiabilities;

    @Column(name = "capital_wip")
    private double capitalWip;

    @Column(name = "investments")
    private double investments;

    @Column(name = "cash_bank_balance")
    private double cashBankBalance;

    @Column(name = "inventories")
    private double inventories;

    @Column(name = "sundry_debtors")
    private double sundryDebtors;

    @Column(name = "other_current_assets")
    private double otherCurrentAssets;

    @Column(name = "loans_and_advances")
    private double loansAndAdvances;

    @Column(name = "total_current_assets")
    private double totalCurrentAssets;

    @Column(name = "current_liabilities")
    private double currentLiabilities;

    @Column(name = "provisions")
    private double provisions;

    @Column(name = "net_current_assets")
    private double netCurrentAssets;

    @Column(name = "total_current_liabilities")
    private double totalCurrentLiabilities;

    @Column(name = "def_tax_assets")
    private double defTaxAssets;

    @Column(name = "total_assets")
    private double totalAssets;

    @Column(name = "cash_flow_operations")
    private double cashFlowOperations;

    @Column(name = "cash_flow_investing")
    private double cashFlowInvesting;

    @Column(name = "cash_flow_financing")
    private double cashFlowFinancing;

    @Column(name = "free_cash_flow")
    private double freeCashFlow;

    @Column(name = "minority_interest")
    private double minorityInterest;

    @Column(name = "total_preferred")
    private double totalPreferred;

    @Column(name = "shares_os")
    private double sharesOs;

    @Column(name = "shares_os_fully_diluted")
    private double sharesOsFullyDiluted;

    @Column(name = "eps_basic")
    private double epsBasic;

    @Column(name = "eps_diluted")
    private double epsDiluted;

    @Column(name = "dividend")
    private double dividend;

    @Column(name = "debt_equity")
    private double debtEquity;

    @Column(name = "current_ratio")
    private double currentRatio;

    @Column(name = "roce")
    private double roce;

    @Column(name = "ronw")
    private double ronw;

    @Column(name = "ebidta_margin")
    private double ebidtaMargin;

    @Column(name = "pat_margin")
    private double patMargin;

    @Column(name = "cpm")
    private double cpm;

    @Column(name = "acid_ratio")
    private double acidRatio;

    @Column(name = "debt_to_asset_ratio")
    private double debtToAssetRatio;

    @Column(name = "asset_turnover_ratio")
    private double assetTurnoverRatio;

    @Column(name = "cash_flow_coverage_ratio")
    private double cashFlowCoverageRatio;

    @Column(name = "operation_cash_flow_margin")
    private double operationCashFlowMargin;

    @Column(name = "cash_ratio")
    private double cashRatio;

    @Column(name = "inventory_turnover_ratio")
    private double inventoryTurnoverRatio;

    @Column(name = "working_capital_turnover_ratio")
    private double workingCapitalTurnoverRatio;



    @Column(name = "return_on_assets_ratio", nullable = false)
    private double returnOnAssetsRatio;

    @Column(name = "operating_profit_margin", nullable = false)
    private double operatingProfitMargin;

    @Column(name = "cost_of_material_consumed", nullable = false)
    private double costOfMaterialConsumed;

    @Column(name = "purchase_of_stock_in_trade", nullable = false)
    private double purchaseOfStockInTrade;

    @Column(name = "change_in_inventories_of_finished_goods", nullable = false)
    private double changeInInventoriesOfFinishedGoods;

    @Column(name = "change_in_inventories_of_work_in_progress", nullable = false)
    private double changeInInventoriesOfWorkInProgress;

    @Column(name = "change_in_inventories_of_stock_in_trade", nullable = false)
    private double changeInInventoriesOfStockInTrade;

    @Column(name = "employee_benefit_expenses", nullable = false)
    private double employeeBenefitExpenses;

    @Column(name = "managerial_remuneration", nullable = false)
    private double managerialRemuneration;

    @Column(name = "payment_to_auditors", nullable = false)
    private double paymentToAuditors;

    @Column(name = "insurance_expenses", nullable = false)
    private double insuranceExpenses;

    @Column(name = "power_and_fuel", nullable = false)
    private double powerAndFuel;

    @Column(name = "other_expenses", nullable = false)
    private double otherExpenses;

    @Column(name = "csr_expenditure", nullable = false)
    private double csrExpenditure;

    @Column(name = "pfh_discription", nullable = false, columnDefinition = "TEXT")
    private String pfhDescription;

    @Column(name = "current_investments", nullable = false)
    private double currentInvestments;

    @Column(name = "non_current_investments", nullable = false)
    private double nonCurrentInvestments;

    @Column(name = "short_term_borrowings", nullable = false)
    private double shortTermBorrowings;

    @Column(name = "sundry_creditors", nullable = false)
    private double sundryCreditors;

    @Column(name = "other_current_liablities", nullable = false)
    private double otherCurrentLiabilities;

    @Column(name = "accumulated_depreciation", nullable = false)
    private double accumulatedDepreciation;

    @Column(name = "other_soffunds", nullable = false)
    private double otherSoffunds;

    @Column(name = "other_soffunds_name", nullable = false, columnDefinition = "TEXT")
    private String otherSoffundsName;

    @Column(name = "total_soffunds", nullable = false)
    private double totalSoffunds;

    @Column(name = "misc_expenditure", nullable = false)
    private double miscExpenditure;

    @Column(name = "profit_loss_account", nullable = false)
    private double profitLossAccount;

    @Column(name = "others_aoffunds_name", nullable = false, columnDefinition = "TEXT")
    private String othersAoffundsName;

    @Column(name = "others_ooffunds", nullable = false)
    private double othersOoffunds;

    @Column(name = "total_fixed_assets", nullable = false)
    private double totalFixedAssets;

    @Column(name = "financial_type", nullable = false, length = 255)
    private String financialType;

    @Column(name = "money_received_against_share_warrants", nullable = false)
    private double moneyReceivedAgainstShareWarrants;

    @Column(name = "other_long_term_liabilities", nullable = false)
    private double otherLongTermLiabilities;

    @Column(name = "long_term_provisions", nullable = false)
    private double longTermProvisions;

    @Column(name = "tangible_assets", nullable = false)
    private double tangibleAssets;

    @Column(name = "intangible_assets", nullable = false)
    private double intangibleAssets;

    @Column(name = "long_term_loans_and_advances", nullable = false)
    private double longTermLoansAndAdvances;

    @Column(name = "other_non_current_assets", nullable = false)
    private double otherNonCurrentAssets;

    @Column(name = "long_term_borrowings", nullable = false)
    private double longTermBorrowings;

    @Column(name = "priority_check")
    private Integer priorityCheck;

    @Column(name = "exceptional_items", nullable = false)
    private double exceptionalItems;

    @Column(name = "prior_period_items", nullable = false)
    private double priorPeriodItems;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;




}
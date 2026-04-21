package Pojos.entitySql;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "private_financials_banking")
public class PrivateFinancialsBanking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "companyid")
    private Integer companyId;

    @Column(name = "yearend")
    private String yearEnd;

    @Column(name = "financial_type")
    private String financialType;

    @Column(name = "conversion_factor")
    private Float conversionFactor;

    @Column(name = "financial_format")
    private String financialFormat;

    @Column(name = "updated_on")
    private Integer updatedOn;

    @Column(name = "from_date")
    private Integer fromDate;

    @Column(name = "to_date")
    private Integer toDate;

    @Column(name = "reporting_standard")
    private String reportingStandard;

    @Column(name = "interest_earned")
    private Double interestEarned;

    @Column(name = "other_income")
    private Double otherIncome;

    @Column(name = "total_income")
    private Double totalIncome;

    @Column(name = "operating_expenses")
    private Double operatingExpenses;

    @Column(name = "net_operating_expenses")
    private Double netOperatingExpenses;

    @Column(name = "provisions_and_contingencies")
    private Double provisionsAndContingencies;

    @Column(name = "total_expenditure")
    private Double totalExpenditure;

    @Column(name = "ebitda")
    private Double ebitda;

    @Column(name = "depriciation")
    private Double depreciation;

    @Column(name = "ebit")
    private Double ebit;

    @Column(name = "interest_expended")
    private Double interestExpended;

    @Column(name = "pbt")
    private Double pbt;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "prior_period_items")
    private Double priorPeriodItems;

    @Column(name = "extra_ordinary_items")
    private Double extraOrdinaryItems;

    @Column(name = "others")
    private Double others;

    @Column(name = "net_profit_for_the_year")
    private Double netProfitForTheYear;

    @Column(name = "profit_loss_brought_forward")
    private Double profitLossBroughtForward;

    @Column(name = "total_profit_loss")
    private Double totalProfitLoss;

    @Column(name = "transfer_to_statutory_reserve")
    private Double transferToStatutoryReserve;

    @Column(name = "transfer_to_capital_reserve")
    private Double transferToCapitalReserve;

    @Column(name = "transfer_to_general_reserve")
    private Double transferToGeneralReserve;

    @Column(name = "dividend_paid")
    private Double dividendPaid;

    @Column(name = "others_2")
    private Double others2;

    @Column(name = "balance_in_profit_loss")
    private Double balanceInProfitLoss;

    @Column(name = "total_appropriations")
    private Double totalAppropriations;

    @Column(name = "equity_capital")
    private Double equityCapital;

    @Column(name = "share_capital_deposit")
    private Double shareCapitalDeposit;

    @Column(name = "employee_stock_options")
    private Double employeeStockOptions;

    @Column(name = "reserves_surplus")
    private Double reservesSurplus;

    @Column(name = "share_appl_pending_allot")
    private Double shareApplPendingAllot;

    @Column(name = "total_shareholders_funds")
    private Double totalShareholdersFunds;

    @Column(name = "deposits")
    private Double deposits;

    @Column(name = "borrowings")
    private Double borrowings;

    @Column(name = "total_debt")
    private Double totalDebt;

    @Column(name = "other_liabilities_and_provisions")
    private Double otherLiabilitiesAndProvisions;

    @Column(name = "total_liabilities")
    private Double totalLiabilities;

    @Column(name = "total_capital_liabilities")
    private Double totalCapitalLiabilities;

    @Column(name = "cash_and_balances_with_reserve_bank_of_india")
    private Double cashAndBalancesWithReserveBankOfIndia;

    @Column(name = "balances_with_banks_and_money")
    private Double balancesWithBanksAndMoney;

    @Column(name = "investments")
    private Double investments;

    @Column(name = "advances")
    private Double advances;

    @Column(name = "fixed_assets")
    private Double fixedAssets;

    @Column(name = "other_assets")
    private Double otherAssets;

    @Column(name = "total_assets")
    private Double totalAssets;

    @Column(name = "contingent_liabilities")
    private Double contingentLiabilities;

    @Column(name = "bills_for_collection")
    private Double billsForCollection;

    @Column(name = "cash_flow_operations")
    private Double cashFlowOperations;

    @Column(name = "cash_flow_investing")
    private Double cashFlowInvesting;

    @Column(name = "cash_flow_financing")
    private Double cashFlowFinancing;

    @Column(name = "free_cash_flow")
    private Double freeCashFlow;

    @Column(name = "total_preferred")
    private Double totalPreferred;

    @Column(name = "shares_os")
    private Double sharesOs;

    @Column(name = "shares_os_fully_diluted")
    private Double sharesOsFullyDiluted;

    @Column(name = "eps_basic")
    private Double epsBasic;

    @Column(name = "eps_diluted")
    private Double epsDiluted;

    @Column(name = "dividend")
    private Double dividend;

    @Column(name = "debt_equity")
    private Double debtEquity;

    @Column(name = "current_ratio")
    private Double currentRatio;

    @Column(name = "roce")
    private Double roce;

    @Column(name = "ronw")
    private Double ronw;

    @Column(name = "ebidta_margin")
    private Double ebidtaMargin;

    @Column(name = "pat_margin")
    private Double patMargin;

    @Column(name = "cpm")
    private Double cpm;

    @Column(name = "priority_check")
    private Integer priorityCheck;

    @Column(name = "pfh_discription", columnDefinition = "TEXT")
    private String pfhDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    // Constructors, getters, and setters
}
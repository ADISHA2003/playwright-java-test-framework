package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "finance_banking_consolidated")
public class FinanceBankingConsolidated {

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

    @Column(name = "interest_discount_on_advances_bills")
    private Double interestDiscountOnAdvancesBills;

    @Column(name = "income_from_investments")
    private Double incomeFromInvestments;

    @Column(name = "interest_on_balance_with_rbi_and_other_inter_bank_funds")
    private Double interestOnBalanceWithRBIAndOtherInterBankFunds;

    @Column(name = "others")
    private Double others;

    @Column(name = "total_interest_earned")
    private Double totalInterestEarned;

    @Column(name = "other_income")
    private Double otherIncome;

    @Column(name = "total_income")
    private Double totalIncome;

    @Column(name = "interest_expended")
    private Double interestExpended;

    @Column(name = "payments_to_and_provisions_for_employees")
    private Double paymentsToAndProvisionsForEmployees;

    @Column(name = "depreciation")
    private Double depreciation;

    @Column(name = "depreciation_on_leased_assets")
    private Double depreciationOnLeasedAssets;

    @Column(name = "operating_expenses_excluded_employee_cost_and_depreciation")
    private Double operatingExpensesExcludedEmployeeCostAndDepreciation;

    @Column(name = "total_operating_expenses")
    private Double totalOperatingExpenses;

    @Column(name = "provision_towards_income_tax")
    private Double provisionTowardsIncomeTax;

    @Column(name = "provision_towards_deferred_tax")
    private Double provisionTowardsDeferredTax;

    @Column(name = "provision_towards_other_taxes")
    private Double provisionTowardsOtherTaxes;

    @Column(name = "other_provision_and_contingencies")
    private Double otherProvisionAndContingencies;

    @Column(name = "total_provision_and_contingencies")
    private Double totalProvisionAndContingencies;

    @Column(name = "total_expenditure")
    private Double totalExpenditure;

    @Column(name = "net_profit_loss_for_the_year")
    private Double netProfitLossForTheYear;

    @Column(name = "prior_period_items")
    private Double priorPeriodItems;

    @Column(name = "extra_ordinary_items")
    private Double extraOrdinaryItems;

    @Column(name = "net_profit_loss_for_the_year_after_ei")
    private Double netProfitLossForTheYearAfterEI;

    @Column(name = "income_minority_interest")
    private Double incomeMinorityInterest;

    @Column(name = "share_of_profit_loss_from_associate")
    private Double shareOfProfitLossFromAssociate;

    @Column(name = "profit_loss_after_mi_and_associate")
    private Double profitLossAfterMiAndAssociate;

    @Column(name = "profit_loss_brought_forward")
    private Double profitLossBroughtForward;

    @Column(name = "transferred_on_amalgamation")
    private Double transferredOnAmalgamation;

    @Column(name = "total_profit_loss")
    private Double totalProfitLoss;

    @Column(name = "transfer_to_from_statutory_reserve")
    private Double transferToFromStatutoryReserve;

    @Column(name = "transfer_to_from_reserve_fund")
    private Double transferToFromReserveFund;

    @Column(name = "transfer_to_from_special_reserve")
    private Double transferToFromSpecialReserve;

    @Column(name = "transfer_to_from_capital_reserve")
    private Double transferToFromCapitalReserve;

    @Column(name = "transfer_to_from_general_reserve")
    private Double transferToFromGeneralReserve;

    @Column(name = "transfer_to_from_investment_reserve")
    private Double transferToFromInvestmentReserve;

    @Column(name = "transfer_to_from_revenue_and_other_reserves")
    private Double transferToFromRevenueAndOtherReserves;

    @Column(name = "devidend_for_the_previous_year")
    private Double devidendForThePreviousYear;

    @Column(name = "equety_share_devidend")
    private Double equetyShareDevidend;

    @Column(name = "preference_share_devidend")
    private Double preferenceShareDevidend;

    @Column(name = "tax_on_devidend")
    private Double taxOnDevidend;

    @Column(name = "balance_carried_over_to_balance_sheet")
    private Double balanceCarriedOverToBalanceSheet;

    @Column(name = "total_appropriations")
    private Double totalAppropriations;

    @Column(name = "equity_shares")
    private Double equityShares;

    @Column(name = "equity_capital")
    private Double equityCapital;

    @Column(name = "preference_capital")
    private Double preferenceCapital;

    @Column(name = "total_share_capital")
    private Double totalShareCapital;

    @Column(name = "revaluation_reserves")
    private Double revaluationReserves;

    @Column(name = "reserves_and_surplus")
    private Double reservesAndSurplus;

    @Column(name = "total_reserves_and_surplus")
    private Double totalReservesAndSurplus;

    @Column(name = "money_against_share_warrants")
    private Double moneyAgainstShareWarrants;

    @Column(name = "employee_stock_options")
    private Double employeeStockOptions;

    @Column(name = "total_share_holder_funds")
    private Double totalShareHolderFunds;

    @Column(name = "equity_share_application_money")
    private Double equityShareApplicationMoney;

    @Column(name = "pref_share_application_money")
    private Double prefShareApplicationMoney;

    @Column(name = "share_capital_suspense")
    private Double shareCapitalSuspense;

    @Column(name = "liability_minority_interest")
    private Double liabilityMinorityInterest;

    @Column(name = "policy_holders_funds")
    private Double policyHoldersFunds;

    @Column(name = "liability_group_share_in_joint_ventures")
    private Double liabilityGroupShareInJointVentures;

    @Column(name = "deposits")
    private Double deposits;

    @Column(name = "borrowings")
    private Double borrowings;

    @Column(name = "other_liabilities_and_provisions")
    private Double otherLiabilitiesAndProvisions;

    @Column(name = "total_capital_and_liabilities")
    private Double totalCapitalAndLiabilities;

    @Column(name = "cash_and_balance_with_reserve_bank_of_india")
    private Double cashAndBalanceWithReserveBankOfIndia;

    @Column(name = "balance_with_banks_money_at_call_and_short_notice")
    private Double balanceWithBanksMoneyAtCallAndShortNotice;

    @Column(name = "investments")
    private Double investments;

    @Column(name = "advances")
    private Double advances;

    @Column(name = "fixed_assets")
    private Double fixedAssets;

    @Column(name = "other_assets")
    private Double otherAssets;

    @Column(name = "asset_minority_interest")
    private Double assetMinorityInterest;

    @Column(name = "asset_group_share_in_joint_ventures")
    private Double assetGroupShareInJointVentures;

    @Column(name = "total_assets")
    private Double totalAssets;

    @Column(name = "net_profit_loss_before_extraordinary_items_and_tax")
    private Double netProfitLossBeforeExtraordinaryItemsAndTax;

    @Column(name = "net_cash_flow_from_operating_activities")
    private Double netCashFlowFromOperatingActivities;

    @Column(name = "net_cash_used_in_investment_activities")
    private Double netCashUsedInInvestmentActivities;

    @Column(name = "net_cash_used_from_financing_activities")
    private Double netCashUsedFromFinancingActivities;

    @Column(name = "foreign_exchange_gains_losses")
    private Double foreignExchangeGainsLosses;

    @Column(name = "net_inc_dec_in_cash_and_cash_equivalents")
    private Double netIncDecInCashAndCashEquivalents;

    @Column(name = "cash_and_cash_equivalent_begin_of_year")
    private Double cashAndCashEquivalentBeginOfYear;

    @Column(name = "cash_and_cash_equivalent_end_of_year")
    private Double cashAndCashEquivalentEndOfYear;

    @Column(name = "basic_eps")
    private Double basicEps;

    @Column(name = "diluted_eps")
    private Double dilutedEps;

    @Column(name = "number_of_branches")
    private Double numberOfBranches;

    @Column(name = "number_of_employees")
    private Double numberOfEmployees;

    @Column(name = "capital_adequecy_ratios")
    private Double capitalAdequacyRatios;

    @Column(name = "key_performance_tier1")
    private Double keyPerformanceTier1;

    @Column(name = "key_performance_tier2")
    private Double keyPerformanceTier2;

    @Column(name = "gross_npars")
    private Double grossNpars;

    @Column(name = "gross_npa_percentage")
    private Double grossNpaPercentage;

    @Column(name = "net_npars")
    private Double netNpars;

    @Column(name = "net_npa_percentage")
    private Double netNpaPercentage;

    @Column(name = "net_npa_to_advances_percentage")
    private Double netNpaToAdvancesPercentage;

    @Column(name = "bills_for_collection")
    private Double billsForCollection;

    @Column(name = "contingent_liabilities")
    private Double contingentLiabilities;

    @Column(name = "bonus_equity_share_capital")
    private Double bonusEquityShareCapital;

    @Column(name = "modified_date", length = 20)
    private String modifiedDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

}


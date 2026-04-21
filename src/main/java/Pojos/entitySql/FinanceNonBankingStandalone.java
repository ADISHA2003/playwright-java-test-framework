package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "finance_non_banking_standalone")
public class FinanceNonBankingStandalone {

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

    @Column(name = "revenue_from_operations_gross")
    private Double revenueFromOperationsGross;

    @Column(name = "less_excise_service_tax_other_levies")
    private Double lessExciseServiceTaxOtherLevies;

    @Column(name = "revenue_from_operations_net")
    private Double revenueFromOperationsNet;

    @Column(name = "other_operating_revenues")
    private Double otherOperatingRevenues;

    @Column(name = "total_operating_revenues")
    private Double totalOperatingRevenues;

    @Column(name = "other_income")
    private Double otherIncome;

    @Column(name = "total_revenue")
    private Double totalRevenue;

    @Column(name = "cost_of_materials_consumed")
    private Double costOfMaterialsConsumed;

    @Column(name = "purchase_of_stock_in_trade")
    private Double purchaseOfStockInTrade;

    @Column(name = "purchase_of_crude_oil_and_others")
    private Double purchaseOfCrudeOilAndOthers;

    @Column(name = "cost_of_power_purchased")
    private Double costOfPowerPurchased;

    @Column(name = "cost_of_fuel")
    private Double costOfFuel;

    @Column(name = "aircraft_fuel_expenses")
    private Double aircraftFuelExpenses;

    @Column(name = "aircraft_lease_rentals")
    private Double aircraftLeaseRentals;

    @Column(name = "operating_and_direct_expenses")
    private Double operatingAndDirectExpenses;

    @Column(name = "changes_in_inventories_of_fgwip_and_stock_in_trade")
    private Double changesInInventoriesOfFGWIPAndStockInTrade;

    @Column(name = "employee_benefit_expenses")
    private Double employeeBenefitExpenses;

    @Column(name = "finance_costs")
    private Double financeCosts;

    @Column(name = "provisions_and_contingencies")
    private Double provisionsAndContingencies;

    @Column(name = "depreciations_and_amortisation_expenses")
    private Double depreciationsAndAmortisationExpenses;

    @Column(name = "misc_expenses_woff")
    private Double miscExpensesWoff;

    @Column(name = "other_expenses")
    private Double otherExpenses;

    @Column(name = "less_inter_unit_segment_division_transfer")
    private Double lessInterUnitSegmentDivisionTransfer;

    @Column(name = "less_transfer_to_from_investment_fixed_assets_others")
    private Double lessTransferToFromInvestmentFixedAssetsOthers;

    @Column(name = "less_amounts_transfer_to_capital_accounts")
    private Double lessAmountsTransferToCapitalAccounts;

    @Column(name = "less_share_of_loss_from_partnership_firm")
    private Double lessShareOfLossFromPartnershipFirm;

    @Column(name = "total_expenses")
    private Double totalExpenses;

    @Column(name = "profit_loss_before_exceptional_extraordinary_items_and_tax")
    private Double profitLossBeforeExceptionalExtraordinaryItemsAndTax;

    @Column(name = "exceptional_items")
    private Double exceptionalItems;

    @Column(name = "profit_loss_before_tax")
    private Double profitLossBeforeTax;

    @Column(name = "current_tax")
    private Double currentTax;

    @Column(name = "less_mat_credit")
    private Double lessMatCredit;

    @Column(name = "deferred_tax")
    private Double deferredTax;

    @Column(name = "other_direct_taxes")
    private Double otherDirectTaxes;

    @Column(name = "tax_for_earlier_years")
    private Double taxForEarlierYears;

    @Column(name = "total_tax_expenses_continued_operations")
    private Double totalTaxExpensesContinuedOperations;

    @Column(name = "profit_loss_after_tax_and_before_extraordinary_items")
    private Double profitLossAfterTaxAndBeforeExtraordinaryItems;

    @Column(name = "prior_period_items")
    private Double priorPeriodItems;

    @Column(name = "extraordinary_items")
    private Double extraordinaryItems;

    @Column(name = "profit_loss_from_continuing_operations")
    private Double profitLossFromContinuingOperations;

    @Column(name = "profit_loss_from_discontinuing_operations")
    private Double profitLossFromDiscontinuingOperations;

    @Column(name = "total_taxExpenses_discontinuing_operations")
    private Double totalTaxExpensesDiscontinuingOperations;

    @Column(name = "net_profit_loss_from_discontinuing_operations")
    private Double netProfitLossFromDiscontinuingOperations;

    @Column(name = "profit_loss_for_the_period")
    private Double profitLossForThePeriod;

    @Column(name = "equity_shares")
    private Double equityShares;

    @Column(name = "equity_capital")
    private Double equityCapital;

    @Column(name = "preference_capital")
    private Double preferenceCapital;

    @Column(name = "totalShare_capital")
    private Double totalShareCapital;

    @Column(name = "revaluation_reserves")
    private Double revaluationReserves;

    @Column(name = "reserves_and_surplus")
    private Double reservesAndSurplus;

    @Column(name = "total_reserves_and_surplus")
    private Double totalReservesAndSurplus;

    @Column(name = "money_against_share_warrants")
    private Double moneyAgainstShareWarrants;

    @Column(name = "employees_stock_options")
    private Double employeesStockOptions;

    @Column(name = "total_share_holders_funds")
    private Double totalShareHoldersFunds;

    @Column(name = "equity_share_application_money")
    private Double equityShareApplicationMoney;

    @Column(name = "preference_share_application_money")
    private Double preferenceShareApplicationMoney;

    @Column(name = "share_capital_suspense")
    private Double shareCapitalSuspense;

    @Column(name = "hybrid_debt_other_securities")
    private Double hybridDebtOtherSecurities;

    @Column(name = "statutory_consumer_reserves")
    private Double statutoryConsumerReserves;

    @Column(name = "special_apprn_towards_project_cost")
    private Double specialApprnTowardsProjectCost;

    @Column(name = "service_line_contribution_from_consumers")
    private Double serviceLineContributionFromConsumers;

    @Column(name = "government_other_grants")
    private Double governmentOtherGrants;

    @Column(name = "long_term_borrowings")
    private Double longTermBorrowings;

    @Column(name = "deferred_tax_liabilities")
    private Double deferredTaxLiabilities;

    @Column(name = "other_long_term_liabilities")
    private Double otherLongTermLiabilities;

    @Column(name = "long_term_provisions")
    private Double longTermProvisions;

    @Column(name = "total_non_current_liabilities")
    private Double totalNonCurrentLiabilities;

    @Column(name = "foreign_currency_monetary_item")
    private Double foreignCurrencyMonetaryItem;

    @Column(name = "short_term_borrowings")
    private Double shortTermBorrowings;

    @Column(name = "trade_payables")
    private Double tradePayables;

    @Column(name = "other_current_liabilities")
    private Double otherCurrentLiabilities;

    @Column(name = "short_term_provisions")
    private Double shortTermProvisions;

    @Column(name = "total_current_liabilities")
    private Double totalCurrentLiabilities;

    @Column(name = "total_capital_and_liabilities")
    private Double totalCapitalAndLiabilities;

    @Column(name = "tangible_assets")
    private Double tangibleAssets;

    @Column(name = "intangible_assets")
    private Double intangibleAssets;

    @Column(name = "capital_work_in_progress")
    private Double capitalWorkInProgress;

    @Column(name = "intangible_assets_under_development")
    private Double intangibleAssetsUnderDevelopment;

    @Column(name = "other_assets")
    private Double otherAssets;

    @Column(name = "construction_stores")
    private Double constructionStores;

    @Column(name = "mining_development_expenditure")
    private Double miningDevelopmentExpenditure;

    @Column(name = "assets_held_for_sale")
    private Double assetsHeldForSale;

    @Column(name = "total_fixed_assets")
    private Double totalFixedAssets;

    @Column(name = "non_current_investments")
    private Double nonCurrentInvestments;

    @Column(name = "deferred_taxAssets")
    private Double deferredTaxAssets;

    @Column(name = "long_term_loans_and_advances")
    private Double longTermLoansAndAdvances;

    @Column(name = "other_non_current_assets")
    private Double otherNonCurrentAssets;

    @Column(name = "total_non_current_assets")
    private Double totalNonCurrentAssets;

    @Column(name = "foreign_currency_mnetary_item_translation_diff_acct")
    private Double foreignCurrencyMnetaryItemTranslationDiffAcct;

    @Column(name = "current_investments")
    private Double currentInvestments;

    @Column(name = "inventories")
    private Double inventories;

    @Column(name = "trade_receivables")
    private Double tradeReceivables;

    @Column(name = "cash_and_cash_equivalents")
    private Double cashAndCashEquivalents;

    @Column(name = "short_term_loans_and_advances")
    private Double shortTermLoansAndAdvances;

    @Column(name = "other_current_assets")
    private Double otherCurrentAssets;

    @Column(name = "total_current_assets")
    private Double totalCurrentAssets;

    @Column(name = "total_assets")
    private Double totalAssets;

    @Column(name = "net_profit_loss_before_extraordinary_items_and_tax")
    private Double netProfitLossBeforeExtraordinaryItemsAndTax;

    @Column(name = "net_cash_flow_from_operating_activities")
    private Double netCashFlowFromOperatingActivities;

    @Column(name = "net_cash_used_in_investing_activities")
    private Double netCashUsedInInvestingActivities;

    @Column(name = "net_cash_used_from_financing_activities")
    private Double netCashUsedFromFinancement;

    @Column(name = "foreign_exchange_gains_losses")
    private Double foreignExchangeGainsLosses;

    @Column(name = "adjustments_on_amalgamation_merger_demerger_others")
    private Double adjustmentsOnAmalgamationMergerDemergerOthers;

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

    @Column(name = "contingent_liabilities")
    private Double contingentLiabilities;

    @Column(name = "cif_value_of_raw_materials")
    private Double cifValueOfRawMaterials;

    @Column(name = "cif_value_of_stores_spares_and_loose_tools")
    private Double cifValueOfStoresSparesAndLooseTools;

    @Column(name = "cif_value_of_other_goods")
    private Double cifValueOfOtherGoods;

    @Column(name = "cif_value_of_capital_goods")
    private Double cifValueOfCapitalGoods;

    @Column(name = "expenditure_in_foreign_currency")
    private Double expenditureInForeignCurrency;

    @Column(name = "dividend_remittance_in_foreign_currency")
    private Double dividendRemittanceInForeignCurrency;

    @Column(name = "foreign_exchange_fob")
    private Double foreignExchangeFob;

    @Column(name = "foreign_exchange_other_earnings")
    private Double foreignExchangeOtherEarnings;

    @Column(name = "imported_raw_materials")
    private Double importedRawMaterials;

    @Column(name = "indigenous_raw_materials")
    private Double indigenousRawMaterials;

    @Column(name = "imported_stores_and_spares")
    private Double importedStoresAndSpares;

    @Column(name = "indigenous_stores_and_spares")
    private Double indigenousStoresAndSpares;

    @Column(name = "equity_share_dividend")
    private Double equityShareDividend;

    @Column(name = "preference_share_dividend")
    private Double preferenceShareDividend;

    @Column(name = "tax_on_dividend")
    private Double taxOnDividend;

    @Column(name = "equity_dividend_rate")
    private Double equityDividendRate;

    @Column(name = "bonus_equity_share_capital")
    private Double bonusEquityShareCapital;

    @Column(name = "non_current_investments_quoted_market_value")
    private Double nonCurrentInvestmentsQuotedMarketValue;

    @Column(name = "non_current_investments_unquoted_book_value")
    private Double nonCurrentInvestmentsUnquotedBookValue;

    @Column(name = "current_investments_quoted_market_value")
    private Double currentInvestmentsQuotedMarketValue;

    @Column(name = "current_investments_unquoted_book_value")
    private Double currentInvestmentsUnquotedBookValue;

    @Column(name = "modified_date", length = 20)
    private String modifiedDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

}

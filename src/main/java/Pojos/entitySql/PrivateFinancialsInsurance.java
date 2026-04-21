package Pojos.entitySql;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity 
@Data
@Table(name = "private_financials_insurance") 
public class PrivateFinancialsInsurance {

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
	private float conversionFactor;

	@Column(name = "financial_format")
	private String financialFormat;

	@Column(name = "updated_on")
	private String updatedOn;

	@Column(name = "from_date")
	private String fromDate;

	@Column(name = "to_date")
	private String toDate;

	@Column(name = "reporting_standard")
	private String reportingStandard;

	@Column(name = "premium")
	private Double premium;

	@Column(name = "reinsurance_ceded")
	private Double reinsuranceCeded;

	@Column(name = "reinsurance_accepted")
	private Double reinsuranceAccepted;

	@Column(name = "premiums_earned_net")
	private Double premiumsEarnedNet;


	@Column(name = "interest_dividends_and_rent_gross")
	private Double interestDividendsAndRentGross;

	@Column(name = "profit_on_sale_redemption_of_investments")
	private Double profitOnSaleRedemptionOfInvestments;

	@Column(name = "loss_on_sale_redemption_of_investments")
	private Double lossOnSaleRedemptionOfInvestments;

	@Column(name = "transfer_gain_on_revaluation_in_fair_value")
	private Double transferGainOnRevaluationInFairValue;

	@Column(name = "amortisation_of_premium_on_investments")
	private Double amortisationOfPremiumOnInvestments;

	@Column(name = "others_tech_1")
	private Double othersTech1;

	@Column(name = "income_from_investments")
	private Double incomeFromInvestments;

	@Column(name = "other_income")
	private Double otherIncome;

	@Column(name = "total_income")
	private Double totalIncome;

	@Column(name = "claims_incurred")
	private Double claimsIncurred;

	@Column(name = "commission")
	private Double commission;

	@Column(name = "operating_expenses_related_to_insurance_business")
	private Double operatingExpensesRelatedToInsuranceBusiness;

	@Column(name = "depreciation")
	private Double depreciation;


	@Column(name = "interest")
	private Double interest;

	@Column(name = "net_operating_expenses")
	private Double netOperatingExpenses;

	@Column(name = "premium_deficiency")
	private Double premiumDeficiency;

	@Column(name = "service_tax_on_ulc")
	private Double serviceTaxOnUlc;

	@Column(name = "provision_for_doubtful_debts")
	private Double provisionForDoubtfulDebts;

	@Column(name = "bad_debts_written_off")
	private Double badDebtsWrittenOff;

	@Column(name = "provison_for_tax")
	private Double provisionForTax;

	@Column(name = "provision_other_than_taxation")
	private Double provisionOtherThanTaxation;

	@Column(name = "others_tech_2")
	private Double othersTech2;

	@Column(name = "benefits_paid_net")
	private Double benefitsPaidNet;

	@Column(name = "interim_terminal_bonuses_paid")
	private Double interestTerminalBonusesPaid;


	@Column(name = "other_expences")
	private Double otherExpenses;

	@Column(name = "total_expenses")
	private Double totalExpenses;

	@Column(name = "surplus_deficit_before_tax")
	private Double surplusDeficitBeforeTax;

	@Column(name = "others_tech_3")
	private Double othersTech3;

	@Column(name = "surplus_deficit_after_tax")
	private Double surplusDeficitAfterTax;

	@Column(name = "surplus_available_at_begning")
	private Double surplusAvailableAtBeginning;

	@Column(name = "total_surplus_for_available")
	private Double totalSurplusForAvailable;

	@Column(name = "appropriations_balance_for_appropriations")
	private Double appropriationsBalanceForAppropriations;

	@Column(name = "transfer_to_shareholders_account")
	private Double transferToShareholdersAccount;

	@Column(name = "funds_for_discontinued_policies")
	private Double fundsForDiscontinuedPolicies;

	@Column(name = "funds_for_future_appropriation")
	private Double fundsForFutureAppropriation;

	@Column(name = "others_tech_4")
	private Double othersTech4;

	@Column(name = "total_apropriations")
	private Double totalAppropriations;

	@Column(name = "amount_transfered_from_policyholder_account")
	private Double amountTransferedFromPolicyholderAccount;

	@Column(name = "fire_insurance")
	private Double fireInsurance;

	@Column(name = "marine_insurance")
	private Double marineInsurance;

	@Column(name = "miscellaneous_insurance")
	private Double miscellaneousInsurance;

	@Column(name = "operating_profit_transfered_to_policyholder_account")
	private Double operatingProfitTransferedToPolicyholderAccount;

	@Column(name = "income_from_investments_non_tech")
	private Double incomeFromInvestmentsNonTech;

	@Column(name = "other_income_non_tech")
	private Double otherIncomeNonTech;

	@Column(name = "total_revenue_tech")
	private Double totalRevenueTech;

	@Column(name = "total_revenue_non_tech")
	private Double totalRevenueNonTech;

	@Column(name = "expenses_other_than_insurance")
	private Double expensesOtherThanInsurance;

	@Column(name = "depreciation_non_tech")
	private Double depreciationNonTech;

	@Column(name = "interest_non_tech")
	private Double interestNonTech;


	@Column(name = "net_operating_expenses_non_tech")
	private Double netOperatingExpensesNonTech;

	@Column(name = "bad_debts_written_off_non_tech")
	private Double badDebtsWrittenOffNonTech;

	@Column(name = "provisions_other_than_tax_non_tech")
	private Double provisionsOtherThanTaxNonTech;

	@Column(name = "contribution_from_shareholder_account")
	private Double contributionFromShareholderAccount;

	@Column(name = "other_expences_non_tech")
	private Double otherExpensesNonTech;

	@Column(name = "total_expenses_non_tech")
	private Double totalExpensesNonTech;

	@Column(name = "pbt_tech")
	private Double pbtTech;

	@Column(name = "pbt_non_tech")
	private Double pbtNonTech;

	@Column(name = "provison_for_tax_non_tech")
	private Double provisionForTaxNonTech;

	@Column(name = "others_non_tech_2")
	private Double othersNonTech2;

	@Column(name = "pat_tech")
	private Double patTech;

	@Column(name = "pat_non_tech")
	private Double patNonTech;

	@Column(name = "appropriations")
	private Double appropriations;

	@Column(name = "p_l_balance_sheet_tech")
	private String plBalanceSheetTech;

	@Column(name = "p_l_balance_sheet_non_tech")
	private Double plBalanceSheetNonTech;

	@Column(name = "earnings_per_share")
	private Double earningsPerShare;

	@Column(name = "share_capital")
	private Double shareCapital;

	@Column(name = "share_appl_pending_allot")
	private Double shareApplPendingAllot;

	@Column(name = "reserve_and_surplus")
	private Double reserveAndSurplus;

	@Column(name = "networth")
	private Double netWorth;

	@Column(name = "credit_fair_value")
	private Double creditFairValue;

	@Column(name = "total_shareholders_funds")
	private Double totalShareholdersFunds;

	@Column(name = "borrowings")
	private Double borrowings;

	@Column(name = "minority_interest")
	private Double minorityInterest;


	@Column(name = "deff_tax_liab")
	private Double deffTaxLiability;

	@Column(name = "total_loans_def_tax_liab")
	private Double totalLoansDefTaxLiability;

	@Column(name = "policyholders_funds")
	private Double policyholdersFunds;

	@Column(name = "credit_fair_value_change_acc")
	private Double creditFairValueChangeAcc;

	@Column(name = "policy_liabilties")
	private Double policyLiabilities;

	@Column(name = "insurance_reserves")
	private Double insuranceReserves;

	@Column(name = "provision_for_linked_liabilites")
	private Double provisionForLinkedLiabilities;

	@Column(name = "add_fair_value_change")
	private Double addFairValueChange;

	@Column(name = "funds_for_discontinued_policies_sof")
	private Double fundsForDiscontinuedPoliciesSof;

	@Column(name = "total_provision_for_liabilities")
	private Double totalProvisionForLiabilities;

	@Column(name = "total_policyholders_fund")
	private Double totalPolicyholdersFund;

	@Column(name = "funds_for_future_appropriation_sof")
	private Double fundsForFutureAppropriationSof;

	@Column(name = "total_liabilities")
	private Double totalLiabilities;

	@Column(name = "totoal_soffunds")
	private Double totalSofFunds;

	@Column(name = "shareholders")
	private Double shareholders;

	@Column(name = "policyholders")
	private Double policyholders;

	@Column(name = "investments")
	private Double investments;

	@Column(name = "total_investments")
	private Double totalInvestments;

	@Column(name = "assets_to_cover_linked_liabilities")
	private Double assetsToCoverLinkedLiabilities;

	@Column(name = "loan")
	private Double loan;

	@Column(name = "fixed_assets")
	private Double fixedAssets;

	@Column(name = "def_tax_assets")
	private Double defTaxAssets;

	@Column(name = "total_investments_loans_assets")
	private Double totalInvestmentsLoansAssets;

	@Column(name = "cash_bank_balances")
	private Double cashBankBalances;

	@Column(name = "advances_other_assets")
	private Double advancesOtherAssets;

	@Column(name = "total_current_assests")
	private Double totalCurrentAssets;

	@Column(name = "current_liablities")
	private Double currentLiabilities;

	@Column(name = "provisions")
	private Double provisions;

	@Column(name = "deffered_tax_liability")
	private Double deferredTaxLiability;

	@Column(name = "total_current_liabilities_provisions")
	private Double totalCurrentLiabilitiesProvisions;

	@Column(name = "net_current_assets")
	private Double netCurrentAssets;

	@Column(name = "misc_expenditure")
	private Double miscExpenditure;

	@Column(name = "debit_balance_for_pl")
	private Double debitBalanceForPL;

	@Column(name = "dif_in_revenue")
	private Double differenceInRevenue;

	@Column(name = "total_aoffunds")
	private Double totalAofFunds;

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
	private Double sharesOutstanding;

	@Column(name = "shares_os_fully_diluted")
	private Double sharesOutstandingFullyDiluted;

	@Column(name = "eps_basic")
	private Double earningsPerShareBasic;

	@Column(name = "eps_diluted")
	private Double earningsPerShareDiluted;

	@Column(name = "dividend")
	private Double dividend;

	@Column(name = "net_retention")
	private Double netRetention;

	@Column(name = "management_expenses")
	private Double managementExpenses;

	@Column(name = "commission_ratio")
	private Double commissionRatio;

	@Column(name = "claims_ratio")
	private Double claimsRatio;

	@Column(name = "policyholders_liabilities")
	private Double policyholdersLiabilities;

	@Column(name = "growth_rate")
	private Double growthRate;

	@Column(name = "change_in_networth")
	private Double changeInNetworth;

	@Column(name = "pat_margin")
	private Double patMargin;

	@Column(name = "surplus_to_policy")
	private Double surplusToPolicy;

	@Column(name = "loss_ratio")
	private Double lossRatio;

	@Column(name = "total_fund_under_p_a")
	private Double totalFundUnderPA;

	@Column(name = "total_fund_under_s_a")
	private Double totalFundUnderSA;

	@Column(name = "return_on_capital_employed")
	private Double returnOnCapitalEmployed;

	@Column(name = "return_on_networth")
	private Double returnOnNetworth;

	@Column(name = "operating_profit_margin")
	private Double operatingProfitMargin;

	@Column(name = "pbt_margin")
	private Double pbtMargin;

	@Column(name = "reinsurance_accepted_to_gross_premium")
	private Double reinsuranceAcceptedToGrossPremium;

	@Column(name = "reinsurance_ceded_to_gross_premium")
	private Double reinsuranceCededToGrossPremium;

	@Column(name = "income_from_investments_to_gross_premium")
	private Double incomeFromInvestmentsToGrossPremium;

	@Column(name = "net_earning_ratio")
	private Double netEarningRatio;

	@Column(name = "net_incurred_claims")
	private Double netIncurredClaims;

	@Column(name = "combined_ratio")
	private Double combinedRatio;

	@Column(name = "investment_yield")
	private Double investmentYield;

	@Column(name = "operating_expenses_ratio")
	private Double operatingExpensesRatio;

	@Column(name = "priority_check")
	private Integer priorityCheck;

	@Column(name = "pfh_discription", columnDefinition = "LONGTEXT")
	private String pfhDescription;

	@Column(name = "updated_at")
	private String updatedAt;

	@Column(name = "created_at")
	private String createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

	
	
}

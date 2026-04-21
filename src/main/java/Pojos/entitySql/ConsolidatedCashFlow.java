package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "consolidated_cash_flow")
public class ConsolidatedCashFlow {

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

    @Column(name = "profits_before_tax")
    private Double profitsBeforeTax;

    @Column(name = "profits_after_tax")
    private Double profitsAfterTax;

    @Column(name = "depreciation")
    private Double depreciation;

    @Column(name = "fin_lease_and_rental_charges")
    private Double finLeaseAndRentalCharges;

    @Column(name = "lease_equalisation")
    private Double leaseEqualisation;

    @Column(name = "pl_in_forex")
    private Double plInForex;

    @Column(name = "gain_on_forex_exch_tran")
    private Double gainOnForexExchTran;

    @Column(name = "pl_on_sale_of_assets")
    private Double plOnSaleOfAssets;

    @Column(name = "pl_on_sale_of_investments")
    private Double plOnSaleOfInvestments;

    @Column(name = "profit_adj_on_sale_of_undrtkng")
    private Double profitAdjOnSaleOfUndrtkng;

    @Column(name = "interest_income")
    private Double interestIncome;

    @Column(name = "interest_paid_net")
    private Double interestPaidNet;

    @Column(name = "interest_net")
    private Double interestNet;

    @Column(name = "devidend_received_oprt_activity")
    private Double dividendReceivedOprtActivity;

    @Column(name = "devidend_net")
    private Double dividendNet;

    @Column(name = "investments")
    private Double investments;

    @Column(name = "misc_income")
    private Double miscIncome;

    @Column(name = "amortisation_of_expenses_oprt_activity")
    private Double amortisationOfExpensesOprtActivity;

    @Column(name = "assets_written_off")
    private Double assetsWrittenOff;

    @Column(name = "misc_expenses")
    private Double miscExpenses;

    @Column(name = "payment_towards_vrs")
    private Double paymentTowardsVrs;

    @Column(name = "prov_and_wo_net")
    private Double provAndWoNet;

    @Column(name = "provision_for_gratuity")
    private Double provisionForGratuity;

    @Column(name = "prov_for_dimun_in_value_of_invest")
    private Double provisionForDimunInValueOfInvest;

    @Column(name = "provisions_for_bad_debts_npa")
    private Double provisionsForBadDebtsNpa;

    @Column(name = "trade_and_oth_receivables")
    private Double tradeAndOthReceivables;

    @Column(name = "trade_bill_spurchased")
    private Double tradeBillSPurchased;

    @Column(name = "inventories_orpt_activity")
    private Double inventoriesOprtActivity;

    @Column(name = "trade_payables")
    private Double tradePayables;

    @Column(name = "tax_provision")
    private Double taxProvision;

    @Column(name = "direct_taxes_paid")
    private Double directTaxesPaid;

    @Column(name = "advance_taxe_paid")
    private Double advanceTaxePaid;

    @Column(name = "loan_and_advances")
    private Double loanAndAdvances;

    @Column(name = "transfer_from_reserve")
    private Double transferFromReserve;

    @Column(name = "others_from_oprt_activity")
    private Double othersFromOprtActivity;

    @Column(name = "prior_year_adjusments")
    private Double priorYearAdjusments;

    @Column(name = "provisions_written_back")
    private Double provisionsWrittenBack;

    @Column(name = "prior_years_taxation")
    private Double priorYearsTaxation;

    @Column(name = "balance_written_back")
    private Double balanceWrittenBack;

    @Column(name = "other_assets")
    private Double otherAssets;

    @Column(name = "other_liabilities")
    private Double otherLiabilities;

    @Column(name = "change_in_deposits")
    private Double changeInDeposits;

    @Column(name = "change_in_borrowing")
    private Double changeInBorrowing;

    @Column(name = "discount_exp_on_loans_wrt_off")
    private Double discountExpOnLoansWrtOff;

    @Column(name = "increase_decrease_in_advances")
    private Double increaseDecreaseInAdvances;

    @Column(name = "increase_decrease_in_investments")
    private Double increaseDecreaseInInvestments;

    @Column(name = "net_stock_on_hire")
    private Double netStockOnHire;

    @Column(name = "leased_assets_net_of_sale")
    private Double leasedAssetsNetOfSale;

    @Column(name = "excess_depreciation_wb")
    private Double excessDepreciationWb;

    @Column(name = "premium_on_lease_of_land")
    private Double premiumOnLeaseOfLand;

    @Column(name = "extra_ordinary_items")
    private Double extraOrdinaryItems;

    @Column(name = "operationg_minority_interest")
    private Double operationgMinorityInterest;

    @Column(name = "operationg_share_of_profit_of_asso")
    private Double operationgShareOfProfitOfAsso;

    @Column(name = "net_cash_flow_oprtng_activity")
    private Double netCashFlowOprtngActivity;

    @Column(name = "purchase_of_fixed_assets")
    private Double purchaseOfFixedAssets;

    @Column(name = "sale_of_fixed_assets")
    private Double saleOfFixedAssets;

    @Column(name = "capital_wip")
    private Double capitalWip;

    @Column(name = "capital_subsidy_recd")
    private Double capitalSubsidyRecd;

    @Column(name = "investment_in_good_will")
    private Double investmentInGoodWill;

    @Column(name = "purchase_of_investments")
    private Double purchaseOfInvestments;

    @Column(name = "sale_of_investments_inves_activity")
    private Double saleOfInvestmentsInvesActivity;

    @Column(name = "aquisition_of_companies")
    private Double aquisitionOfCompanies;

    @Column(name = "sale_of_undrtkng_extra_ord_item")
    private Double saleOfUndrtkngExtraOrdItem;

    @Column(name ="net_inc_dec_in_cash_and_equvlnt")
    private Double netIncDecInCashAndEquvlnt ;

    @Column(name = "interest_received")
    private Double interestReceived;

    @Column(name = "devidend_received_inves_activity")
    private Double devidendReceivedInvesActivity;

    @Column(name = "investment_income")
    private Double investmentIncome;

    @Column(name = "inter_corporate_deposits")
    private Double interCorporateDeposits;

    @Column(name = "investment_in_subsidiaries")
    private Double investmentInSubsidiaries;

    @Column(name = "loan_to_subsidiaries")
    private Double loanToSubsidiaries;

    @Column(name = "investment_in_group_cos")
    private Double investmentInGroupCos;

    @Column(name = "issue_of_sh_on_acqu_of_cos")
    private Double issueOfShOnAcquOfCos;

    @Column(name = "cans_of_investment_in_cos_acq")
    private Double cansOfInvestmentInCosAcq;

    @Column(name = "certificate_of_deposit_in_bank")
    private Double certificateOfDepositInBank;

    @Column(name = "movement_in_loans")
    private Double movementInLoans;

    @Column(name = "investing_minority_interest")
    private Double investingMinorityInterest;

    @Column(name = "investing_share_of_profit_of_asso")
    private Double investingShareOfProfitOfAsso;

    @Column(name = "others_from_invest_activity")
    private Double othersFromInvestActivity;

    @Column(name = "movement_in_working_capital")
    private Double movementInWorkingCapital;

    @Column(name = "amortisation_of_expenses_inves_activity")
    private Double amortisationOfExpensesInvesActivity;

    @Column(name = "taxes_paid")
    private Double taxesPaid;

    @Column(name = "expenses_capitalised")
    private Double expensesCapitalised;

    @Column(name = "extraordinary_items_invst_activity")
    private Double extraordinaryItemsInvstActivity;

    @Column(name = "purchase_of_fixed_assets_leased_out")
    private Double purchaseOfFixedAssetsLeasedOut;

    @Column(name = "net_inc_dec_in_current_asset")
    private Double netIncDecInCurrentAsset;

    @Column(name = "net_inc_dec_in_advances")
    private Double netIncDecInAdvances;

    @Column(name = "net_inc_dec_in_currentliab")
    private Double netIncDecInCurrentLiab;

    @Column(name = "net_cash_used_in_investment_activity")
    private Double netCashUsedInInvestmentActivity;

    @Column(name = "proceeds_from_issue_of_eq_capital")
    private Double proceedsFromIssueOfEqCapital;

    @Column(name = "proceeds_from_issue_of_pref_capital")
    private Double proceedsFromIssueOfPrefCapital;

    @Column(name = "proceeds_from_issue_of_sn_cap_incl_sh_prem")
    private Double proceedsFromIssueOfSnCapInclShPrem;

    @Column(name = "redemption_of_capital")
    private Double redemptionOfCapital;

    @Column(name = "proceeds_from_issue_of_deb")
    private Double proceedsFromIssueOfDeb;

    @Column(name = "proceeds_from_bank_borrowings")
    private Double proceedsFromBankBorrowings;

    @Column(name = "proceeds_from_th_l_term_borr")
    private Double proceedsFromThLTermBorr;

    @Column(name = "proceeds_from_sh_term_borr")
    private Double proceedsFromShTermBorr;

    @Column(name = "proceeds_from_deposits")
    private Double proceedsFromDeposits;

    @Column(name = "repayment_of_borrowings")
    private Double repaymentOfBorrowings;

    @Column(name = "share_application")
    private Double shareApplication;

    @Column(name = "loan_from_a_corporate_body")
    private Double loanFromACorporateBody;

    @Column(name = "devidend_paid")
    private Double devidendPaid;

    @Column(name = "interest_paid")
    private Double interestPaid;

    @Column(name = "financial_charges")
    private Double financialCharges;

    @Column(name = "cash_credit_advances")
    private Double cashCreditAdvances;

    @Column(name = "cash_cap_investment_subsidy")
    private Double cashCapInvestmentSubsidy;

    @Column(name = "other_from_fin_activity")
    private Double otherFromFinActivity;

    @Column(name = "foreign_exchange_gains_losses_fin_activity")
    private Double foreignExchangeGainsLossesFinActivity;

    @Column(name = "share_premium")
    private Double sharePremium;

    @Column(name = "misc_expences_written_off")
    private Double miscExpencesWrittenOff;

    @Column(name = "sale_of_investments_fin_activity")
    private Double saleOfInvestmentsFinActivity;

    @Column(name = "reserves")
    private Double reserves;

    @Column(name = "current_liabilities")
    private Double currentLiabilities;

    @Column(name = "loan_disbursed")
    private Double loanDisbursed;

    @Column(name = "inventories_fin_activitiy")
    private Double inventoriesFinActivitiy;

    @Column(name = "extraordinary_items_fin_activity")
    private Double extraordinaryItemsFinActivity;

    @Column(name = "deffered_exp_against_borrowing")
    private Double deferredExpAgainstBorrowing;

    @Column(name = "share_application_refund")
    private Double shareApplicationRefund;

    @Column(name = "on_redem_of_deben")
    private Double onRedemptionOfDebenture;

    @Column(name = "of_oth_l_term_borr")
    private Double offOtherLongTermBorrowing;

    @Column(name = "of_sh_term_borr")
    private Double offShortTermBorrowing;


    @Column(name = "of_fin_lease_liabi")
    private Double offFinLeaseLiabilities;


    @Column(name = "shelter_assistance_reserve")
    private Double shelterAssistanceReserve;

    @Column(name = "repayment_of_short_term_borrow")
    private Double repaymentOfShortTermBorrow;

    @Column(name = "financing_share_of_profit_of_asso")
    private Double financingShareOfProfitOfAsso;

    @Column(name = "proceeds_from_shares_issued_by_subsidiaries")
    private Double proceedsFromSharesIssuedBySubsidiaries;

    @Column(name = "proceeds_from_issue_of_perpetual_bonds")
    private Double proceedsFromIssueOfPerpetualBonds;

    @Column(name = "proceeds_from_issue_of_subordinated_debts")
    private Double proceedsFromIssueOfSubordinatedDebts;

    @Column(name = "repayment_of_long_term_borrow")
    private Double repaymentOfLongTermBorrow;

    @Column(name = "net_cash_used_in_finance_activity")
    private Double netCashUsedInFinanceActivity;

    @Column(name = "foreign_exchange_gains_losses_net_fin_activity")
    private Double foreignExchangeGainsLossesNetFinActivity;


//    @Column(name = "net_inc_dec_in_cash_and_equvalnt")
//    private Double netIncDecInCashAndEquvalnt;

    @Column(name = "cash_and_equvalnt_begin_of_year")
    private Double cashAndEquvalntBeginOfYear;

    @Column(name = "cash_and_equvalnt_end_of_year")
    private Double cashAndEquvalntEndOfYear;

    @Column(name = "modified_date", length = 20)
    private String modifiedDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    
}

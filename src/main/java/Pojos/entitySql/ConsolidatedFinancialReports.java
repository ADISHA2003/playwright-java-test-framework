package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "consolidated_financial_reports")
public class ConsolidatedFinancialReports {

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

    @Column(name = "operating_income")
    private Double operatingIncome;

    @Column(name = "manufacturing_expenses")
    private Double manufacturingExpenses;

    @Column(name = "material_consumed")
    private Double materialConsumed;

    @Column(name = "personnel_exp")
    private Double personnelExpenses;

    @Column(name = "selling_expenses")
    private Double sellingExpenses;

    @Column(name = "admin_expenses")
    private Double adminExpenses;

    @Column(name = "exp_capitalised")
    private Double expCapitalised;

    @Column(name = "cost_of_sales")
    private Double costOfSales;

    @Column(name = "banks_provisions_made")
    private Double banksProvisionsMade;

    @Column(name = "operating_profit")
    private Double operatingProfit;

    @Column(name = "other_recurring_income")
    private Double otherRecurringIncome;

    @Column(name = "adjusted_pbdit")
    private Double adjustedPbdit;

    @Column(name = "financial_expences")
    private Double financialExpenses;

    @Column(name = "depreciation")
    private Double depreciation;

    @Column(name = "Prel_def_rev_exp_w_off")
    private Double prelDefRevExpWOff;

    @Column(name = "adjusted_pbt")
    private Double adjustedPbt;

    @Column(name = "taxation")
    private Double taxation;

    @Column(name = "adjusted_pat")
    private Double adjustedPat;

    @Column(name = "income_minority_interest")
    private Double incomeMinorityInterest;

    @Column(name = "Share_of_pl_in_associates")
    private Double shareOfPlInAssociates;

    @Column(name = "adjusted_pat_after_mi_and_sa")
    private Double adjustedPatAfterMiAndSa;

    @Column(name = "non_recurring_income")
    private Double nonRecurringIncome;

    @Column(name = "non_cash_adjustments")
    private Double nonCashAdjustments;

    @Column(name = "reported_net_profit")
    private Double reportedNetProfit;

    @Column(name = "equity_dividend")
    private Double equityDividends;

    @Column(name = "proposed_pref_divdnd")
    private Double proposedPrefDividend;

    @Column(name = "retained_earnings")
    private Double retainedEarnings;

    @Column(name = "appropriations")
    private Double appropriations;

    @Column(name = "sales_manufacturing")
    private Double salesManufacturing;

    @Column(name = "sales_trading")
    private Double salesTrading;

    @Column(name = "excise")
    private Double excise;

    @Column(name = "fundbased_income")
    private Double fundbasedIncome;

    @Column(name = "feebased_income")
    private Double feebasedIncome;

    @Column(name = "fiscal_benefits")
    private Double fiscalBenefits;

    @Column(name = "raw_mat_consumed")
    private Double rawMatConsumed;

    @Column(name = "packing_material_consumed")
    private Double packingMaterialConsumed;

    @Column(name = "spares_stores_consmption")
    private Double sparesStoresConsumption;

    @Column(name = "purchase_finish_goods")
    private Double purchaseFinishGoods;

    @Column(name = "dec_inc_in_stocks")
    private Double decIncInStocks;

    @Column(name = "power_fuel")
    private Double powerFuel;

    @Column(name = "other_manufacturing_exp")
    private Double otherManufacturingExp;

    @Column(name = "exp_advertising")
    private Double expAdvertising;

    @Column(name = "exp_other_promotion")
    private Double expOtherPromotion;

    @Column(name = "distribution_exp")
    private Double distributionExp;

    @Column(name = "other_selling_expenses")
    private Double otherSellingExpenses;

    @Column(name = "income_group_share_in_jv")
    private Double incomeGroupShareInJv;

    @Column(name = "pl_sale_of_asset")
    private Double plSaleOfAsset;

    @Column(name = "pl_on_sale_of_invstmts")
    private Double plOnSaleOfInvestments;

    @Column(name = "insurance_claims")
    private Double insuranceClaims;

    @Column(name = "exchg_rate_fluct")
    private Double exchgRateFluct;

    @Column(name = "layoff_retrench_vrs")
    private Double layoffRetrenchVrs;

    @Column(name = "extr_ordinary_items")
    private Double extrOrdinaryItems;

    @Column(name = "contingent_liabilities")
    private Double contingentLiabilities;

    @Column(name = "export_fob_value")
    private Double exportFobValue;

    @Column(name = "export_earnings")
    private Double exportEarnings;

    @Column(name = "cif_value_imports")
    private Double cifValueImports;

    @Column(name = "imp_capital_goods")
    private Double impCapitalGoods;

    @Column(name = "foreign_exchange_expn")
    private Double foreignExchangeExpn;

    @Column(name = "imported_rawmat")
    private Double importedRawMat;

    @Column(name = "indigenious_rawmat")
    private Double indigeniousRawMat;

    @Column(name = "imported_spares")
    private Double importedSpares;

    @Column(name = "indigenious_spares")
    private Double indigeniousSpares;

    @Column(name = "equity_capital")
    private Double equityCapital;

    @Column(name = "pref_capital")
    private Double prefCapital;

    @Column(name = "init_contribution_settler")
    private Double initContributionSettler;

    @Column(name = "share_appl_money")
    private Double shareApplMoney;

    @Column(name = "pref_cap_share_app_money")
    private Double prefCapShareAppMoney;

    @Column(name = "esop")
    private Double esop;

    @Column(name = "authorised_capital")
    private Double authorisedCapital;

    @Column(name = "res_and_surplus")
    private Double resAndSurplus;

    @Column(name = "free_reserves_and_surplus")
    private Double freeReservesAndSurplus;

    @Column(name = "other_reserves")
    private Double otherReserves;

    @Column(name = "secured_loans")
    private Double securedLoans;

    @Column(name = "unsecured_loans")
    private Double unsecuredLoans;

    @Column(name = "long_term_loan")
    private Double longTermLoan;

    @Column(name = "unsecured_term_loans")
    private Double unsecuredTermLoans;

    @Column(name = "borrowings_by_bank")
    private Double borrowingsByBank;

    @Column(name = "liability_minority_interest")
    private Double liabilityMinorityInterest;

    @Column(name = "liability_policy_holders_fund")
    private Double liabilityPolicyHoldersFund;

    @Column(name = "liability_group_share_in_jv")
    private Double liabilityGroupShareInJv;

    @Column(name = "gross_block")
    private Double grossBlock;

    @Column(name = "revaluation_reserve")
    private Double revaluationReserve;

    @Column(name = "depreciation_on_fassets")
    private Double depreciationOnFassets;

    @Column(name = "net_block")
    private Double netBlock;

    @Column(name = "capital_wip")
    private Double capitalWip;

    @Column(name = "investments")
    private Double investments;

    @Column(name = "current_assets")
    private Double currentAssets;

    @Column(name = "curr_liab_and_prov")
    private Double currLiabAndProv;

    @Column(name = "net_current_assets")
    private Double netCurrentAssets;

    @Column(name = "cash_credits")
    private Double cashCredits;

    @Column(name = "bills_purchased")
    private Double billsPurchased;

    @Column(name = "term_loans")
    private Double termLoans;

    @Column(name = "advances_outside_india")
    private Double advancesOutsideIndia;

    @Column(name = "adv_out_housing_loans")
    private Double advOutHousingLoans;

    @Column(name = "inter_office_adj_net_liab")
    private Double interOfficeAdjNetLiab;

    @Column(name = "asset_minority_interest")
    private Double assetMinorityInterest;

    @Column(name = "asset_group_share_in_jv")
    private Double assetGroupShareInJv;

    @Column(name = "misc_exp_not_w_off")
    private Double miscExpNotWOff;

    @Column(name = "bonus_in_equity_cap")
    private Double bonusInEquityCap;

    @Column(name = "number_of_equity_shares")
    private Double numberOfEquityShares;

    @Column(name = "demand_deposits")
    private Double demandDeposits;

    @Column(name = "savings_deposits_unsecured")
    private Double savingsDepositsUnsecured;

    @Column(name = "time_deposits_unsecured")
    private Double timeDepositsUnsecured;

    @Column(name = "dep_of_ind_branches")
    private Double depOfIndBranches;

    @Column(name = "dep_of_forgn_branches")
    private Double depOfForgnBranches;

    @Column(name = "invest_outside_india")
    private Double investOutsideIndia;

    @Column(name = "book_value")
    private Double bookValue;

    @Column(name = "market_value")
    private Double marketValue;

    @Column(name = "cash_and_bank_balance")
    private Double cashAndBankBalance;

    @Column(name = "money_at_call_short_notice")
    private Double moneyAtCallShortNotice;

    @Column(name = "receivables")
    private Double receivables;

    @Column(name = "loan_adv")
    private Double loanAdv;

    @Column(name = "raw_inventory")
    private Double rawInventory;

    @Column(name = "wip_inventory")
    private Double wipInventory;

    @Column(name = "finished_goods_inventory")
    private Double finishedGoodsInventory;

    @Column(name = "other_inventory")
    private Double otherInventory;

    @Column(name = "sundry_creditors")
    private Double sundryCreditors;

    @Column(name = "other_current_liabilities")
    private Double otherCurrentLiabilities;

    @Column(name = "total_provisions")
    private Double totalProvisions;

    @Column(name = "current_year_adj")
    private Double currentYearAdj;

    @Column(name = "prev_year_adj")
    private Double prevYearAdj;

    @Column(name = "adj_fix_ass")
    private Double adjFixAss;

    @Column(name = "purchases")
    private Double purchases;

    @Column(name = "number_of_employees")
    private Integer numberOfEmployees;

    @Column(name = "number_of_branches")
    private Integer numberOfBranches;

    @Column(name = "modified_date", length = 20)
    private String modifiedDate;

    @Column(name = "total_income")
    private Double totalIncome;

    @Column(name = "ebit")
    private Double ebit;

    @Column(name = "net_worth")
    private Double netWorth;

    @Column(name = "total_debt")
    private Double totalDebt;

    @Column(name = "TotalAssets")
    private Double TotalAssets;

    @Column(name = "ebita_margin")
    private Double ebitaMargin;

    @Column(name = "equitydividend")
    private Double equityDividend;

    @Column(name = "closingprice")
    private Double closingPrice;

    @Column(name = "Market_Capitalization")
    private Double MarketCapitalization;

    @Column(name = "EnterpriseValue")
    private Double EnterpriseValue;

    @Column(name = "total_current_liabilities")
    private Double totalCurrentLiabilities;

    @Column(name = "total_current_assets")
    private Double totalCurrentAssets;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

}


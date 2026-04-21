package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "quarterly_results")
public class QuarterlyResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quarterly_results_id")
    private Integer id;

    @Column(name = "company_code", nullable = false)
    private Double companyCode;

    @Column(name = "year_ending1", nullable = false)
    private short yearEnding1;

    @Column(name = "months", nullable = false)
    private byte months;

    @Column(name = "quarter", nullable = false)
    private byte quarter;

    @Column(name = "operating_income", nullable = false)
    private Double operatingIncome;

    @Column(name = "other_operating_income", nullable = false)
    private Double otherOperatingIncome;

    @Column(name = "total_income_from_operations", nullable = false)
    private Double totalIncomeFromOperations;

    @Column(name = "int_or_disc_on_adv_or_bills", nullable = false)
    private Double intOrDiscOnAdvOrBills;

    @Column(name = "income_on_investment", nullable = false)
    private Double incomeOnInvestment;

    @Column(name = "int_on_balances_with_rbi", nullable = false)
    private Double intOnBalancesWithRBI;

    @Column(name = "others", nullable = false)
    private Double others;

    @Column(name = "other_recurring_income", nullable = false)
    private Double otherRecurringIncome;

    @Column(name = "stock_adjustment", nullable = false)
    private Double stockAdjustment;

    @Column(name = "raw_material_consumed", nullable = false)
    private Double rawMaterialConsumed;

    @Column(name = "purchase_of_traded_goods", nullable = false)
    private Double purchaseOfTradedGoods;

    @Column(name = "power_and_fuel", nullable = false)
    private Double powerAndFuel;

    @Column(name = "employee_expenses", nullable = false)
    private Double employeeExpenses;

    @Column(name = "excise", nullable = false)
    private Double excise;

    @Column(name = "admin_and_selling_expenses", nullable = false)
    private Double adminAndSellingExpenses;
//
//    @Column(name = "research_and_development_expenses", nullable = false)
//    private Double researchAndDevelopmentExpenses;

    @Column(name = "expenses_capitalised", nullable = false)
    private Double expensesCapitalised;

    @Column(name = "other_expeses", nullable = false)
    private Double otherExpeses;

    @Column(name = "pl_before_other_inc_int_excp_item_tax", nullable = false)
    private Double plBeforeOtherIncIntExcpItemTax;

    @Column(name = "pl_before_int_excp_item_tax", nullable = false)
    private Double plBeforeIntExcpItemTax;

    @Column(name = "interest_charges", nullable = false)
    private Double interestCharges;

    @Column(name = "pl_before_excp_item_tax", nullable = false)
    private Double plBeforeExcpItemTax;

    @Column(name = "exceptional_items", nullable = false)
    private Double exceptionalItems;

    @Column(name = "depreciation", nullable = false)
    private Double depreciation;

    @Column(name = "operating_profit_before_provisions_and_contingencies", nullable = false)
    private Double operatingProfitBeforeProvisionsAndContingencies;

    @Column(name = "bank_provisions_made", nullable = false)
    private Double bankProvisionsMade;

    @Column(name = "pl_before_tax", nullable = false)
    private Double plBeforeTax;

    @Column(name = "tax_charges", nullable = false)
    private Double taxCharges;

    @Column(name = "pl_after_tax_from_ordinery_activities", nullable = false)
    private Double plAfterTaxFromOrdinaryActivities;

    @Column(name = "extra_ordinary_item", nullable = false)
    private Double extraOrdinaryItem;

    @Column(name = "reported_pat", nullable = false)
    private Double reportedPat;

    @Column(name = "prior_year_adj", nullable = false)
    private Double priorYearAdj;

    @Column(name = "reserves_written_back", nullable = false)
    private Double reservesWrittenBack;

    @Column(name = "equity_capital", nullable = false)
    private Double equityCapital;

    @Column(name = "reserves_and_surplus", nullable = false)
    private Double reservesAndSurplus;

    @Column(name = "eq_dividend_rate", nullable = false)
    private Double eqDividendRate;

    @Column(name = "aggregate_of_non_promoto_no_of_shares", nullable = false)
    private Double aggregateOfNonPromotoNoOfShares;

    @Column(name = "aggregate_of_non_promoto_holding_percent", nullable = false)
    private Double aggregateOfNonPromotoHoldingPercent;

    @Column(name = "government_share", nullable = false)
    private Double governmentShare;

    @Column(name = "capital_adequacy_ratio", nullable = false)
    private Double capitalAdequacyRatio;

    @Column(name = "capital_adequacy_baseII", nullable = false)
    private Double capitalAdequacyBaseII;

    @Column(name = "gross_npa", nullable = false)
    private Double grossNPA;

    @Column(name = "net_npa", nullable = false)
    private Double netNPA;

    @Column(name = "percentage_of_gross_npa", nullable = false)
    private Double percentageOfGrossNPA;

    @Column(name = "percentage_of_net_npa", nullable = false)
    private Double percentageOfNetNPA;

    @Column(name = "return_on_assets_per", nullable = false)
    private Double returnOnAssetsPer;

    @Column(name = "before_basic_eps", nullable = false)
    private Double beforeBasicEPS;

    @Column(name = "before_diluted_eps", nullable = false)
    private Double beforeDilutedEPS;

    @Column(name = "after_basic_eps", nullable = false)
    private Double afterBasicEPS;

    @Column(name = "after_diluted_eps", nullable = false)
    private Double afterDilutedEPS;

    @Column(name = "en_number_of_shares", nullable = false)
    private Double enNumberOfShares;

    @Column(name = "en_per_share_as_per_of_tot_sh_hol_of_pro_and_group", nullable = false)
    private Double enPerShareAsPerOfTotShHolOfProAndGroup;

    @Column(name = "en_per_share_as_per_of_tot_sh_cap_of_company", nullable = false)
    private Double enPerShareAsPerOfTotShCapOfCompany;

    @Column(name = "non_en_number_of_shares", nullable = false)
    private Double nonEnNumberOfShares;

    @Column(name = "non_en_per_share_as_per_of_tot_sh_hol_of_pro_and_group", nullable = false)
    private Double nonEnPerShareAsPerOfTotShHolOfProAndGroup;

    @Column(name = "non_en_per_share_as_per_of_tot_sh_cap_of_company", nullable = false)
    private Double nonEnPerShareAsPerOfTotShCapOfCompany;

    @Column(name = "notes", nullable = false, length = 3000)
    private String notes;

    @Column(name = "segment_notes", nullable = false, length = 3000)
    private String segmentNotes;

    @Column(name = "modified_date", nullable = false, length = 20)
    private String modifiedDate;

    @Column(name = "net_sales", nullable = true)
    private Double netSales;

    @Column(name = "total_income", nullable = true)
    private Double totalIncome;

    @Column(name = "total_expenses", nullable = true)
    private Double totalExpenses;

    @Column(name = "operating_profit", nullable = true)
    private Double operatingProfit;

    @Column(name = "ebitda", nullable = true)
    private Double ebitda;

    @Column(name = "other_adjustments", nullable = true)
    private Double otherAdjustments;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

}


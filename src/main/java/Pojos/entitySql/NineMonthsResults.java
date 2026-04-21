package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "nine_months_results")
public class NineMonthsResults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nine_months_results_id")
    private Integer NineMonthResultsId;

    @Column(name = "company_code", nullable = false)
    private double companyCode;

    @Column(name = "year_ending1", nullable = false)
    private short yearEnding1;

    @Column(name = "months", nullable = false)
    private byte months;

    @Column(name = "nine", nullable = false)
    private byte nine;

    @Column(name = "operating_income")
    private Double operatingIncome;

    @Column(name = "other_operating_income", nullable = false)
    private double otherOperatingIncome;

    @Column(name = "total_income_from_operations", nullable = false)
    private double totalIncomeFromOperations;

    @Column(name = "int_or_disc_on_adv_or_bills", nullable = false)
    private double intOrDiscOnAdvOrBills;

    @Column(name = "income_on_investment", nullable = false)
    private double incomeOnInvestment;

    @Column(name = "int_on_balances_with_rbi", nullable = false)
    private double intOnBalancesWithRBI;
    
    @Column(name = "others", nullable = false)
    private double others;

    @Column(name = "other_recurring_income", nullable = false)
    private double otherRecurringIncome;

    @Column(name = "stock_adjustment", nullable = false)
    private double stockAdjustment;

    @Column(name = "raw_material_consumed", nullable = false)
    private double rawMaterialConsumed;

    @Column(name = "purchase_of_traded_goods", nullable = false)
    private double purchaseOfTradedGoods;

    @Column(name = "power_and_fuel", nullable = false)
    private double powerAndFuel;

    @Column(name = "employee_expenses", nullable = false)
    private double employeeExpenses;

    @Column(name = "excise", nullable = false)
    private double excise;

    @Column(name = "admin_and_selling_expenses", nullable = false)
    private double adminAndSellingExpenses;

    @Column(name = "research_and_devlopment_expenses", nullable = false)
    private double researchAndDevelopmentExpenses;


    @Column(name = "expenses_capitalised", nullable = false)
    private double expensesCapitalised;

    @Column(name = "other_expeses", nullable = false)
    private double otherExpeses;

    @Column(name = "pl_before_other_inc_int_excp_item_tax", nullable = false)
    private double plBeforeOtherIncIntExcpItemTax;

    @Column(name = "pl_before_int_excp_item_tax", nullable = false)
    private double plBeforeIntExcpItemTax;

    @Column(name = "interest_charges", nullable = false)
    private double interestCharges;

    @Column(name = "pl_before_excp_item_tax", nullable = false)
    private double plBeforeExcpItemTax;

    @Column(name = "exceptional_items", nullable = false)
    private double exceptionalItems;

    @Column(name = "depreciation", nullable = false)
    private double depreciation;

    @Column(name = "operating_profit_before_provisions_and_contingencies", nullable = false)
    private double operatingProfitBeforeProvisionsAndContingencies;

    @Column(name = "bank_provisions_made", nullable = false)
    private double bankProvisionsMade;

    @Column(name = "pl_before_tax", nullable = false)
    private double plBeforeTax;

    @Column(name = "tax_charges", nullable = false)
    private double taxCharges;

    @Column(name = "pl_after_tax_from_ordinery_activities", nullable = false)
    private double plAfterTaxFromOrdinaryActivities;

    @Column(name = "extra_ordinary_item", nullable = false)
    private double extraOrdinaryItem;

    @Column(name = "reported_pat", nullable = false)
    private double reportedPat;

    @Column(name = "prior_year_adj", nullable = false)
    private double priorYearAdj;

    @Column(name = "reserves_written_back", nullable = false)
    private double reservesWrittenBack;

    @Column(name = "equity_capital", nullable = false)
    private double equityCapital;

    @Column(name = "reserves_and_surplus", nullable = false)
    private double reservesAndSurplus;

    @Column(name = "eq_dividend_rate", nullable = false)
    private double eqDividendRate;

    @Column(name = "aggregate_of_non_promoto_no_of_shares", nullable = false)
    private double aggregateOfNonPromotoNoOfShares;

    @Column(name = "aggregate_of_non_promoto_holding_percent", nullable = false)
    private double aggregateOfNonPromotoHoldingPercent;

    @Column(name = "government_share", nullable = false)
    private double governmentShare;

    @Column(name = "capital_adequacy_ratio", nullable = false)
    private double capitalAdequacyRatio;

    @Column(name = "capital_adequacy_baseII", nullable = false)
    private double capitalAdequacyBaseII;

    @Column(name = "gross_npa", nullable = false)
    private double grossNPA;

    @Column(name = "net_npa", nullable = false)
    private double netNPA;

    @Column(name = "percentage_of_gross_npa", nullable = false)
    private double percentageOfGrossNPA;

    @Column(name = "percentage_of_net_npa", nullable = false)
    private double percentageOfNetNPA;

    @Column(name = "return_on_assets_per", nullable = false)
    private double returnOnAssetsPer;

    @Column(name = "before_basic_eps", nullable = false)
    private double beforeBasicEPS;

    @Column(name = "before_diluted_eps", nullable = false)
    private double beforeDilutedEPS;

    @Column(name = "after_basic_eps", nullable = false)
    private double afterBasicEPS;

    @Column(name = "after_diluted_eps", nullable = false)
    private double afterDilutedEPS;

    @Column(name = "en_number_of_shares", nullable = false)
    private double enNumberOfShares;

    @Column(name = "en_per_share_as_per_of_tot_sh_hol_of_pro_and_group", nullable = false)
    private double enPerShareAsPerOfTotShHolOfProAndGroup;

    @Column(name = "en_per_share_as_per_of_tot_sh_cap_of_company", nullable = false)
    private double enPerShareAsPerOfTotShCapOfCompany;

    @Column(name = "non_en_number_of_shares", nullable = false)
    private double nonEnNumberOfShares;

    @Column(name = "non_en_per_share_as_per_of_tot_sh_hol_of_pro_and_group", nullable = false)
    private double nonEnPerShareAsPerOfTotShHolOfProAndGroup;

    @Column(name = "non_en_per_share_as_per_of_tot_sh_cap_of_company", nullable = false)
    private double nonEnPerShareAsPerOfTotShCapOfCompany;

    @Column(name = "notes", nullable = false, length = 3000)
    private String notes;

    @Column(name = "segment_notes", nullable = false, length = 3000)
    private String segmentNotes;

    @Column(name = "modified_date", nullable = false, length = 20)
    private String modifiedDate;

    @Column(name = "net_sales")
    private Double netSales;

    @Column(name = "total_income")
    private Double totalIncome;

    @Column(name = "total_expenses")
    private Double totalExpenses;

    @Column(name = "operating_profit")
    private Double operatingProfit;

    @Column(name = "ebitda")
    private Double ebitda;

    @Column(name = "other_adjustments")
    private Double otherAdjustments;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    
}



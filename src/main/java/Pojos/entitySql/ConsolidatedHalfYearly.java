package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "consolidated_half_yearly")
public class ConsolidatedHalfYearly {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "company_code")
    private Double companyCode;

    @Column(name = "year_ending1", length = 20)
    private String yearEnding1;

    @Column(name = "months")
    private Integer months;

    @Column(name = "half")
    private Integer half;

    @Column(name = "operating_income")
    private Double operatingIncome;

    @Column(name = "other_operating_income")
    private Double otherOperatingIncome;

    @Column(name = "total_income_from_operations")
    private Double totalIncomeFromOperations;

    @Column(name = "int_or_disc_on_adv_or_bills")
    private Double intOrDiscOnAdvOrBills;

    @Column(name = "income_on_investment")
    private Double incomeOnInvestment;

    @Column(name = "int_on_balance_with_rbi")
    private Double intOnBalanceWithRBI;

    @Column(name = "others")
    private Double others;

    @Column(name = "other_recurring_income")
    private Double otherRecurringIncome;

  @Column(name = "stock_adjusment")
  private Double stockAdjustment;

    @Column(name = "raw_material_consumed")
    private Double rawMaterialConsumed;

    @Column(name = "purchase_of_traded_goods")
    private Double purchaseOfTradedGoods;

    @Column(name = "power_and_fuel")
    private Double powerAndFuel;

    @Column(name = "employee_expenses")
    private Double employeeExpenses;

    @Column(name = "excise")
    private Double excise;

    @Column(name = "admin_and_selling_expenses")
    private Double adminAndSellingExpenses;

    @Column(name = "research_and_development_expenses")
    private Double researchAndDevelopmentExpenses;

    @Column(name = "expenses_capitalised")
    private Double expensesCapitalised;

    @Column(name = "other_expenses")
    private Double otherExpenses;

    @Column(name = "pl_before_other_inc_excp_item_tax")
    private Double plBeforeOtherIncExcpItemTax;

    @Column(name = "pl_before_int_excp_item_tax")
    private Double plBeforeIntExcpItemTax;

    @Column(name = "interest_charges")
    private Double interestCharges;

    @Column(name = "pl_before_excp_item_tax")
    private Double plBeforeExcpItemTax;

    @Column(name = "exceptional_items")
    private Double exceptionalItems;

    @Column(name = "depreciation")
    private Double depreciation;

    @Column(name = "operating_profit_before_provisions_and_contingencies")
    private Double operatingProfitBeforeProvisionsAndContingencies;

    @Column(name = "bank_provisions_made")
    private Double bankProvisionsMade;

    @Column(name = "pl_before_tax")
    private Double plBeforeTax;

    @Column(name = "tax_charges")
    private Double taxCharges;

    @Column(name = "pl_after_tax_from_ordinery_activities")
    private Double plAfterTaxFromOrdinaryActivities;

    @Column(name = "extra_ordinery_items")
    private Double extraOrdinaryItems;

    @Column(name = "reported_pat")
    private Double reportedPat;

    @Column(name = "minority_interest")
    private Double minorityInterest;

    @Column(name = "share_of_pl_of_associates")
    private Double shareOfPlOfAssociates;

    @Column(name = "net_pl_after_mi_associates")
    private Double netPlAfterMiAssociates;

    @Column(name = "cost_of_investment_in_subsidiary")
    private Double costOfInvestmentInSubsidiary;

    @Column(name = "prior_year_adj")
    private Double priorYearAdj;

    @Column(name = "reserves_written_back")
    private Double reservesWrittenBack;

    @Column(name = "equity_capital")
    private Double equityCapital;

    @Column(name = "reserves_and_surplus")
    private Double reservesAndSurplus;

    @Column(name = "eq_devidend_rate")
    private Double eqDevidendRate;

    @Column(name = "aggregate_of_non_promoto_no_of_shares")
    private Double aggregateOfNonPromotoNoOfShares;

    @Column(name = "aggregate_of_non_promoto_holding_percent")
    private Double aggregateOfNonPromotoHoldingPercent;

    @Column(name = "government_share")
    private Double governmentShare;

    @Column(name = "capital_adequacy_ratio")
    private Double capitalAdequacyRatio;

    @Column(name = "capital_adequacy_base_ii")
    private Double capitalAdequacyBaseIi;

    @Column(name = "gross_npa")
    private Double grossNPA;

    @Column(name = "net_npa")
    private Double netNPA;

    @Column(name = "per_of_gross_npa")
    private Double perOfGrossNPA;

    @Column(name = "per_of_net_npa")
    private Double perOfNetNPA;

    @Column(name = "return_on_assets_per")
    private Double returnOnAssetsPer;

    @Column(name = "before_basic_eps")
    private Double beforeBasicEps;

    @Column(name = "as_before_diluted_eps")
    private Double asBeforeDilutedEps;

    @Column(name = "after_basic_eps")
    private Double afterBasicEps;

    @Column(name = "after_diluted_eps")
    private Double afterDilutedEps;

    @Column(name = "en_number_of_shares")
    private Double enNumberOfShares;

    @Column(name = "en_per_share_as_per_of_tot_sh_hol_of_pro_and_group")
    private Double enPerShareAsPerOfTotShHolOfProAndGroup;

    @Column(name = "en_per_share_as_per_of_tot_sh_cap_of_company")
    private Double enPerShareAsPerOfTotShCapOfCompany;

    @Column(name = "non_en_number_of_shares")
    private Double nonEnNumberOfShares;

    @Column(name = "non_en_per_share_as_per_of_tot_sh_hol_of_pro_and_group")
    private Double nonEnPerShareAsPerOfTotShHolOfProAndGroup;

    @Column(name = "non_en_per_share_as_per_of_tot_sh_cap_of_company")
    private Double nonEnPerShareAsPerOfTotShCapOfCompany;

    @Column(name = "notes")
    private Double notes;

    @Column(name = "segment_notes")
    private Double segmentNotes;

    @Column(name = "modified_date", length = 20)
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

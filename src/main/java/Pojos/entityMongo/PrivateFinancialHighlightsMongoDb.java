package Pojos.entityMongo;


import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data

@Document(collection = "PrivateFinancialHighlightsMongoDb")
    public class PrivateFinancialHighlightsMongoDb {

    @Id
    private int id;
    private double companyid;
    private String yearend;
    private String entityType;
    private String financial_type;
    private float conversion_factor;
    private String financial_format;
    private int updated_on;
    private Integer from_date;
    private Integer to_date;
    private String reporting_standard;
    private double gross_block;
    private double property_plant_and_equipment;
    private double capital_work_in_progress;
    private double investment_property;
    private double goodwill;
    private double other_intangible_assets;
    private double intangible_assets_under_development;
    private double bearer_plants;
    private double biological_assets;
    private double investments_accounted_for_using_equity_method;
    private double non_current_investments;
    private double trade_receivables_non_current;
    private double loans_non_current;
    private double other_non_current_financial_assets;
    private double deferred_tax_assets_net;
    private double other_non_current_assets;
    private double total_non_current_assets;
    private double inventories;
    private double current_investments;
    private double trade_receivables_current;
    private double cash_and_cash_equivalents;
    private double bank_balance_other_than_cash_and_cash_equivalents;
    private double loans_current;
    private double other_current_financial_assets;
    private double current_tax_assets;
    private double other_current_assets;
    private double total_current_assets;
    private double non_current_assets_classified_as_held_for_sale;
    private double regulatory_deferral_account_debit_balances_and_related_deferred;
    private double other_assets_values;
    private String other_assets_names;
    private double total_assets;
    private double equity_share_capital;
    private double other_equity;
    private double non_controlling_interest;
    private double networth;
    private double total_equity;
    private double borrowings_non_current;
    private double trade_payables_non_current;
    private double other_non_current_financial_liabilities;
    private double provisions_non_current;
    private double deferred_tax_liabilities_net;
    private double deferred_government_grants_non_current;
    private double other_non_current_liabilities;
    private double total_non_current_liabilities;
    private double borrowings_current;
    private double trade_payables_current;
    private double other_current_financial_liabilities;
    private double other_current_liabilities;
    private double provisions_current;
    private double current_tax_liabilities;
    private double deferred_government_grants_current;
    private double total_current_liabilities;
    private double liabilities_directly_associated_with_assets_in_disposal_group_cl;
    private double regulatory_deferral_account_credit_balances_and_related_deferred;
    private double other_liabilities_values;
    private String other_liablities_name;
    private double total_liabilities;
    private double total_equity_and_liabilities;
    private double net_sales;
    private double other_income;
    private double total_income;
    private double total_expenditure_pl;
    private double depreciation;
    private double interest;
    private double total_expenditure;
    private double ebitda;
    private double ebit;
    private double pbt;
    private double tax;
    private double pat;
    private double exceptional_items;
    private double prior_period_items;
    private double cash_flow_operations;
    private double cash_flow_investing;
    private double cash_flow_financing;
    private double free_cash_flow;
    private double total_preferred;
    private double shares_os;
    private double shares_os_fully_diluted;
    private double eps_basic;
    private double eps_diluted;
    private double dividend;
    private double debt_equity;
    private double current_ratio;
    private double roce;
    private double ronw;
    private double ebidta_margin;
    private double pat_margin;
    private double cpm;
    private double acid_ratio;
    private double debt_to_asset_ratio;
    private double asset_turnover_ratio;
    private double cash_flow_coverage_ratio;
    private double operation_cash_flow_margin;
    private double cash_ratio;
    private double inventory_turnover_ratio;
    private double working_capital_turnover_ratio;
    private double return_on_assets_ratio;
    private double operating_profit_margin;
    private double cost_of_material_consumed;
    private double purchase_of_stock_in_trade;
    private double change_in_inventories_of_stock_in_trade;
    private double return_on_equity;
    private int priority_check;
    private String pfh_discription;
    private String updated_at;
    private String created_at;
    private double change_in_inventories_of_finished_goods;
    private double employee_benefit_expenses;
    private double managerial_remuneration;
    private double payment_to_auditors;
    private double insurance_expenses;
    private double power_and_fuel;
    private double other_expenses;
    private double csr_expenditure;
    private double change_in_inventories_of_work_in_progress;

    // Constructors, getters, and setters can be added here
    }



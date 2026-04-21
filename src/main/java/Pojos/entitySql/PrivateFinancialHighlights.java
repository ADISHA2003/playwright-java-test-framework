package Pojos.entitySql;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "private_financials_2017")
public class PrivateFinancialHighlights {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column
        private double companyid;

        @Column(length = 255)
        private String yearend;

        @Column(length = 255)
        private String financial_type;

        @Column
        private float conversion_factor;

        @Column(length = 255)
        private String financial_format;

        @Column
        private int updated_on;

        @Column
        private int from_date;

        @Column
        private int to_date;

        @Column(length = 255)
        private String reporting_standard;

        @Column
        private double gross_block;

        @Column
        private double property_plant_and_equipment;

        @Column
        private double capital_work_in_progress;

        @Column
        private double investment_property;

        @Column
        private double goodwill;

        @Column
        private double other_intangible_assets;

        @Column
        private double intangible_assets_under_development;

        @Column
        private double bearer_plants;

        @Column
        private double biological_assets;

        @Column
        private double investments_accounted_for_using_equity_method;

        @Column
        private double non_current_investments;

        @Column
        private double trade_receivables_non_current;

        @Column
        private double loans_non_current;

        @Column
        private double other_non_current_financial_assets;

        @Column
        private double deferred_tax_assets_net;

        @Column
        private double other_non_current_assets;

        @Column
        private double total_non_current_assets;

        @Column
        private double inventories;

        @Column
        private double current_investments;

        @Column
        private double trade_receivables_current;

        @Column
        private double cash_and_cash_equivalents;

        @Column
        private double bank_balance_other_than_cash_and_cash_equivalents;

        @Column
        private double loans_current;

        @Column
        private double other_current_financial_assets;

        @Column
        private double current_tax_assets;

        @Column
        private double other_current_assets;

        @Column
        private double total_current_assets;

        @Column
        private double non_current_assets_classified_as_held_for_sale;

        @Column
        private double regulatory_deferral_account_debit_balances_and_related_deferred;

        @Column
        private double other_assets_values;

        @Column(length = 255)
        private String other_assets_names;

        @Column
        private double total_assets;

        @Column
        private double equity_share_capital;

        @Column
        private double other_equity;

        @Column
        private double non_controlling_interest;

        @Column
        private double networth;

        @Column
        private double total_equity;

        @Column
        private double borrowings_non_current;

        @Column
        private double trade_payables_non_current;

        @Column
        private double other_non_current_financial_liabilities;

        @Column
        private double provisions_non_current;

        @Column
        private double deferred_tax_liabilities_net;

        @Column
        private double deferred_government_grants_non_current;

        @Column
        private double other_non_current_liabilities;

        @Column
        private double total_non_current_liabilities;



        @Column
        private Double borrowingsCurrent;

        @Column
        private Double tradePayablesCurrent;

        @Column
        private Double otherCurrentFinancialLiabilities;

        @Column
        private Double otherCurrentLiabilities;

        @Column
        private Double provisionsCurrent;

        @Column
        private Double currentTaxLiabilities;

        @Column
        private Double deferredGovernmentGrantsCurrent;

        @Column
        private Double totalCurrentLiabilities;

        @Column
        private Double liabilitiesDirectlyAssociatedWithAssetsInDisposalGroupCl;

        @Column
        private Double regulatoryDeferralAccountCreditBalancesAndRelatedDeferred;

        @Column
        private Double otherLiabilitiesValues;

        @Column(length = 255)
        private String otherLiabilitiesName;

        @Column
        private Double totalLiabilities;

        @Column
        private Double totalEquityAndLiabilities;

        @Column
        private Double netSales;

        @Column
        private Double otherIncome;

        @Column
        private Double totalIncome;

        @Column
        private Double totalExpenditurePl;

        @Column
        private Double depreciation;

        @Column
        private Double interest;

        @Column
        private Double totalExpenditure;

        @Column
        private Double ebitda;

        @Column
        private Double ebit;

        @Column
        private Double pbt;

        @Column
        private Double tax;

        @Column
        private Double pat;

        @Column
        private Double exceptionalItems;

        @Column
        private Double priorPeriodItems;

        @Column
        private Double cashFlowOperations;

        @Column
        private Double cashFlowInvesting;

        @Column
        private Double cashFlowFinancing;

        @Column
        private Double freeCashFlow;

        @Column
        private Double totalPreferred;

        @Column
        private Double sharesOs;

        @Column
        private Double sharesOsFullyDiluted;

        @Column
        private Double epsBasic;

        @Column
        private Double epsDiluted;

        @Column
        private Double dividend;

        @Column
        private Double debtEquity;

        @Column
        private Double currentRatio;

        @Column
        private Double roce;

        @Column
        private Double ronw;

        @Column
        private Double ebitdaMargin;

        @Column
        private Double patMargin;

        @Column
        private Double cpm;

        @Column
        private Double acidRatio;

        @Column
        private Double debtToAssetRatio;

        @Column
        private Double assetTurnoverRatio;

        @Column
        private Double cashFlowCoverageRatio;

        @Column
        private Double operationCashFlowMargin;

        @Column
        private Double cashRatio;

        @Column
        private Double inventoryTurnoverRatio;

        @Column
        private Double workingCapitalTurnoverRatio;

        @Column
        private Double returnOnAssetsRatio;

        @Column
        private Double operatingProfitMargin;

        @Column
        private Double costOfMaterialConsumed;

        @Column
        private Double purchaseOfStockInTrade;

        @Column
        private Double changeInInventoriesOfStockInTrade;

        @Column
        private Double returnOnEquity;

        @Column
        private Integer priorityCheck;

        @Column(length = 255)
        private String pfhDiscription;


        @Column
        private LocalDateTime updatedAt;

        @Column
        private LocalDateTime createdAt;

        @Column
        private Double changeInInventoriesOfFinishedGoods;

        @Column
        private Double employeeBenefitExpenses;

        @Column
        private Double managerialRemuneration;

        @Column
        private Double paymentToAuditors;

        @Column
        private Double insuranceExpenses;

        @Column
        private Double powerAndFuel;

        @Column
        private Double otherExpenses;

        @Column
        private Double csrExpenditure;

        @Column
        private Double changeInInventoriesOfWorkInProgress;

        // Constructors, getters, and setters


}

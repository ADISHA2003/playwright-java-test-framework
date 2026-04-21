package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "deal_multiples")
public class DealMultiple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deal_multiple_id")
    private int dealMultipleId;

    @Column(name = "transactionid")
    private Integer transactionId;

    @Column(name = "transactiontype")
    private Integer transactionType;

    @Column(name = "transaction_date")
    private String transactionDate;

    @Column(name = "targetcompanyid")
    private Integer targetCompanyId;

    @Column(name = "sector_id")
    private Integer sectorId;

    @Column(name = "industry_group_id")
    private Integer industryGroupId;

    @Column(name = "industry_id")
    private Integer industryId;

    @Column(name = "sub_industry_id")
    private Integer subIndustryId;

    @Column(name = "implied_equity_value")
    private Double impliedEquityValue;

    @Column(name = "implied_enterprise_value")
    private Double impliedEnterpriseValue;

    @Column(name = "price")
    private Double price;

    @Column(name = "deal_value")
    private Double dealValue;

    @Column(name = "financial_year")
    private Integer financialYear;

    @Column(name = "minority_interset")
    private Double minorityInterest;

    @Column(name = "total_debt")
    private Double totalDebt;

    @Column(name = "cash_bank_balance")
    private Double cashBankBalance;

    @Column(name = "total_preferred")
    private Double totalPreferred;

    @Column(name = "total_income")
    private Double totalIncome;

    @Column(name = "total_assets")
    private Double totalAssets;

    @Column(name = "ebitda")
    private Double ebitda;

    @Column(name = "pbt")
    private Double pbt;

    @Column(name = "ebit")
    private Double ebit;

    @Column(name = "pat")
    private Double pat;

    @Column(name = "eps")
    private Double eps;

    @Column(name = "book_value")
    private Double bookValue;

    @Column(name = "p_e")
    private Double pe;

    @Column(name = "equityvalue_bookvalue")
    private Double equityValueBookValue;

    @Column(name = "ev_totalincome")
    private Double evTotalIncome;

    @Column(name = "ev_ebitda")
    private Double evEbitda;

    @Column(name = "ev_totalassets")
    private Double evTotalAssets;

    @Column(name = "ev_ebit")
    private Double evEbit;

    @Column(name = "ev_pbt")
    private Double evPbt;

    @Column(name = "ev_pat")
    private Double evPat;

    @Column(name = "implied_equity_value_usd")
    private Double impliedEquityValueUsd;

    @Column(name = "implied_enterprise_value_usd")
    private Double impliedEnterpriseValueUsd;

    @Column(name = "percentage_sought")
    private Double percentageSought;

    @Column(name = "financial_type")
    private String financialType;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_at")
    private Date createdAt;

    // Getters and Setters
}
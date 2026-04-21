package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "cash_flow")
public class CashFlow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cash_flow_id")
    private Integer cashFlowId;

    @Column(name = "company_code", nullable = false)
    private Double companyCode;

    @Column(name = "year_ending", length = 20, nullable = false)
    private String yearEnding;

    @Column(name = "months")
    private Integer months;

    @Column(name = "type", length = 5)
    private String type;

    @Column(name = "pbt")
    private Double pbt;

    @Column(name = "pat")
    private Double pat;

    @Column(name = "depreciatn")
    private Double depreciation;

    @Column(name = "fin_lease")
    private Double finLease;

    @Column(name = "lease_eq_res")
    private Double leaseEqRes;

    @Column(name = "pl_in_forex")
    private Double plInForex;

    @Column(name = "gain_forex")
    private Double gainForex;

    @Column(name = "pl_sale_asst")
    private Double plSaleAsst;

    @Column(name = "pl_sale_invs")
    private Double plSaleInvs;

    @Column(name = "p_adj_s_undrt")
    private Double pAdjSUndrt;

    @Column(name = "intrst_inc")
    private Double interestInc;

    @Column(name = "int_paid_net")
    private Double intPaidNet;

    @Column(name = "intrst_net")
    private Double interestNet;

    @Column(name = "dvdnd_rec_op")
    private Double dividendRecOp;

    @Column(name = "dvdnd_net")
    private Double dividendNet;

    @Column(name = "invstmts")
    private Double investments;

    @Column(name = "misc_income")
    private Double miscIncome;

    @Column(name = "amrtsatn_op")
    private Double amortizationOp;

    @Column(name = "asst_w_off")
    private Double assetWOff;

    @Column(name = "misc_exp")
    private Double miscExp;

    @Column(name = "paymtto_vrs")
    private Double paymentToVrs;

    @Column(name = "prov_w_off_nt")
    private Double provWOffNt;

    @Column(name = "prov_graty")
    private Double provGraty;

    @Column(name = "prov_dim_inv")
    private Double provDimInv;

    @Column(name = "prov_bdnpa")
    private Double provBdnpa;

    @Column(name = "trd_recvbl")
    private Double tradeReceivables;

    @Column(name = "trd_bill_pur")
    private Double tradeBillPur;

    @Column(name = "invnt_oprt")
    private Double inventoryOperation;

    @Column(name = "trd_payble")
    private Double tradePayables;

    @Column(name = "tax_provisn")
    private Double taxProvision;

    @Column(name = "dirct_taxes")
    private Double directTaxes;

    @Column(name = "adv_tax_paid")
    private Double advanceTaxPaid;

    @Column(name = "loan_advanc")
    private Double loanAdvance;

    @Column(name = "trans_resrv")
    private Double transferReserve;

    @Column(name = "oth_oprt_act")
    private Double otherOperatingAct;

    @Column(name = "pr_yr_adjust")
    private Double priorYearAdjust;

    @Column(name = "prv_writ_bck")
    private Double provWriteBack;

    @Column(name = "prior_yr_tax")
    private Double priorYearTax;

    @Column(name = "bal_writ_bck")
    private Double balanceWriteBack;

    @Column(name = "oth_assets")
    private Double otherAssets;

    @Column(name = "oth_liablt")
    private Double otherLiabilities;

    @Column(name = "ch_in_depsit")
    private Double changeInDeposit;

    @Column(name = "ch_in_borr")
    private Double changeInBorrowing;

    @Column(name = "disc_exp_ln")
    private Double discountExpenseLn;

    @Column(name = "inc_dec_advn")
    private Double increaseDecreaseAdvance;

    @Column(name = "inc_dec_invs")
    private Double increaseDecreaseInvs;

    @Column(name = "net_stk_hire")
    private Double netStockHire;

    @Column(name = "l_ast_net_sal")
    private Double lastNetSale;

    @Column(name = "exces_dep_wb")
    private Double excessDepWriteBack;

    @Column(name = "prem_ls_land")
    private Double premiumLeaseLand;

    @Column(name = "extr_ord_itm")
    private Double extraOrdinaryItem;

    @Column(name = "net_c_flow_op")
    private Double netCashFlowOp;

    @Column(name = "pur_fix_asst")
    private Double purchaseFixedAsset;

    @Column(name = "sal_fix_asst")
    private Double saleFixedAsset;

    @Column(name = "capital_wip")
    private Double capitalWorkInProgress;

    @Column(name = "cap_subs_rec")
    private Double capitalSubsRec;

    @Column(name = "pur_invst")
    private Double purchaseInvestment;

    @Column(name = "sal_invst_i_a")
    private Double saleInvestmentInIA;

    @Column(name = "aqustn_comp")
    private Double acquisitionCompany;

    @Column(name = "sal_u_t_exItm")
    private Double saleUnderTransExtraItem;

    @Column(name = "int_recd")
    private Double interestReceived;

    @Column(name = "dvnd_recd_i_a")
    private Double dividendReceivedInIA;

    @Column(name = "invst_incm")
    private Double investmentIncome;

    @Column(name = "intr_crp_dep")
    private Double intraCorpDep;

    @Column(name = "inv_in_subs")
    private Double investmentInSubs;

    @Column(name = "loan_to_subs")
    private Double loanToSubs;

    @Column(name = "inv_in_grp_co")
    private Double investmentInGroupCo;

    @Column(name = "iss_sh_acq_co")
    private Double issueSharesAcquiredCompany;

    @Column(name = "canc_inv_cos")
    private Double cancelInvestedCos;

    @Column(name = "cert_dep_bnk")
    private Double certifiedDepBank;

    @Column(name = "movmt_loans")
    private Double movementLoans;

    @Column(name = "oth_inv_act")
    private Double otherInvestmentAct;

    @Column(name = "movmt_wcap")
    private Double movementWorkingCapital;

    @Column(name = "amrt_exp_i_a")
    private Double amortizationExpIA;

    @Column(name = "taxes_paid")
    private Double taxesPaid;

    @Column(name = "exp_captlsd")
    private Double expensesCapitalized;

    @Column(name = "ex_ord_itm_i_a")
    private Double extraOrdinaryItemInIA;

    @Column(name = "p_fix_ast_l_o")
    private Double purchaseFixedAssetLongOrder;

    @Column(name = "nt_i_d_cur_ast")
    private Double netIDCurrentAsset;

    @Column(name = "net_i_d_advn")
    private Double netIDAdvance;

    @Column(name = "nt_i_d_cur_lib")
    private Double netIDCurrentLiabilities;

    @Column(name = "nt_csh_in_i_a")
    private Double netCashInIA;

    @Column(name = "pr_iss_eq_cap")
    private Double priorIssuedEquityCapital;

    @Column(name = "pr_iss_pr_cap")
    private Double priorIssuedPreferredCapital;

    @Column(name = "pr_iss_sh_prem")
    private Double priorIssuedSharePremium;

    @Column(name = "redmtn_cap")
    private Double redemptionCapital;

    @Column(name = "proc_iss_deb")
    private Double proceedsIssueDebenture;

    @Column(name = "pr_bnk_borr")
    private Double priorBankBorrowing;

    @Column(name = "pr_l_trm_borr")
    private Double priorLongTermBorrowing;

    @Column(name = "pr_sh_trm_bor")
    private Double priorShortTermBorrowing;

    @Column(name = "pr_deposit")
    private Double priorDeposit;

    @Column(name = "repmt_borr")
    private Double repaymentBorrowing;

    @Column(name = "sh_applictn")
    private Double shareApplication;

    @Column(name = "ln_corp_body")
    private Double loanCorporateBody;

    @Column(name = "dvdnd_paid")
    private Double dividendPaid;

    @Column(name = "int_paid")
    private Double interestPaid;

    @Column(name = "fin_chrgs")
    private Double financeCharges;

    @Column(name = "csh_crdt_adv")
    private Double cashCreditAdvance;

    @Column(name = "csh_cap_invs")
    private Double cashCapitalInvestment;

    @Column(name = "oth_fin_act")
    private Double otherFinancialActivity;

    @Column(name = "f_e_gn_los_f_a")
    private Double foreignExchangeGainLossFA;

    @Column(name = "sh_premium")
    private Double sharePremium;

    @Column(name = "mis_exp_w_off")
    private Double misExpenseWriteOff;

    @Column(name = "sal_inv_f_a")
    private Double saleInvestmentFA;

    @Column(name = "reserves")
    private Double reserves;

    @Column(name = "curr_liab")
    private Double currentLiabilities;

    @Column(name = "ln_disburse")
    private Double loanDisbursement;

    @Column(name = "invntry_f_a")
    private Double inventoryFA;

    @Column(name = "ex_ord_itm_f_a")
    private Double extraOrdinaryItemFA;

    @Column(name = "dfrd_exp_bor")
    private Double deferredExpenseBorrowing;

    @Column(name = "sh_aplc_rfnd")
    private Double shareApplicationRefund;

    @Column(name = "on_redem_deb")
    private Double onRedemptionDebenture;

    @Column(name = "of_oth_l_t_bor")
    private Double offOtherLongTermBorrowing;

    @Column(name = "of_sh_trm_bor")
    private Double offShortTermBorrowing;

    @Column(name = "of_fin_l_liab")
    private Double offFinancialLongLiabilities;

    @Column(name = "shltr_a_res")
    private Double shelterARes;

    @Column(name = "rep_s_trm_bor")
    private Double repaidShortTermBorrowing;

    @Column(name = "rep_l_trm_bor")
    private Double repaidLongTermBorrowing;

    @Column(name = "nt_csh_usd_f_a")
    private Double netCashUsedFA;

    @Column(name = "f_e_gn_ls_nt_f_a")
    private Double foreignExchangeGainLossNetFA;

    @Column(name = "n_inc_dec_csh")
    private Double netIncreaseDecreaseCash;

    @Column(name = "csh_strt_year")
    private Double cashStartYear;

    @Column(name = "csh_end_year")
    private Double cashEndYear;

    @Column(name = "modified_date", length = 20)
    private String modifiedDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    
}

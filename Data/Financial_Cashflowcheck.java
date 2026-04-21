package VCI.Data;

	
	import java.sql.*;
	import java.time.DateTimeException;
	import java.time.LocalDate;
	import java.util.Arrays;
	import java.util.HashMap;
	import java.util.HashSet;
	import java.util.Map;
	import java.util.Set;

	import com.mongodb.client.MongoCollection;
	import com.mongodb.client.MongoDatabase;
	import utilities.MongoDBHandler;
	import utilities.MySQLDBHandler;
	import org.bson.Document;
	import org.testng.annotations.Test;

			public class Financial_Cashflowcheck {

			    @Test
			    public void Cashflowcomparison() {
			    	 Connection connection = null;
			    	
			    	 try {
			             connection = MySQLDBHandler.getConnection();
			             String sqlQuery = "SELECT * FROM `cash_flow` " ;
			            PreparedStatement mysqlStmt = connection.prepareStatement(sqlQuery);
			            ResultSet mysqlResultSet = mysqlStmt.executeQuery();
		
			            Map<Integer, Map<String, Object>> resultMap = new HashMap<>();
			            ResultSetMetaData metaData = mysqlResultSet.getMetaData();
			            int columnCount = metaData.getColumnCount();

			            while (mysqlResultSet.next()) {
			                int primaryKey = mysqlResultSet.getInt(1);
			                Map<String, Object> rowMap = new HashMap<>();

			                for (int i = 2; i <= columnCount; i++) {
			                    String columnName = metaData.getColumnName(i);
			                    Object columnValue = null; 

			                    try { 
			                        columnValue = mysqlResultSet.getObject(i); 

			                        if (columnValue != null && columnValue instanceof java.sql.Date) { 
			                            java.sql.Date dateValue = (java.sql.Date) columnValue; 
			                            
			                            LocalDate localDate = dateValue.toLocalDate();
			                            
			                            if (localDate.getYear() == 0 || localDate.getMonthValue() == 0 || localDate.getDayOfMonth() == 0) {
			                                columnValue = null;  
			                                }
			                        } 
			                    } catch (SQLException e) { 
			                        if ("Zero date value prohibited".equals(e.getMessage())) { 
			                            columnValue = null; 
			                            } else { 
			                            e.printStackTrace(); 
			                        } 
			                    } catch (DateTimeException ex) {
			                       
			                        columnValue = null;
			                        
			                    }
			                 
			                    String mappedFieldName = mapFieldName(columnName);
			                    rowMap.put(mappedFieldName, columnValue);
			                } 
			                
			            
			                resultMap.put(primaryKey, rowMap);
			            }


			            
			            MongoDBHandler mongoDBConnection = new MongoDBHandler();
			            
			            MongoDatabase database = mongoDBConnection.getDatabase();

			            MongoCollection<Document> mongoCollection = database.getCollection("CashFlowMongoDb");
			            
			            int mysqlRecordCount = resultMap.size();
			            System.out.println("MySQL Record Count: " + mysqlRecordCount);

			            long mongoRecordCount = mongoCollection.countDocuments();
			            System.out.println("MongoDB Record Count: " + mongoRecordCount);

			            if (mysqlRecordCount == mongoRecordCount) {
			                System.out.println("Record counts match between MySQL and MongoDB.");
			            } else {
			                System.out.println("Record counts do not match between MySQL and MongoDB.");
			                System.out.println("MySQL Record Count: " + mysqlRecordCount);
			                System.out.println("MongoDB Record Count: " + mongoRecordCount);
			            }

			            for (Map.Entry<Integer, Map<String, Object>> entry : resultMap.entrySet()) {
			                Integer primaryKeyMysql = entry.getKey();
			                Map<String, Object> mysqlRow = entry.getValue();

			                Document doc = mongoCollection.find(new Document("cashFlowId", primaryKeyMysql)).first();

			                if (doc != null) {
			                    for (Map.Entry<String, Object> mysqlEntry : mysqlRow.entrySet()) {
			                        String fieldName = mysqlEntry.getKey();
			                        Object mysqlValue = mysqlEntry.getValue();
			                        Object mongoValue = doc.get(fieldName);
			                        if (!shouldCompare(fieldName)) {
			                            continue; 
			                        }
			                        if (mysqlValue instanceof Integer && (Integer) mysqlValue == 0 && mongoValue == null) {
			                            
			                            continue; 
			                        }
			                        if (mysqlValue instanceof Number && ((Number) mysqlValue).doubleValue() == 0.0 && mongoValue == null) {
			                            continue; 
			                        }
			                        if (mysqlValue != null && !mysqlValue.equals(mongoValue)) {
			                            System.out.println("Data mismatch for primary key: " + primaryKeyMysql + ", field: " + fieldName);
			                            System.out.println("MySQL value: " + mysqlValue + ", MongoDB value: " + mongoValue);
			                        }
			                    }
			                } else {
			                    System.out.println("Missing record in MongoDB for primary key: " + primaryKeyMysql);
			                }
			            }

			            connection.close();
			            mongoDBConnection.close();
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			    }
			    private static boolean shouldCompare(String mysqlFieldName) {
			    	
			    	Set<String> columnsToCompare = new HashSet<>(Arrays.asList("months", "type","pbt","pat","reserves","cashFlowId","companyCode","yearEnding","depreciation","finLease","leaseEqRes","gainForex","plInForex","plSaleAsst","plSaleInvs","pAdjSUndrt","interestInc","intPaidNet","interestNet","dividendRecOp","dvdnd_net","investments","miscIncome","amortizationOp","assetWOff","miscExp","paymentToVrs","provWOffNt","provGraty","provDimInv","provBdnpa","tradeReceivables","tradeBillPur","inventoryOperation","tradePayables","taxProvision","directTaxes","advanceTaxPaid","loanAdvance","transferReserve","otherOperatingAct","priorYearAdjust","provWriteBack","priorYearTax","balanceWriteBack","otherAssets","otherLiabilities","changeInDeposit","changeInBorrowing","discountExpenseLn","increaseDecreaseAdvance","increaseDecreaseInvs","netStockHire","lastNetSale","excessDepWriteBack","premiumLeaseLand","extraOrdinaryItem","netCashFlowOp","purchaseFixedAsset","saleFixedAsset","capitalWorkInProgress","capitalSubsRec","purchaseInvestment","saleInvestmentInIA","acquisitionCompany","saleUnderTransExtraItem","interestReceived","dividendReceivedInIA","investmentIncome","intraCorpDep","investmentInSubs","loanToSubs","investmentInGroupCo","issueSharesAcquiredCompany","cancelInvestedCos","certifiedDepBank","movementLoans","otherInvestmentAct","movementWorkingCapital","amortizationExpIA","taxesPaid","expensesCapitalized","extraOrdinaryItemInIA","purchaseFixedAssetLongOrder","netIDCurrentAsset","netIDAdvance","netIDCurrentLiabilities","netCashInIA","priorIssuedEquityCapital","priorIssuedPreferredCapital","priorIssuedSharePremium","redemptionCapital","proceedsIssueDebenture","priorBankBorrowing","priorLongTermBorrowing","priorShortTermBorrowing","priorDeposit","repaymentBorrowing","shareApplication","loanCorporateBody","dividendPaid","interestPaid","financeCharges","cashCreditAdvance","cashCapitalInvestment","otherFinancialActivity","foreignExchangeGainLossFA","sharePremium","misExpenseWriteOff","saleInvestmentFA","currentLiabilities","loanDisbursement","inventoryFA","extraOrdinaryItemFA","deferredExpenseBorrowing","shareApplicationRefund","onRedemptionDebenture","offOtherLongTermBorrowing","offShortTermBorrowing","offFinancialLongLiabilities","shelterARes","repaidShortTermBorrowing","repaidLongTermBorrowing","netCashUsedFA","foreignExchangeGainLossNetFA","netIncreaseDecreaseCash","cashStartYear","cashEndYear","modifiedDate" ));
			    	return columnsToCompare.contains(mysqlFieldName);
			    	}
			    
			    private static String mapFieldName(String mysqlFieldName) {
			        switch (mysqlFieldName) {
			            case "cash_flow_id":
			                return "cashFlowId";
			            case "company_code	":
			                return "companyCode";
			            case "year_ending":
			                return "yearEnding";
			            case "depreciatn":
			                return "depreciation";
			            case "fin_lease":
			                return "finLease";
			            case "lease_eq_res":
			                return "leaseEqRes";
			            case "gain_forex	":
			                return "gainForex";
			            case "pl_in_forex":
			                return "plInForex";
			            case "pl_sale_asst":
			                return "plSaleAsst";
			            case "pl_sale_invs":
			                return "plSaleInvs";
			            case "p_adj_s_undrt":
			                return "pAdjSUndrt";
			            case "intrst_inc":
			                return "interestInc";
			            case "int_paid_net":
			                return "intPaidNet";
			            case "intrst_net":
			                return "interestNet";
			            case "dvdnd_rec_op	":
			                return "dividendRecOp";
			            case "dividendNet":
			                return "dvdnd_net";
			            case "invstmts":
			                return "investments";
			            case "misc_income":
			                return "miscIncome";
			            case "amrtsatn_op":
			                return "amortizationOp";
			            case "asst_w_off":
			                return "assetWOff";
			            case "misc_exp":
			                return "miscExp";
			            case "paymtto_vrs":
			                return "paymentToVrs";
			            case "prov_w_off_nt":
			                return "provWOffNt";
			            case "prov_graty":
			                return "provGraty";
			            case "prov_dim_inv":
			                return "provDimInv";
			            case "prov_bdnpa":
			                return "provBdnpa";
			            case "trd_recvbl":
			                return "tradeReceivables";
			            case "trd_bill_pur":
			                return "tradeBillPur";
			            case "invnt_oprt":
			                return "inventoryOperation";
			            case "trd_payble":
			                return "tradePayables";
			            case "tax_provisn":
			                return "taxProvision";
			            case "dirct_taxes":
			                return "directTaxes";
			            case "adv_tax_paid":
			                return "advanceTaxPaid";
			            case "loan_advanc":
			                return "loanAdvance";
			            case "trans_resrv":
			                return "transferReserve";
			            case "oth_oprt_act":
			                return "otherOperatingAct";
			            case "pr_yr_adjust":
			                return "priorYearAdjust";
			            case "prv_writ_bck":
			                return "provWriteBack";
			            case "prior_yr_tax":
			                return "priorYearTax";
			            case "bal_writ_bck":
			                return "balanceWriteBack";
			            case "oth_assets":
			                return "otherAssets";
			            case "oth_liablt":
			                return "otherLiabilities";
			            case "ch_in_depsit":
			                return "changeInDeposit";
			            case "ch_in_borr":
			                return "changeInBorrowing";
			            case "disc_exp_ln":
			                return "discountExpenseLn";
			            case "inc_dec_advn":
			                return "increaseDecreaseAdvance";
			            case "inc_dec_invs":
			                return "increaseDecreaseInvs";
			            case "net_stk_hire":
			                return "netStockHire";
			            case "l_ast_net_sal":
			                return "lastNetSale";
			            case "exces_dep_wb":
			                return "excessDepWriteBack";
			            case "prem_ls_land":
			                return "premiumLeaseLand";
			            case "extr_ord_itm":
			                return "extraOrdinaryItem";
			            case "net_c_flow_op":
			                return "netCashFlowOp";
			            case "pur_fix_asst":
			                return "purchaseFixedAsset";
			            case "sal_fix_asst":
			                return "saleFixedAsset";
			            case "capital_wip":
			                return "capitalWorkInProgress";
			            case "cap_subs_rec":
			                return "capitalSubsRec";
			            case "pur_invst":
			                return "purchaseInvestment";
			            case "sal_invst_i_a":
			                return "saleInvestmentInIA";
			            case "aqustn_comp":
			                return "acquisitionCompany";
			            case "sal_u_t_exItm":
			                return "saleUnderTransExtraItem";
			            case "int_recd":
			                return "interestReceived";
			            case "dvnd_recd_i_a":
			                return "dividendReceivedInIA";
			            case "invst_incm":
			                return "investmentIncome";
			            case "intr_crp_dep":
			                return "intraCorpDep";
			            case "inv_in_subs":
			                return "investmentInSubs";
			            case "loan_to_subs":
			                return "loanToSubs";
			            case "inv_in_grp_co":
			                return "investmentInGroupCo";
			            case "iss_sh_acq_co":
			                return "issueSharesAcquiredCompany";
			            case "canc_inv_cos":
			                return "cancelInvestedCos";
			            case "cert_dep_bnk":
			                return "certifiedDepBank";
			            case "movmt_loans":
			                return "movementLoans";
			            case "oth_inv_act":
			                return "otherInvestmentAct";
			            case "movmt_wcap":
			                return "movementWorkingCapital";
			            case "amrt_exp_i_a":
			                return "amortizationExpIA";
			            case "taxes_paid":
			                return "taxesPaid";
			            case "exp_captlsd":
			                return "expensesCapitalized";
			            case "ex_ord_itm_i_a":
			                return "extraOrdinaryItemInIA";
			            case "p_fix_ast_l_o":
			                return "purchaseFixedAssetLongOrder";
			            case "nt_i_d_cur_ast":
			                return "netIDCurrentAsset";
			            case "net_i_d_advn":
			                return "netIDAdvance";
			            case "nt_i_d_cur_lib":
			                return "netIDCurrentLiabilities";
			            case "nt_csh_in_i_a":
			                return "netCashInIA";
			            case "pr_iss_eq_cap":
			                return "priorIssuedEquityCapital";
			            case "pr_iss_pr_cap":
			                return "priorIssuedPreferredCapital";
			            case "pr_iss_sh_prem":
			                return "priorIssuedSharePremium";
			            case "redmtn_cap":
			                return "redemptionCapital";
			            case "proc_iss_deb":
			                return "proceedsIssueDebenture";
			            case "pr_bnk_borr":
			                return "priorBankBorrowing";
			            case "pr_l_trm_borr":
			                return "priorLongTermBorrowing";
			            case "pr_sh_trm_bor":
			                return "priorShortTermBorrowing";
			            case "pr_deposit":
			                return "priorDeposit";
			            case "repmt_borr":
			                return "repaymentBorrowing";
			            case "sh_applictn":
			                return "shareApplication";
			            case "ln_corp_body":
			                return "loanCorporateBody";
			            case "dvdnd_paid":
			                return "dividendPaid";
			            case "int_paid":
			                return "interestPaid";
			            case "fin_chrgs":
			                return "financeCharges";
			            case "csh_crdt_adv":
			                return "cashCreditAdvance";
			            case "csh_cap_invs":
			                return "cashCapitalInvestment";
			            case "oth_fin_act":
			                return "otherFinancialActivity";
			            case "f_e_gn_los_f_a":
			                return "foreignExchangeGainLossFA";
			            case "sh_premium":
			                return "sharePremium";
			            case "mis_exp_w_off":
			                return "misExpenseWriteOff";
			            case "sal_inv_f_a":
			                return "saleInvestmentFA";
			            case "curr_liab":
			                return "currentLiabilities";
			            case "ln_disburse":
			                return "loanDisbursement";
			            case "invntry_f_a":
			                return "inventoryFA";
			            case "ex_ord_itm_f_a":
			                return "extraOrdinaryItemFA";
			            case "dfrd_exp_bor":
			                return "deferredExpenseBorrowing";
			            case "sh_aplc_rfnd":
			                return "shareApplicationRefund";
			            case "on_redem_deb":
			                return "onRedemptionDebenture";
			            case "of_oth_l_t_bor":
			                return "offOtherLongTermBorrowing";
			            case "of_sh_trm_bor":
			                return "offShortTermBorrowing";
			            case "of_fin_l_liab":
			                return "offFinancialLongLiabilities";
			            case "shltr_a_res":
			                return "shelterARes";
			            case "rep_s_trm_bor":
			                return "repaidShortTermBorrowing";
			            case "rep_l_trm_bor":
			                return "repaidLongTermBorrowing";
			            case "nt_csh_usd_f_a":
			                return "netCashUsedFA";
			            case "f_e_gn_ls_nt_f_a":
			                return "foreignExchangeGainLossNetFA";
			            case "n_inc_dec_csh":
			                return "netIncreaseDecreaseCash";
			            case "csh_strt_year":
			                return "cashStartYear";
			            case "csh_end_year":
			                return "cashEndYear";
			            case "modified_date":
			                return "modifiedDate";
			            default:
			                return mysqlFieldName;
			        }
			    }
			}




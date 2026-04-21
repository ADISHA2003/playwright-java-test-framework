package VCI.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.testng.annotations.Test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import utilities.MongoDBHandler;
import utilities.MySQLDBHandler;

public class Financial_consolidatedcashflowcheck {

	@Test
    public void Consolidatedcashflowcomparison() {
    	 Connection connection = null;
    	
    	 try {
             connection = MySQLDBHandler.getConnection();
             String sqlQuery = "SELECT * FROM `consolidated_cash_flow` " ;
            PreparedStatement mysqlStmt = connection.prepareStatement(sqlQuery);
            ResultSet mysqlResultSet = mysqlStmt.executeQuery();

            // Step 3: Save MySQL results in a map
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
                            
                            // Check if the year, month, or day value is invalid
                            if (localDate.getYear() == 0 || localDate.getMonthValue() == 0 || localDate.getDayOfMonth() == 0) {
                                columnValue = null; // Replace invalid date with null 
                                }
                        } 
                    } catch (SQLException e) { 
                        if ("Zero date value prohibited".equals(e.getMessage())) { 
                            columnValue = null; // Set column value to null 
                            } else { 
                            e.printStackTrace(); 
                        } 
                    } catch (DateTimeException ex) {
                        // Catch any DateTimeException and set column value to null
                        columnValue = null;
                        
                    }
                    // Add the column value to rowMap
                    String mappedFieldName = mapFieldName(columnName);
                    rowMap.put(mappedFieldName, columnValue);
                } 
                // Add rowMap to your data structure
            
                resultMap.put(primaryKey, rowMap);
            }


            // Step 4: Establish MongoDB connection
            MongoDBHandler mongoDBConnection = new MongoDBHandler();

            // Get the database instance
            MongoDatabase database = mongoDBConnection.getDatabase();

            MongoCollection<Document> mongoCollection = database.getCollection("ConsolidatedCashFlowMongoDb");
            
            int mysqlRecordCount = resultMap.size();
            System.out.println("MySQL Record Count: " + mysqlRecordCount);

            // Count MongoDB records
            long mongoRecordCount = mongoCollection.countDocuments();
            System.out.println("MongoDB Record Count: " + mongoRecordCount);

            // Compare record counts
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

                Document doc = mongoCollection.find(new Document("id", primaryKeyMysql)).first();

                if (doc != null) {
                    for (Map.Entry<String, Object> mysqlEntry : mysqlRow.entrySet()) {
                        String fieldName = mysqlEntry.getKey();
                        Object mysqlValue = mysqlEntry.getValue();
                        Object mongoValue = doc.get(fieldName);
                        if (!shouldCompare(fieldName)) {
                            continue; // Skip this field
                        }
                        if (mysqlValue instanceof Integer && (Integer) mysqlValue == 0 && mongoValue == null) {
                            
                            continue; // Skip to the next iteration of the loop
                        }
                        if (mysqlValue instanceof Number && ((Number) mysqlValue).doubleValue() == 0.0 && mongoValue == null) {
                            continue; // Skip to the next iteration of the loop
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
    	// Specify the MySQL column names that should be compared
    	Set<String> columnsToCompare = new HashSet<>(Arrays.asList("id","months", "type","depreciation","investments","reserves","companyCode","yearEnding","profitsBeforeTax","profitsAfterTax","finLeaseAndRentalCharges","leaseEqualisation","plInForex","gainOnForexExchTran","plOnSaleOfAssets","plOnSaleOfInvestments","profitAdjOnSaleOfUndrtkng","interestIncome","interestPaidNet","dividendReceivedOprtActivity","dividendNet","miscIncome","amortisationOfExpensesOprtActivity","assetsWrittenOff","miscExpenses","paymentTowardsVrs","provAndWoNet","provisionForGratuity","provisionForDimunInValueOfInvest","provisionsForBadDebtsNpa","tradeAndOthReceivables","tradeBillSPurchased","inventoriesOprtActivity","tradePayables","taxProvision","directTaxesPaid","advanceTaxePaid","loanAndAdvances","transferFromReserve","othersFromOprtActivity","priorYearAdjusments","provisionsWrittenBack","priorYearsTaxation","balanceWrittenBack","otherAssets","otherLiabilities","changeInDeposits","changeInBorrowing","discountExpOnLoansWrtOff","increaseDecreaseInAdvances","increaseDecreaseInInvestments","netStockOnHire","leasedAssetsNetOfSale","excessDepreciationWb","premiumOnLeaseOfLand","extraOrdinaryItems","operationgMinorityInterest","operationgShareOfProfitOfAsso","netCashFlowOprtngActivity","purchaseOfFixedAssets","saleOfFixedAssets","capitalWip","capitalSubsidyRecd","investmentInGoodWill","purchaseOfInvestments","saleOfInvestmentsInvesActivity","aquisitionOfCompanies","saleOfUndrtkngExtraOrdItem","interestReceived","devidendReceivedInvesActivity","investmentIncome","interCorporateDeposits","investmentInSubsidiaries","loanToSubsidiaries","investmentInGroupCos","issueOfShOnAcquOfCos","cansOfInvestmentInCosAcq","certificateOfDepositInBank","movementInLoans","investingMinorityInterest","investingShareOfProfitOfAsso","othersFromInvestActivity","movementInWorkingCapital","amortisationOfExpensesInvesActivity","taxesPaid","expensesCapitalised","extraordinaryItemsInvstActivity","purchaseOfFixedAssetsLeasedOut","netIncDecInCurrentAsset","netIncDecInAdvances","netIncDecInCurrentLiab","netCashUsedInInvestmentActivity","proceedsFromIssueOfEqCapital","proceedsFromIssueOfPrefCapital","proceedsFromIssueOfSnCapInclShPrem","redemptionOfCapital","proceedsFromIssueOfDeb","proceedsFromBankBorrowings","proceedsFromThLTermBorr","proceedsFromShTermBorr","proceedsFromDeposits","repaymentOfBorrowings","shareApplication","loanFromACorporateBody","devidendPaid","interestPaid","financialCharges","cashCreditAdvances","cashCapInvestmentSubsidy","otherFromFinActivity","foreignExchangeGainsLossesFinActivity","sharePremium","miscExpencesWrittenOff","saleOfInvestmentsFinActivity","currentLiabilities","loanDisbursed","inventoriesFinActivitiy","extraordinaryItemsFinActivity","deferredExpAgainstBorrowing","shareApplicationRefund","onRedemptionOfDebenture","offOtherLongTermBorrowing","offShortTermBorrowing","shelterAssistanceReserve","repaymentOfShortTermBorrow","financingShareOfProfitOfAsso","proceedsFromSharesIssuedBySubsidiaries","proceedsFromIssueOfPerpetualBonds","proceedsFromIssueOfSubordinatedDebts","repaymentOfLongTermBorrow","netCashUsedInFinanceActivity","foreignExchangeGainsLossesNetFinActivity","cashAndEquvalntBeginOfYear","cashAndEquvalntEndOfYear","modifiedDate" ));
    	return columnsToCompare.contains(mysqlFieldName);
    	}
    
    private static String mapFieldName(String mysqlFieldName) {
        switch (mysqlFieldName) {
          
            case "company_code	":
                return "companyCode";
            case "year_ending":
                return "yearEnding";
            case "profits_before_tax":
                return "profitsBeforeTax";
            case "profits_after_tax":
                return "profitsAfterTax";
            case "fin_lease_and_rental_charges":
                return "finLeaseAndRentalCharges";
            case "lease_equalisation":
                return "leaseEqualisation";
            case "pl_in_forex":
                return "plInForex";
            case "gain_on_forex_exch_tran":
                return "gainOnForexExchTran";
            case "pl_on_sale_of_assets":
                return "plOnSaleOfAssets";
            case "pl_on_sale_of_investments":
                return "plOnSaleOfInvestments";
            case "profit_adj_on_sale_of_undrtkng":
                return "profitAdjOnSaleOfUndrtkng";
            case "interest_income":
                return "interestIncome";
            case "interest_paid_net":
                return "interestPaidNet";
            case "devidend_received_oprt_activity":
                return "dividendReceivedOprtActivity";
            case "devidend_net":
                return "dividendNet";
            case "misc_income":
                return "miscIncome";
            case "amortisation_of_expenses_oprt_activity":
                return "amortisationOfExpensesOprtActivity";
            case "assets_written_off":
                return "assetsWrittenOff";
            case "misc_expenses":
                return "miscExpenses";
            case "payment_towards_vrs":
                return "paymentTowardsVrs";
            case "prov_and_wo_net":
                return "provAndWoNet";
            case "provision_for_gratuity":
                return "provisionForGratuity";
            case "prov_for_dimun_in_value_of_invest":
                return "provisionForDimunInValueOfInvest";
            case "provisions_for_bad_debts_npa":
                return "provisionsForBadDebtsNpa";
            case "trade_and_oth_receivables":
                return "tradeAndOthReceivables";
            case "trade_bill_spurchased":
                return "tradeBillSPurchased";
            case "inventories_orpt_activity":
                return "inventoriesOprtActivity";
            case "trade_payables":
                return "tradePayables";
            case "tax_provision":
                return "taxProvision";
            case "direct_taxes_paid":
                return "directTaxesPaid";
            case "advance_taxe_paid":
                return "advanceTaxePaid";
            case "loan_and_advances":
                return "loanAndAdvances";
            case "transfer_from_reserve":
                return "transferFromReserve";
            case "others_from_oprt_activity":
                return "othersFromOprtActivity";
            case "prior_year_adjusments":
                return "priorYearAdjusments";
            case "provisions_written_back":
                return "provisionsWrittenBack";
            case "prior_years_taxation":
                return "priorYearsTaxation";
            case "balance_written_back":
                return "balanceWrittenBack";
            case "other_assets":
                return "otherAssets";
            case "other_liabilities":
                return "otherLiabilities";
            case "change_in_deposits":
                return "changeInDeposits";
            case "change_in_borrowing":
                return "changeInBorrowing";
            case "discount_exp_on_loans_wrt_off":
                return "discountExpOnLoansWrtOff";
            case "increase_decrease_in_advances":
                return "increaseDecreaseInAdvances";
            case "increase_decrease_in_investments":
                return "increaseDecreaseInInvestments";
            case "net_stock_on_hire":
                return "netStockOnHire";
            case "leased_assets_net_of_sale":
                return "leasedAssetsNetOfSale";
            case "excess_depreciation_wb":
                return "excessDepreciationWb";
            case "premium_on_lease_of_land":
                return "premiumOnLeaseOfLand";
            case "extra_ordinary_items":
                return "extraOrdinaryItems";
            case "operationg_minority_interest":
                return "operationgMinorityInterest";
            case "operationg_share_of_profit_of_asso":
                return "operationgShareOfProfitOfAsso";
            case "net_cash_flow_oprtng_activity":
                return "netCashFlowOprtngActivity";
            case "purchase_of_fixed_assets":
                return "purchaseOfFixedAssets";
            case "sale_of_fixed_assets":
                return "saleOfFixedAssets";
            case "capital_wip":
                return "capitalWip";
            case "capital_subsidy_recd":
                return "capitalSubsidyRecd";
            case "investment_in_good_will":
                return "investmentInGoodWill";
            case "purchase_of_investments":
                return "purchaseOfInvestments";
            case "sale_of_investments_inves_activity":
                return "saleOfInvestmentsInvesActivity";
            case "aquisition_of_companies":
                return "aquisitionOfCompanies";
            case "sale_of_undrtkng_extra_ord_item":
                return "saleOfUndrtkngExtraOrdItem";
            case "interest_received":
                return "interestReceived";
            case "devidend_received_inves_activity":
                return "devidendReceivedInvesActivity";
            case "investment_income":
                return "investmentIncome";
            case "inter_corporate_deposits":
                return "interCorporateDeposits";
            case "investment_in_subsidiaries":
                return "investmentInSubsidiaries";
            case "loan_to_subsidiaries":
                return "loanToSubsidiaries";
            case "investment_in_group_cos":
                return "investmentInGroupCos";
            case "issue_of_sh_on_acqu_of_cos":
                return "issueOfShOnAcquOfCos";
            case "cans_of_investment_in_cos_acq":
                return "cansOfInvestmentInCosAcq";
            case "certificate_of_deposit_in_bank":
                return "certificateOfDepositInBank";
            case "movement_in_loans":
                return "movementInLoans";
            case "investing_minority_interest":
                return "investingMinorityInterest";
            case "investing_share_of_profit_of_asso":
                return "investingShareOfProfitOfAsso";
            case "others_from_invest_activity":
                return "othersFromInvestActivity";
            case "movement_in_working_capital":
                return "movementInWorkingCapital";
            case "amortisation_of_expenses_inves_activity":
                return "amortisationOfExpensesInvesActivity";
            case "taxes_paid":
                return "taxesPaid";
            case "expenses_capitalised":
                return "expensesCapitalised";
            case "extraordinary_items_invst_activity":
                return "extraordinaryItemsInvstActivity";
            case "purchase_of_fixed_assets_leased_out":
                return "purchaseOfFixedAssetsLeasedOut";
            case "net_inc_dec_in_current_asset":
                return "netIncDecInCurrentAsset";
            case "net_inc_dec_in_advances":
                return "netIncDecInAdvances";
            case "net_inc_dec_in_currentliab":
                return "netIncDecInCurrentLiab";
            case "net_cash_used_in_investment_activity":
                return "netCashUsedInInvestmentActivity";
            case "proceeds_from_issue_of_eq_capital":
                return "proceedsFromIssueOfEqCapital";
            case "proceeds_from_issue_of_pref_capital":
                return "proceedsFromIssueOfPrefCapital";
            case "proceeds_from_issue_of_sn_cap_incl_sh_prem":
                return "proceedsFromIssueOfSnCapInclShPrem";
            case "redemption_of_capital":
                return "redemptionOfCapital";
            case "proceeds_from_issue_of_deb":
                return "proceedsFromIssueOfDeb";
            case "proceeds_from_bank_borrowings":
                return "proceedsFromBankBorrowings";
            case "proceeds_from_th_l_term_borr":
                return "proceedsFromThLTermBorr";
            case "proceeds_from_sh_term_borr":
                return "proceedsFromShTermBorr";
            case "proceeds_from_deposits":
                return "proceedsFromDeposits";
            case "repayment_of_borrowings":
                return "repaymentOfBorrowings";
            case "share_application":
                return "shareApplication";
            case "loan_from_a_corporate_body":
                return "loanFromACorporateBody";
            case "devidend_paid":
                return "devidendPaid";
            case "interest_paid":
                return "interestPaid";
            case "financial_charges":
                return "financialCharges";
            case "cash_credit_advances":
                return "cashCreditAdvances";
            case "cash_cap_investment_subsidy":
                return "cashCapInvestmentSubsidy";
            case "other_from_fin_activity":
                return "otherFromFinActivity";
            case "foreign_exchange_gains_losses_fin_activity":
                return "foreignExchangeGainsLossesFinActivity";
            case "share_premium":
                return "sharePremium";
            case "misc_expences_written_off":
                return "miscExpencesWrittenOff";
            case "sale_of_investments_fin_activity":
                return "saleOfInvestmentsFinActivity";
            
            case "current_liabilities":
                return "currentLiabilities";
            case "loan_disbursed":
                return "loanDisbursed";
            case "inventories_fin_activitiy":
                return "inventoriesFinActivitiy";
            case "extraordinary_items_fin_activity":
                return "extraordinaryItemsFinActivity";
            case "deffered_exp_against_borrowing":
                return "deferredExpAgainstBorrowing";
            case "share_application_refund":
                return "shareApplicationRefund";
            case "on_redem_of_deben":
                return "onRedemptionOfDebenture";
            case "of_oth_l_term_borr":
                return "offOtherLongTermBorrowing";
            case "of_sh_term_borr":
                return "offShortTermBorrowing";
            case "shelter_assistance_reserve":
                return "shelterAssistanceReserve";
            case "repayment_of_short_term_borrow":
                return "repaymentOfShortTermBorrow";
            case "financing_share_of_profit_of_asso":
                return "financingShareOfProfitOfAsso";
            case "proceeds_from_shares_issued_by_subsidiaries":
                return "proceedsFromSharesIssuedBySubsidiaries";
            case "proceeds_from_issue_of_perpetual_bonds":
                return "proceedsFromIssueOfPerpetualBonds";
            case "proceeds_from_issue_of_subordinated_debts":
                return "proceedsFromIssueOfSubordinatedDebts";
            case "repayment_of_long_term_borrow":
                return "repaymentOfLongTermBorrow";
            case "net_cash_used_in_finance_activity":
                return "netCashUsedInFinanceActivity";
            case "foreign_exchange_gains_losses_net_fin_activity":
                return "foreignExchangeGainsLossesNetFinActivity";
            case "cash_and_equvalnt_begin_of_year":
                return "cashAndEquvalntBeginOfYear";
            case "cash_and_equvalnt_end_of_year":
                return "cashAndEquvalntEndOfYear";
            case "modified_date":
                return "modifiedDate";
            default:
                return mysqlFieldName;
        }
    }
}





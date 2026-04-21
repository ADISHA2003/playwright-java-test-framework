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

public class Financial_privateinsurance {
	
	@Test
    public void PrivateFinancialInsurance() {
    	 Connection connection = null;
    	
    	 try {
             connection = MySQLDBHandler.getConnection();
             String sqlQuery = "SELECT * FROM `private_financials_insurance`" ;
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


            MongoDBHandler mongoDBConnection = new MongoDBHandler();

            MongoDatabase database = mongoDBConnection.getDatabase();

            MongoCollection<Document> mongoCollection = database.getCollection("PrivateFinancialsInsuranceMongoDb");
            
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

                        // Check if both values are null
                        if (mysqlValue == null && mongoValue == null) {
                            continue; // Both values are null, no mismatch
                        }

                        // Convert values to strings for easier comparison
                        String mysqlStr = mysqlValue != null ? mysqlValue.toString().trim() : null;
                        String mongoStr = mongoValue != null ? mongoValue.toString().trim() : null;

                        if (mysqlStr == null && mongoStr == null) {
                            continue; // Both values are null, no mismatch
                        }

                        if (mysqlStr != null && mysqlStr.equals(mongoStr)) {
                            continue; // Values are equal, no mismatch
                        }
                        // Compare values
                       
                            // Values are different, log the mismatch
                            System.out.println("Data mismatch for primary key: " + primaryKeyMysql + ", field: " + fieldName);
                            System.out.println("MySQL value: " + mysqlStr + ", MongoDB value: " + mongoStr);
                        
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
    	Set<String> columnsToCompare = new HashSet<>(Arrays.asList("id", "premium","commission","depreciation", "appropriations", "borrowings", "shareholders","policyholders", "investments", "loan","provisions","dividend", "companyId", "yearEnd", "conversionFactor","financialType","financialFormat", "updatedOn","fromDate","toDate","reportingStandard","reinsuranceCeded","reinsuranceAccepted","premiumsEarnedNet","profitOnSaleRedemptionOfInvestments","lossOnSaleRedemptionOfInvestments","transferGainOnRevaluationInFairValue","amortisationOfPremiumOnInvestments","othersTech1","incomeFromInvestments","otherIncome","totalIncome","claimsIncurred","operatingExpensesRelatedToInsuranceBusiness","netOperatingExpenses","premiumDeficiency","serviceTaxOnUlc","provisionForDoubtfulDebts","badDebtsWrittenOff","provisionForTax","provisionOtherThanTaxation","othersTech2","benefitsPaidNet","otherExpenses","totalExpenses","surplusDeficitBeforeTax","othersTech3","surplusDeficitAfterTax","surplusAvailableAtBeginning","totalSurplusForAvailable","appropriationsBalanceForAppropriations","transferToShareholdersAccount","fundsForDiscontinuedPolicies","fundsForFutureAppropriation","othersTech4","totalAppropriations","amountTransferedFromPolicyholderAccount","fireInsurance","marineInsurance","miscellaneousInsurance","operatingProfitTransferedToPolicyholderAccount","incomeFromInvestmentsNonTech","otherIncomeNonTech","totalRevenueTech","totalRevenueNonTech","expensesOtherThanInsurance","depreciationNonTech","netOperatingExpensesNonTech","badDebtsWrittenOffNonTech","provisionsOtherThanTaxNonTech","contributionFromShareholderAccount","otherExpensesNonTech","totalExpensesNonTech","pbtTech","pbtNonTech","provisionForTaxNonTech","othersNonTech2","patTech","patNonTech","plBalanceSheetTech","plBalanceSheetNonTech","earningsPerShare","shareCapital","shareApplPendingAllot","reserveAndSurplus","netWorth","creditFairValue","totalShareholdersFunds","deffTaxLiability","totalLoansDefTaxLiability","policyholdersFunds","creditFairValueChangeAcc","policyLiabilities","insuranceReserves","provisionForLinkedLiabilities","addFairValueChange","fundsForDiscontinuedPoliciesSof","totalProvisionForLiabilities","totalPolicyholdersFund","fundsForFutureAppropriationSof","totalLiabilities","totalSofFunds","totalInvestments","assetsToCoverLinkedLiabilities","fixedAssets","defTaxAssets","totalInvestmentsLoansAssets","cashBankBalances","advancesOtherAssets","totalCurrentAssets","currentLiabilities","deferredTaxLiability","totalCurrentLiabilitiesProvisions","netCurrentAssets","miscExpenditure","debitBalanceForPL","differenceInRevenue","totalAofFunds","cashFlowOperations","cashFlowInvesting","cashFlowFinancing","freeCashFlow","totalPreferred","sharesOutstanding","sharesOutstandingFullyDiluted","earningsPerShareBasic","earningsPerShareDiluted","netRetention","managementExpenses","commissionRatio","claimsRatio" ,"policyholdersLiabilities","growthRate",""));
    	return columnsToCompare.contains(mysqlFieldName);
    	}
    
    private static String mapFieldName(String mysqlFieldName) {
        switch (mysqlFieldName) {
            case "companyid":
                return "companyId";
            case "yearend":
                return "yearEnd";
            case "conversion_factor":
                return "conversionFactor";
            case "financial_type":
                return "financialType";
            case "financial_format":
                return "financialFormat";
            case "updated_on":
                return "updatedOn";
            case "from_date":
                return "fromDate";
            case "to_date":
                return "toDate";
            case "reporting_standard":
                return "reportingStandard";
            case "reinsurance_ceded":
                return "reinsuranceCeded";
            case "reinsurance_accepted":
                return "reinsuranceAccepted";	
            case "premiums_earned_net":
                return "premiumsEarnedNet";
            case "profit_on_sale_redemption_of_investments":
                return "profitOnSaleRedemptionOfInvestments";
            case "loss_on_sale_redemption_of_investments":
                return "lossOnSaleRedemptionOfInvestments";
            case "transfer_gain_on_revaluation_in_fair_value":
                return "transferGainOnRevaluationInFairValue";
            case "amortisation_of_premium_on_investments":
                return "amortisationOfPremiumOnInvestments";
            case "others_tech_1":
                return "othersTech1";
            case "income_from_investments":
                return "incomeFromInvestments";
            case "other_income":
                return "otherIncome";
            case "total_income":
                return "totalIncome";
            case "claims_incurred":
                return "claimsIncurred";
            case "operating_expenses_related_to_insurance_business":
                return "operatingExpensesRelatedToInsuranceBusiness";	

            case "net_operating_expenses":
                return "netOperatingExpenses";
            case "premium_deficiency":
                return "premiumDeficiency";
            case "service_tax_on_ulc":
                return "serviceTaxOnUlc";
            case "provision_for_doubtful_debts":
                return "provisionForDoubtfulDebts";
            case "bad_debts_written_off":
                return "badDebtsWrittenOff";
            case "provison_for_tax":
                return "provisionForTax";
            case "provision_other_than_taxation":
                return "provisionOtherThanTaxation";
            case "others_tech_2":
                return "othersTech2";
            case "benefits_paid_net":
                return "benefitsPaidNet";
            case "other_expences":
                return "otherExpenses";
            case "total_expenses":
                return "totalExpenses";
            case "surplus_deficit_before_tax":
                return "surplusDeficitBeforeTax";
            case "others_tech_3":
                return "othersTech3";
            case "surplus_deficit_after_tax":
                return "surplusDeficitAfterTax";
            case "surplus_available_at_begning":
                return "surplusAvailableAtBeginning";
            case "total_surplus_for_available":
                return "totalSurplusForAvailable";	
                
            case "appropriations_balance_for_appropriations":
                return "appropriationsBalanceForAppropriations";
            case "transfer_to_shareholders_account":
                return "transferToShareholdersAccount";
            case "funds_for_discontinued_policies":
                return "fundsForDiscontinuedPolicies";
            case "funds_for_future_appropriation":
                return "fundsForFutureAppropriation";
            case "others_tech_4":
                return "othersTech4";
            case "total_apropriations":
                return "totalAppropriations";
            case "amount_transfered_from_policyholder_account":
                return "amountTransferedFromPolicyholderAccount";	
                
            case "fire_insurance":
                return "fireInsurance";
            case "marine_insurance":
                return "marineInsurance";
            case "miscellaneous_insurance":
                return "miscellaneousInsurance";
            case "operating_profit_transfered_to_policyholder_account":
                return "operatingProfitTransferedToPolicyholderAccount";
            case "income_from_investments_non_tech":
                return "incomeFromInvestmentsNonTech";
            case "other_income_non_tech":
                return "otherIncomeNonTech";
            case "total_revenue_tech":
                return "totalRevenueTech";

            case "total_revenue_non_tech":
                return "totalRevenueNonTech";
            case "expenses_other_than_insurance":
                return "expensesOtherThanInsurance";
            case "depreciation_non_tech":
                return "depreciationNonTech";
            case "net_operating_expenses_non_tech":
                return "netOperatingExpensesNonTech";
            case "bad_debts_written_off_non_tech":
                return "badDebtsWrittenOffNonTech";
            case "provisions_other_than_tax_non_tech":
                return "provisionsOtherThanTaxNonTech";
            case "contribution_from_shareholder_account":
                return "contributionFromShareholderAccount";	
                
            case "other_expences_non_tech":
                return "otherExpensesNonTech";
            case "total_expenses_non_tech":
                return "totalExpensesNonTech";
            case "pbt_tech":
                return "pbtTech";
            case "pbt_non_tech":
                return "pbtNonTech";
            case "provison_for_tax_non_tech":
                return "provisionForTaxNonTech";
            case "others_non_tech_2":
                return "othersNonTech2";
            case "pat_tech":
                return "patTech";
                
            case "pat_non_tech":
                return "patNonTech";
            case "p_l_balance_sheet_tech":
                return "plBalanceSheetTech";
            case "p_l_balance_sheet_non_tech":
                return "plBalanceSheetNonTech";
            case "earnings_per_share":
                return "earningsPerShare";
            case "share_capital":
                return "shareCapital";
            case "share_appl_pending_allot":
                return "shareApplPendingAllot";
            case "reserve_and_surplus":
                return "reserveAndSurplus";	
                
            case "networth":
                return "netWorth";
            case "credit_fair_value":
                return "creditFairValue";
            case "total_shareholders_funds":
                return "totalShareholdersFunds";
            case "deff_tax_liab":
                return "deffTaxLiability";
            case "total_loans_def_tax_liab":
                return "totalLoansDefTaxLiability";
            case "policyholders_funds":
                return "policyholdersFunds";
            case "credit_fair_value_change_acc":
                return "creditFairValueChangeAcc";
                
            case "policy_liabilties":
                return "policyLiabilities";
            case "insurance_reserves":
                return "insuranceReserves";
            case "provision_for_linked_liabilites":
                return "provisionForLinkedLiabilities";
            case "add_fair_value_change":
                return "addFairValueChange";
            case "funds_for_discontinued_policies_sof":
                return "fundsForDiscontinuedPoliciesSof";
            case "total_provision_for_liabilities":
                return "totalProvisionForLiabilities";
            case "total_policyholders_fund":
                return "totalPolicyholdersFund";	
                
            case "funds_for_future_appropriation_sof":
                return "fundsForFutureAppropriationSof";
            case "total_liabilities":
                return "totalLiabilities";
            case "totoal_soffunds":
                return "totalSofFunds";
            case "total_investments":
                return "totalInvestments";
            case "assets_to_cover_linked_liabilities":
                return "assetsToCoverLinkedLiabilities";
            case "fixed_assets":
                return "fixedAssets";
            case "def_tax_assets":
                return "defTaxAssets";
                
            case "total_investments_loans_assets":
                return "totalInvestmentsLoansAssets";
            case "cash_bank_balances":
                return "cashBankBalances";
            case "advances_other_assets":
                return "advancesOtherAssets";
            case "total_current_assests":
                return "totalCurrentAssets";
            case "current_liablities":
                return "currentLiabilities";
            case "deffered_tax_liability":
                return "deferredTaxLiability";
            case "total_current_liabilities_provisions":
                return "totalCurrentLiabilitiesProvisions";	
                
            case "net_current_assets":
                return "netCurrentAssets";
            case "misc_expenditure":
                return "miscExpenditure";
            case "debit_balance_for_pl":
                return "debitBalanceForPL";
            case "dif_in_revenue":
                return "differenceInRevenue";
            case "total_aoffunds":
                return "totalAofFunds";
            case "cash_flow_operations":
                return "cashFlowOperations";
            case "cash_flow_investing":
                return "cashFlowInvesting";
            case "cash_flow_financing":
                return "cashFlowFinancing";
            case "free_cash_flow":
                return "freeCashFlow";
            case "total_preferred":
                return "totalPreferred";
            case "shares_os":
                return "sharesOutstanding";
            case "shares_os_fully_diluted":
                return "sharesOutstandingFullyDiluted";
            case "eps_basic":
                return "earningsPerShareBasic";
            case "eps_diluted":
                return "earningsPerShareDiluted";	
                
            case "net_retention":
                return "netRetention";
            case "management_expenses":
                return "managementExpenses";
            case "commission_ratio":
                return "commissionRatio";
            case "claims_ratio":
                return "claimsRatio";
            case "policyholders_liabilities":
                return "policyholdersLiabilities";
            case "growth_rate":
                return "growthRate";
            case "change_in_networth":
                return "changeInNetworth";
            case "pat_margin":
                return "patMargin";
            case "surplus_to_policy":
                return "surplusToPolicy";
            case "loss_ratio":
                return "lossRatio";
            case "total_fund_under_p_a":
                return "totalFundUnderPA";
            case "total_fund_under_s_a":
                return "totalFundUnderSA";
            case "return_on_capital_employed":
                return "returnOnCapitalEmployed";
            case "return_on_networth":
                return "returnOnNetworth";
            case "operating_profit_margin":
                return "operatingProfitMargin";
            case "pbt_margin":
                return "pbtMargin";
            case "reinsurance_accepted_to_gross_premium":
                return "reinsuranceAcceptedToGrossPremium";
            case "reinsurance_ceded_to_gross_premium":
                return "reinsuranceCededToGrossPremium";
            case "income_from_investments_to_gross_premium":
                return "incomeFromInvestmentsToGrossPremium";
            case "net_earning_ratio":
                return "netEarningRatio";
            case "net_incurred_claims":
                return "netIncurredClaims";	
                
            case "combined_ratio":
                return "combinedRatio";
            case "investment_yield":
                return "investmentYield";
            case "operating_expenses_ratio":
                return "operatingExpensesRatio";
            case "priority_check":
                return "priorityCheck";
            case "pfh_discription":
                return "pfhDescription";
            case "updated_at":
                return "updatedAt";
            case "created_at":
                return "createdAt";
            default:
                return mysqlFieldName;
        }
    }
}





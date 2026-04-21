package Pojos.entityMongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;




@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection="FinancialNonBankingMongoDb")
public class FinancialNonBankingMongoDb {



        @Id
        private String id;
        private Integer companyId;
        private String companyName;
        private String companyCode;
        private String entityType;
        private String yearEnding;
        private int months;
        private String type; // 'standalone' or 'consolidated'
        private String faceValue;
        private String revenueFromOperationsGross;
        private String lessExciseServiceTaxOtherLevies;
        private String revenueFromOperationsNet;
        private String otherOperatingRevenues;
        private String totalOperatingRevenues;
        private String otherIncome;
        private String totalRevenue;
        private String costOfMaterialsConsumed;
        private String purchaseOfStockInTrade;
        private String purchaseOfCrudeOilAndOthers;
        private String costOfPowerPurchased;
        private String costOfFuel;
        private String aircraftFuelExpenses;
        private String aircraftLeaseRentals;
        private String operatingAndDirectExpenses;
        private String changesInInventoriesOfFgwipAndStockInTrade;
        private String employeeBenefitExpenses;
        private String financeCosts;
        private String provisionsAndContingencies;
        private String depreciationsAndAmortisationExpenses;
        private String miscExpensesWoff;
        private String otherExpenses;
        private String lessInterUnitSegmentDivisionTransfer;
        private String lessTransferToFromInvestmentFixedAssetsOthers;
        private String lessAmountsTransferToCapitalAccounts;
        private String lessShareOfLossFromPartnershipFirm;
        private String totalExpenses;
        private String profitLossBeforeExceptionalExtraordinaryItemsAndTax;
        private String exceptionalItems;
        private String profitLossBeforeTax;
        private String currentTax;
        private String lessMatCredit;
        private String deferredTax;
        private String otherDirectTaxes;
        private String taxForEarlierYears;
        private String totalTaxExpensesContinuedOperations;


}

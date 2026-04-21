package Pojos.entityMongo;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "FinancialBankingMongoDb")
public class FinancialBankingMongoDb {


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
        private String interestDiscountOnAdvancesBills;
        private String incomeFromInvestments;
        private String interestOnBalanceWithRbiAndOtherInterBankFunds;
        private String others;
        private String totalInterestEarned;
        private String otherIncome;
        private String totalIncome;
        private String interestExpended;
        private String paymentsToAndProvisionsForEmployees;
        private String depreciation;
        private String depreciationOnLeasedAssets;
        private String operatingExpensesExcludedEmployeeCostAndDepreciation;
        private String totalOperatingExpenses;
        private String provisionTowardsIncomeTax;
        private String provisionTowardsDeferredTax;
        private String provisionTowardsOtherTaxes;
        private String otherProvisionAndContingencies;
        private String totalProvisionAndContingencies;
        private String totalExpenditure;
        private String netProfitLossForTheYear;
        private String priorPeriodItems;
        private String extraOrdinaryItems;
        private String netProfitLossForTheYearAfterEi;
        private String profitLossBroughtForward;
        private String transferredOnAmalgamation;
        private String totalProfitLoss;
        private String transferToFromStatutoryReserve;
        private String transferToFromReserveFund;
        private String transferToFromSpecialReserve;
        private String transferToFromCapitalReserve;
        private String transferToFromGeneralReserve;
        private String transferToFromInvestmentReserve;
        private String transferToFromRevenueAndOtherReserves;
        private String devidendForTheYear;
    }


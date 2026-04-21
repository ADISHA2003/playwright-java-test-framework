package Pojos.entityMongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "SnapshotResultsMongoDb")
public class SnapshotResultsMongoDb {

    @Id
    private String id;
    private Integer companyId;
    private String companyName;
    private Integer halfYearlyResultsId;
    private Long companyCode;

    private String entityType;

    private short yearEnding1;
    private byte months;
    private byte half;
    private Double operatingIncome;
    private Double otherOperatingIncome;
    private Double totalIncomeFromOperations;
    private Double intOrDiscOnAdvOrBills;
    private Double incomeOnInvestment;
    private Double intOnBalancesWithRBI;
    private Double others;
    private Double otherRecurringIncome;
    private Double stockAdjustment;
    private Double rawMaterialConsumed;
    private Double purchaseOfTradedGoods;
    private Double powerAndFuel;
    private Double employeeExpenses;
    private Double excise;
    private Double adminAndSellingExpenses;
    private Double researchAndDevelopmentExpenses;
    private Double expensesCapitalised;
    private Double otherExpenses;
    private Double plBeforeOtherIncIntExcpItemTax;
    private Double plBeforeIntExcpItemTax;
    private Double interestCharges;
    private Double plBeforeExcpItemTax;
    private Double exceptionalItems;
    private Double depreciation;
    private Double operatingProfitBeforeProvisionsAndContingencies;
    private Double bankProvisionsMade;
    private Double plBeforeTax;
    private Double taxCharges;
    private Double plAfterTaxFromOrdinaryActivities;
    private Double extraOrdinaryItem;
    private Double reportedPat;
    private Double priorYearAdj;
    private Double reservesWrittenBack;
    private Double equityCapital;
    private Double reservesAndSurplus;
    private Double eqDividendRate;
    private Double aggregateOfNonPromotoNoOfShares;
    private Double aggregateOfNonPromotoHoldingPercent;
    private Double governmentShare;
    private Double capitalAdequacyRatio;
    private Double capitalAdequacyBaseII;
    private Double grossNPA;
    private Double netNPA;
    private Double percentageOfGrossNPA;
    private Double percentageOfNetNPA;
    private Double returnOnAssetsPer;
    private Double beforeBasicEPS;
    private Double beforeDilutedEPS;
    private Double afterBasicEPS;
    private Double afterDilutedEPS;
    private Double enNumberOfShares;
    private Double enPerShareAsPerOfTotShHolOfProAndGroup;
    private Double enPerShareAsPerOfTotShCapOfCompany;
    private Double nonEnNumberOfShares;
    private Double nonEnPerShareAsPerOfTotShHolOfProAndGroup;
    private Double nonEnPerShareAsPerOfTotShCapOfCompany;
    private String notes;
    private String segmentNotes;
    private String modifiedDate;
    private Double netSales;
    private Double totalIncome;
    private Double totalExpenses;
    private Double operatingProfit;
    private Double ebitda;
    private Double otherAdjustments;

    private Double intOnBalanceWithRBI;

    private Double plBeforeOtherIncExcpItemTax;

    private Double extraOrdinaryItems;

    private Double minorityInterest;
    private Double shareOfPlOfAssociates;
    private Double netPlAfterMiAssociates;
    private Double costOfInvestmentInSubsidiary;

    private Double eqDevidendRate;
    ;
    private Double capitalAdequacyBaseIi;

    private Double perOfGrossNPA;
    private Double perOfNetNPA;

    private Double beforeBasicEps;
    private Double asBeforeDilutedEps;
    private Double afterBasicEps;
    private Double afterDilutedEps;

    private byte nine;

    private Integer quarterlyResultsId;

    private byte quarter;

    private Double asBeforeDilutedEPS;

}

package Pojos.entityMongo;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "RatiosMongoDb")
public class RatiosMongoDb {
    @Id
    private String id;
    private Integer companyId;
    private String companyName;
    private String companyCode;
    private String yearEnding;
    private String entityType;
    private int months;
    private String type; // 'standalone' or 'consolidated'
    private String faceValue;
    private String adjustedEps;
    private String adjustedCashEps;
    private String returnOnAssetsExcl;
    private Double returnOnAssetsIncl;
    private String dividendPerShare;
    private String reportedEps;
    private String reportedCashEps;
    private String opProfitPerShare;
    private String netOperatingIncomePerShare;
    private String freeReservesPerShare;
    private Double operatingMargin;
    private String reportedReturnOnNetWorth;
    private String adjustedReturnOnNetWorth;
    private String adjustedCashMargin;
    private String returnOnLongTermFund;
    private String currentRatio;
    private Double quickRatio;
    private String averageRawMatHolding;
    private String averageFinishedGoodsHold;
    private String noOfDaysInWorkingCap;
    private String longTermDebtEquity;
    private String ownersFund;
    private String totalDebtToOwnersFund;
    private String currentRatioInclShortTermLoans;
    private String assetTurnOverRatio;
    private String longTermAssets;
    private String financialChargesCoverageRatio;
    private String financialChargesCoverageRatioPostTax;
    private String dividendPayoutRatioNp;
    private String dividendPayoutRatioCp;
    private String earningRetentionRatio;
    private String cashEarningRetentionRatio;
    private String materialCostComposition;
    private String sellDistributCostComp;
    private String investmentsTurnRatio;
    private String adjustedCashFlowTimes;
    private String importedCompOfRawMaterialConsumed;
    private String expAsTotalSales;
    private String interestSpread;
    private String operatingExpByOpIncome;
    private String npByOperatinIncome;
    private String npByCapitalEmployed;
    private Date modifiedDate;
}
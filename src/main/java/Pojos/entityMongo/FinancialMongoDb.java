package Pojos.entityMongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "FinancialMongoDb")
public class FinancialMongoDb {

    @Id
    private String id;
    private Integer companyId;
    private String companyName;
    private Integer vccCompanyId;
    private Long companyCode;
    private String entityType;
    private Integer year1;
    private String yearEnding;
    private byte months;
    private Double operatingIncome;
    private Double totalIncome;
    private Double costOfSales;
    private Double adjustedPbdit;
    private Double ebit;
    private Double adjustedPbt;
    private Double adjustedPat;
    private Double equityCapital;
    private Double resAndSurplus;
    private Double netWorth;
    private Double totalDebt;
    private Double grossBlock;
    private Double depreciation;
    private Double inventories;
    private Double currentAssets;
    private Double investments;
    private Double cashAndBankBalance;
    private Double netCurrentAssets;
    private Double totalCurrentLiabilities;
    private Double totalCurrentAssets;
    private Double securedLoans;
    private Double unsecuredLoans;
    private Double netBlock;
    private Double totalAssets;
    private Double ebitaMargin;
    private Double closingPrice;
    private Double marketCapitalization;
    private Double equityDividend;
    private Double enterpriseValue;
    private String cfYearEnding;
    private Double netCFlowOp;
    private Double ntCshInIA;
    private Double ntCshUsdFA;
    private String rYearEnding;
    private Double totalDebtToOwnersFund;
    private Double currentRatio;
    private Double npByCapitalEmployed;
    private Double reportedReturnOnNetWorth;
    private Double netProfitMargin;
    private Double dividendPayoutRatioCp;
    private Double reportedEps;
    private Double priceToBookValue;
    private Double reportedCashEps;
    private Double divYieldPer;
    private Double operatingMargin;
    private Double returnOnAssets;
    private Double cashRatio;
    private Double inventoryTurnoverRatio;
    private Double workingCapitalTurnoverRatio;
    private Double debtToAssetRatio;
    private Double quickRatio;
    private String financialType;
    private String createdAt;
    private String updatedAt;
}

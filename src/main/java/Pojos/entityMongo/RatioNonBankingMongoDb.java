package Pojos.entityMongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "RatioNonBankingMongoDb")
public class RatioNonBankingMongoDb {

    @Id
    private String id;
    private Integer companyId;
    private String companyName;
    private Long companyCode;
    private String entityType;
    private String yearEnding;
    private Integer months;
    private String type;
    private Double faceValue;
    private Double basicEpc;
    private Double dilutedEpc;
    private Double cashEpc;
    private Double bvPerShareExclRevalReserve;
    private Double bvPerShareInclRevalReserve;
    private Double dividendPerShare;
    private Double operatingRevenuePerShare;
    private Double pbditPerShare;
    private Double pbitPerShare;
    private Double pbtPerShare;
    private Double npPerShare;
    private Double pbditMargin;
    private Double pbitMargin;
    private Double pbtMargin;
    private Double npMargin;
    private Double ronw;
    private Double roce;
    private Double returnOnAssets;
    private Double longTermDebtEquity;
    private Double debtEquity;
    private Double assetTurnOver;
    private Double currentRatio;
    private Double quickRatio;
    private Double inventoryTurnoverRatio;
    private Double dividendPayoutNp;
    private Double dividendPayoutCp;
    private Double earningRetention;
    private Double cashEarningRetention;
    private Double interestCoverage;
    private Double interestCoveragePostTax;
    private Double enterpriseValue;
    private Double evPerNetSale;
    private Double evPerEbitda;
    private Double marketCapPerSales;
    private Double retentionRatio;
    private Double priceToBv;
    private Double priceToSale;
    private Double earningsYield;
    private String modifiedDate;

    private Double inventoryTernoverRatio;

}

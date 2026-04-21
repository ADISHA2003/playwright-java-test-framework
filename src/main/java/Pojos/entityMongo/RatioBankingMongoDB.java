package Pojos.entityMongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "RatioBankingMongoDB")
public class RatioBankingMongoDB {

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
    private Double basicEps;
    private Double dilutedEps;
    private Double cashEps;
    private Double bvPerShareExclRevalReserve;
    private Double bvPerShareInclRevalReserve;
    private Double devidendPerShare;
    private Double operatingRevenuePerShare;
    private Double npPerShare;
    private Double interestIncomePerEmployee;
    private Double npPerEmployee;
    private Double businessPerEmployee;
    private Double interestIncomePerBranch;
    private Double npPerBranches;
    private Double businessPerBranches;
    private Double npm;
    private Double opm;
    private Double roa;
    private Double roe;
    private Double nim;
    private Double costToIncome;
    private Double interestIncomeByEarningAssets;
    private Double nonInterestIncomeByEarningAssets;
    private Double operatingProfitByEarningAssets;
    private Double operatingExpensesByEarningAssets;
    private Double interestExpensesByEarningAssets;
    private Double enterpriceValues;
    private Double evPerNetSale;
    private Double priceToBv;
    private Double priceToSales;
    private Double retentionRatio;
    private Double earningsYield;
    private String modifiedDate;

}

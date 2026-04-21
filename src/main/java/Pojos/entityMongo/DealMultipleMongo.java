package Pojos.entityMongo;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "dealMultiple")
public class DealMultipleMongo {

    @Id
    private String id;
    private Integer dealMultipleId;

    private Integer transactionId;
    private String transactionType;
    private String transactionDate;
    private Integer targetCompanyId;
    private String sectorId;
    private String industryGroupId;
    private String industryId;
    private String subIndustryId;
    private Double impliedEquityValue;
    private Double impliedEnterpriseValue;
    private Double price;
    private Double dealValue;
    private Integer financialYear;
    private Double minorityInterest;
    private Double totalDebt;
    private Double cashBankBalance;
    private Double totalPreferred;
    private Double totalIncome;
    private Double totalAssets;
    private Double ebitda;
    private Double pbt;
    private Double ebit;
    private Double pat;
    private Double eps;
    private Double bookValue;
    private Double pe;
    private Double equityValueBookValue;
    private Double evTotalIncome;
    private Double evEbitda;
    private Double evTotalAssets;
    private Double evEbit;
    private Double evPbt;
    private Double evPat;
    private Double impliedEquityValueUsd;
    private Double impliedEnterpriseValueUsd;
    private Double percentageSought;
    private String financialType;
    private Date updatedAt;
    private Date createdAt;

    // Getters and Setters
}

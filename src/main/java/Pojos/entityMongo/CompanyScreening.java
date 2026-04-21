package Pojos.entityMongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "companyScreening")
public class CompanyScreening {


    @Id
    private String id;

    private Integer companyId;
    private String companyName;
    private String companyLogo;
    private String companyStatus;
    private Integer foundedYear;
    private String dba;
    private String website;
    private Contact contact;
    private String businessDescription;
    private String fundingStatus;
    private Number totalFunding;
    private String corporateIdentityNumber;
    private String valuationClass;
    private Integer financialYear;
    private String financialType;
    private Double netSales;
    private Double totalIncome;
    private Double depreciation;
    private Double pbt;
    private Double pat;
    private Double epsBasic;
    private Double equityPaidUp;
    private Double reservesSurplus;
    private Double grossBlock;
    private Double cashBankBalance;
    private Double cashFlowOperations;
    private Double cashFlowInvesting;
    private Double cashFlowFinancing;
    private Double debtEquity;
    private Double patMargin;
    private Double cpm;
    private Double totalExpenditure;
    private Double totalCurrentLiabilities;
    private Double investments;
    private Double totalAssets;
    private List<Taxonomy> taxonomy;
    private List<InvestorInfo> investorInfo;

    // current requirements
    private Double grossMargin;
    private boolean acquisitionStatus;
    private String headquarters;
    private Double cashRatio;
    private Double financingCashFlow;
    private Double investingCashFlow;
    private Double operationsCashFlow;
    private String investorType;
    private String investorName;
    private String companyType;
    private Double workingCapital;
    private Double currentRatio;
    private Long acquisitionDate;
    private Double debtToEquityRatio;
    private String acquiredBy;
    private Double ebit;
    private Double ebitda;
    private Double ebitdaMargin;
    private Double evToEbitdaRatio;
    private String ipoExchange;
    private Double netBlock;
    private String investmentGradeRating;
    private Long incorporationDate;
    private Double inventoryTurnoverRatio;
    private String investorHeadquarters;
    private Double acquisitionPrice;
    private Double postMoneyValuation;
    private Integer noOfMarqueeInvestors;
    private String governanceRating;
    private String xCornFlag;
    private Double longTermBorrowings;
    private Double roe;
    private Double annualRevenue;
    private List<Map<String, Number>> revenueCAGR;
    private Double netWorth;
    private Double operatingMargin;
    private Double netProfit;
    private Double netProfitMargin;
    private Double pbRatio;
    private Double peRatio;
    private boolean hasVentureDebt;
    private Double rdExpensesAsPercentageOfRevenue;
    private List<Map<String, Number>> revenueGrowthYoY;
    private Double quickRatio;
    private Double reservesAndSurplus;
    private Double roce;
    private Double ronw;
    private Double shortTermBorrowings;
    private Double debtToEbitdaRatio;
    private Double totalCurrentAssets;
    private Double currentLiabilities;
    private Double totalDebt;
    private Double totalEquityFundingRaised;
    private Long dateOfLastFunding;
    private Double lastEquityFundingRaised;
    private Long ipoDate;
    private List<Map<String, Number>> ebitdaCAGR;
    private List<Map<String, Number>> netProfitCAGR;
    private Double netCashFlow;
    private Double netCashFlowToEquity;
    private Double interestCoverageRatio;
    private Double roa;
    private Long employeeCount;
    private String mongoId;

    @LastModifiedDate
    private Date updatedAt;
    @CreatedDate
    private Date createdAt;
}//debtToEquityRatio
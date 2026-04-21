package Pojos.entityMongo;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data

@Document(collection = "PrivateFinancialsBankingMongoDb")
public class PrivateFinancialsBankingMongoDb {



        @Id
        private int id;
        private Integer companyId;
        private String yearEnd;
        private String financialType;
        private Float conversionFactor;
        private String financialFormat;
        private Integer updatedOn;
        private Integer fromDate;
        private Integer toDate;
        private String reportingStandard;
        private Double interestEarned;
        private Double otherIncome;
        private Double totalIncome;
        private Double operatingExpenses;
        private Double netOperatingExpenses;
        private Double provisionsAndContingencies;
        private Double totalExpenditure;
        private Double ebitda;
        private Double depreciation;
        private Double ebit;
        private Double interestExpended;
        private Double pbt;
        private Double tax;
        private Double priorPeriodItems;
        private Double extraOrdinaryItems;
        private Double others;
        private Double netProfitForTheYear;
        private Double profitLossBroughtForward;
        private Double totalProfitLoss;
        private Double transferToStatutoryReserve;
        private Double transferToCapitalReserve;
        private Double transferToGeneralReserve;
        private Double dividendPaid;
        private Double others2;
        private Double balanceInProfitLoss;
        private Double totalAppropriations;
        private Double equityCapital;
        private Double shareCapitalDeposit;
        private Double employeeStockOptions;
        private Double reservesSurplus;
        private Double shareApplPendingAllot;
        private Double totalShareholdersFunds;
        private Double deposits;
        private Double borrowings;
        private Double totalDebt;
        private Double otherLiabilitiesAndProvisions;
        private Double totalLiabilities;
        private Double totalCapitalLiabilities;
        private Double cashAndBalancesWithReserveBankOfIndia;
        private Double balancesWithBanksAndMoney;
        private Double investments;
        private Double advances;
        private Double fixedAssets;
        private Double otherAssets;
        private Double totalAssets;
        private Double contingentLiabilities;
        private Double billsForCollection;
        private Double cashFlowOperations;
        private Double cashFlowInvesting;
        private Double cashFlowFinancing;
        private Double freeCashFlow;
        private Double totalPreferred;
        private Double sharesOs;
        private Double sharesOsFullyDiluted;
        private Double epsBasic;
        private Double epsDiluted;
        private Double dividend;
        private Double debtEquity;
        private Double currentRatio;
        private Double roce;
        private Double ronw;
        private Double ebidtaMargin;
        private Double patMargin;
        private Double cpm;
        private Integer priorityCheck;
        private String pfhDescription;
}



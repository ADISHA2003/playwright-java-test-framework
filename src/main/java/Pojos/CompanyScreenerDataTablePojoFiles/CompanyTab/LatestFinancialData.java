package Pojos.CompanyScreenerDataTablePojoFiles.CompanyTab;

public class LatestFinancialData {
    private Double netProfitMargin;
    private Double revenueCAGR;
    private Double ebitdaMargin;
    private FinancialData ebitda;
    private Double ebitdaCAGR;
    private Double grossMargin;
    private FinancialData annualRevenue;
    private FinancialData netProfit;

    // Getters and setters
    public Double getNetProfitMargin() {
        return netProfitMargin;
    }

    public void setNetProfitMargin(Double netProfitMargin) {
        this.netProfitMargin = netProfitMargin;
    }

    public Double getRevenueCAGR() {
        return revenueCAGR;
    }

    public void setRevenueCAGR(Double revenueCAGR) {
        this.revenueCAGR = revenueCAGR;
    }

    public Double getEbitdaMargin() {
        return ebitdaMargin;
    }

    public void setEbitdaMargin(Double ebitdaMargin) {
        this.ebitdaMargin = ebitdaMargin;
    }

    public FinancialData getEbitda() {
        return ebitda;
    }

    public void setEbitda(FinancialData ebitda) {
        this.ebitda = ebitda;
    }

    public Double getEbitdaCAGR() {
        return ebitdaCAGR;
    }

    public void setEbitdaCAGR(Double ebitdaCAGR) {
        this.ebitdaCAGR = ebitdaCAGR;
    }

    public Double getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(Double grossMargin) {
        this.grossMargin = grossMargin;
    }

    public FinancialData getAnnualRevenue() {
        return annualRevenue;
    }

    public void setAnnualRevenue(FinancialData annualRevenue) {
        this.annualRevenue = annualRevenue;
    }

    public FinancialData getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(FinancialData netProfit) {
        this.netProfit = netProfit;
    }
}

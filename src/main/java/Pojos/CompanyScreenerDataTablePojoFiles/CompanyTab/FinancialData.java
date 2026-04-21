package Pojos.CompanyScreenerDataTablePojoFiles.CompanyTab;

public class FinancialData {
    private Double amount;
    private String actualDealCurrency;
    private String actualScale;
    private String convertedScale;
    private String convertedDealCurrency;
    private Double conversionRate;
    private Double actualDealValue;
    private boolean greater;

    // Getters and setters
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getActualDealCurrency() {
        return actualDealCurrency;
    }

    public void setActualDealCurrency(String actualDealCurrency) {
        this.actualDealCurrency = actualDealCurrency;
    }

    public String getActualScale() {
        return actualScale;
    }

    public void setActualScale(String actualScale) {
        this.actualScale = actualScale;
    }

    public String getConvertedScale() {
        return convertedScale;
    }

    public void setConvertedScale(String convertedScale) {
        this.convertedScale = convertedScale;
    }

    public String getConvertedDealCurrency() {
        return convertedDealCurrency;
    }

    public void setConvertedDealCurrency(String convertedDealCurrency) {
        this.convertedDealCurrency = convertedDealCurrency;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Double getActualDealValue() {
        return actualDealValue;
    }

    public void setActualDealValue(Double actualDealValue) {
        this.actualDealValue = actualDealValue;
    }

    public boolean isGreater() {
        return greater;
    }

    public void setGreater(boolean greater) {
        this.greater = greater;
    }
}

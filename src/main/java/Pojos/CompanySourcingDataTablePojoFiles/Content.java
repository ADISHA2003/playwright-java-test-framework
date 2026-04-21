
package Pojos.CompanySourcingDataTablePojoFiles;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "companyId",
    "companyName",
    "companyLogo",
    "companyStatus",
    "foundedYear",
    "dba",
    "website",
    "contact",
    "businessDescription",
    "corporateIdentityNumber",
    "investorInfo",
    "acquisitionStatus",
    "companyType",
    "acquisitionDate",
    "acquiredBy",
    "acquisitionPrice",
    "hasVentureDebt",
    "valuationClass",
    "ipoExchange",
    "xcornFlag"
})
@Generated("jsonschema2pojo")
public class Content {

    @JsonProperty("id")
    private String id;
    @JsonProperty("companyId")
    private Integer companyId;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("companyLogo")
    private String companyLogo;
    @JsonProperty("companyStatus")
    private String companyStatus;
    @JsonProperty("foundedYear")
    private Integer foundedYear;
    @JsonProperty("dba")
    private String dba;
    @JsonProperty("website")
    private String website;
    @JsonProperty("contact")
    private Contact contact;
    @JsonProperty("businessDescription")
    private String businessDescription;
    @JsonProperty("corporateIdentityNumber")
    private String corporateIdentityNumber;
    @JsonProperty("investorInfo")
    private List<InvestorInfo> investorInfo;
    @JsonProperty("acquisitionStatus")
    private Boolean acquisitionStatus;
    @JsonProperty("companyType")
    private String companyType;
    @JsonProperty("acquisitionDate")
    private Long acquisitionDate;
    @JsonProperty("acquiredBy")
    private String acquiredBy;
    @JsonProperty("acquisitionPrice")
    private Double acquisitionPrice;
    @JsonProperty("hasVentureDebt")
    private Boolean hasVentureDebt;
    @JsonProperty("valuationClass")
    private String valuationClass;
    @JsonProperty("ipoExchange")
    private String ipoExchange;
    @JsonProperty("xcornFlag")
    private String xcornFlag;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("companyId")
    public Integer getCompanyId() {
        return companyId;
    }

    @JsonProperty("companyId")
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("companyLogo")
    public String getCompanyLogo() {
        return companyLogo;
    }

    @JsonProperty("companyLogo")
    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    @JsonProperty("companyStatus")
    public String getCompanyStatus() {
        return companyStatus;
    }

    @JsonProperty("companyStatus")
    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    @JsonProperty("foundedYear")
    public Integer getFoundedYear() {
        return foundedYear;
    }

    @JsonProperty("foundedYear")
    public void setFoundedYear(Integer foundedYear) {
        this.foundedYear = foundedYear;
    }

    @JsonProperty("dba")
    public String getDba() {
        return dba;
    }

    @JsonProperty("dba")
    public void setDba(String dba) {
        this.dba = dba;
    }

    @JsonProperty("website")
    public String getWebsite() {
        return website;
    }

    @JsonProperty("website")
    public void setWebsite(String website) {
        this.website = website;
    }

    @JsonProperty("contact")
    public Contact getContact() {
        return contact;
    }

    @JsonProperty("contact")
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @JsonProperty("businessDescription")
    public String getBusinessDescription() {
        return businessDescription;
    }

    @JsonProperty("businessDescription")
    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }

    @JsonProperty("corporateIdentityNumber")
    public String getCorporateIdentityNumber() {
        return corporateIdentityNumber;
    }

    @JsonProperty("corporateIdentityNumber")
    public void setCorporateIdentityNumber(String corporateIdentityNumber) {
        this.corporateIdentityNumber = corporateIdentityNumber;
    }

    @JsonProperty("investorInfo")
    public List<InvestorInfo> getInvestorInfo() {
        return investorInfo;
    }

    @JsonProperty("investorInfo")
    public void setInvestorInfo(List<InvestorInfo> investorInfo) {
        this.investorInfo = investorInfo;
    }

    @JsonProperty("acquisitionStatus")
    public Boolean getAcquisitionStatus() {
        return acquisitionStatus;
    }

    @JsonProperty("acquisitionStatus")
    public void setAcquisitionStatus(Boolean acquisitionStatus) {
        this.acquisitionStatus = acquisitionStatus;
    }

    @JsonProperty("companyType")
    public String getCompanyType() {
        return companyType;
    }

    @JsonProperty("companyType")
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    @JsonProperty("acquisitionDate")
    public Long getAcquisitionDate() {
        return acquisitionDate;
    }

    @JsonProperty("acquisitionDate")
    public void setAcquisitionDate(Long acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    @JsonProperty("acquiredBy")
    public String getAcquiredBy() {
        return acquiredBy;
    }

    @JsonProperty("acquiredBy")
    public void setAcquiredBy(String acquiredBy) {
        this.acquiredBy = acquiredBy;
    }

    @JsonProperty("acquisitionPrice")
    public Double getAcquisitionPrice() {
        return acquisitionPrice;
    }

    @JsonProperty("acquisitionPrice")
    public void setAcquisitionPrice(Double acquisitionPrice) {
        this.acquisitionPrice = acquisitionPrice;
    }

    @JsonProperty("hasVentureDebt")
    public Boolean getHasVentureDebt() {
        return hasVentureDebt;
    }

    @JsonProperty("hasVentureDebt")
    public void setHasVentureDebt(Boolean hasVentureDebt) {
        this.hasVentureDebt = hasVentureDebt;
    }

    @JsonProperty("valuationClass")
    public String getValuationClass() {
        return valuationClass;
    }

    @JsonProperty("valuationClass")
    public void setValuationClass(String valuationClass) {
        this.valuationClass = valuationClass;
    }

    @JsonProperty("ipoExchange")
    public String getIpoExchange() {
        return ipoExchange;
    }

    @JsonProperty("ipoExchange")
    public void setIpoExchange(String ipoExchange) {
        this.ipoExchange = ipoExchange;
    }

    @JsonProperty("xcornFlag")
    public String getXcornFlag() {
        return xcornFlag;
    }

    @JsonProperty("xcornFlag")
    public void setXcornFlag(String xcornFlag) {
        this.xcornFlag = xcornFlag;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

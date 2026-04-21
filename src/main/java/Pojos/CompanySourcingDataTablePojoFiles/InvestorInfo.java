
package Pojos.CompanySourcingDataTablePojoFiles;

import java.util.LinkedHashMap;
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
    "investorId",
    "name",
    "investorType",
    "relationshipType",
    "percentageStake",
    "stakeType",
    "capitalCommitted",
    "creationTimestamp",
    "createdAt",
    "updatedAt"
})
@Generated("jsonschema2pojo")
public class InvestorInfo {

    @JsonProperty("investorId")
    private String investorId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("investorType")
    private String investorType;
    @JsonProperty("relationshipType")
    private String relationshipType;
    @JsonProperty("percentageStake")
    private Double percentageStake;
    @JsonProperty("stakeType")
    private String stakeType;
    @JsonProperty("capitalCommitted")
    private Object capitalCommitted;
    @JsonProperty("creationTimestamp")
    private Object creationTimestamp;
    @JsonProperty("createdAt")
    private Object createdAt;
    @JsonProperty("updatedAt")
    private Object updatedAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("investorId")
    public String getInvestorId() {
        return investorId;
    }

    @JsonProperty("investorId")
    public void setInvestorId(String investorId) {
        this.investorId = investorId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("investorType")
    public String getInvestorType() {
        return investorType;
    }

    @JsonProperty("investorType")
    public void setInvestorType(String investorType) {
        this.investorType = investorType;
    }

    @JsonProperty("relationshipType")
    public String getRelationshipType() {
        return relationshipType;
    }

    @JsonProperty("relationshipType")
    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    @JsonProperty("percentageStake")
    public Double getPercentageStake() {
        return percentageStake;
    }

    @JsonProperty("percentageStake")
    public void setPercentageStake(Double percentageStake) {
        this.percentageStake = percentageStake;
    }

    @JsonProperty("stakeType")
    public String getStakeType() {
        return stakeType;
    }

    @JsonProperty("stakeType")
    public void setStakeType(String stakeType) {
        this.stakeType = stakeType;
    }

    @JsonProperty("capitalCommitted")
    public Object getCapitalCommitted() {
        return capitalCommitted;
    }

    @JsonProperty("capitalCommitted")
    public void setCapitalCommitted(Object capitalCommitted) {
        this.capitalCommitted = capitalCommitted;
    }

    @JsonProperty("creationTimestamp")
    public Object getCreationTimestamp() {
        return creationTimestamp;
    }

    @JsonProperty("creationTimestamp")
    public void setCreationTimestamp(Object creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    @JsonProperty("createdAt")
    public Object getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updatedAt")
    public Object getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updatedAt")
    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
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

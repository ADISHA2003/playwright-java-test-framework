
package Pojos.GlobalSearchComponentPojoFiles;

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
    "companies",
    "investors",
    "serviceProviders",
    "funds",
    "professionals",
    "sector",
    "report",
    "documents"
})
@Generated("jsonschema2pojo")
public class GlobalSearchComponentPojo {

    @JsonProperty("companies")
    private Companies companies;
    @JsonProperty("investors")
    private Object investors;
    @JsonProperty("serviceProviders")
    private Object serviceProviders;
    @JsonProperty("funds")
    private Object funds;
    @JsonProperty("professionals")
    private Object professionals;
    @JsonProperty("sector")
    private Object sector;
    @JsonProperty("report")
    private Object report;
    @JsonProperty("documents")
    private Object documents;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("companies")
    public Companies getCompanies() {
        return companies;
    }

    @JsonProperty("companies")
    public void setCompanies(Companies companies) {
        this.companies = companies;
    }

    @JsonProperty("investors")
    public Object getInvestors() {
        return investors;
    }

    @JsonProperty("investors")
    public void setInvestors(Object investors) {
        this.investors = investors;
    }

    @JsonProperty("serviceProviders")
    public Object getServiceProviders() {
        return serviceProviders;
    }

    @JsonProperty("serviceProviders")
    public void setServiceProviders(Object serviceProviders) {
        this.serviceProviders = serviceProviders;
    }

    @JsonProperty("funds")
    public Object getFunds() {
        return funds;
    }

    @JsonProperty("funds")
    public void setFunds(Object funds) {
        this.funds = funds;
    }

    @JsonProperty("professionals")
    public Object getProfessionals() {
        return professionals;
    }

    @JsonProperty("professionals")
    public void setProfessionals(Object professionals) {
        this.professionals = professionals;
    }

    @JsonProperty("sector")
    public Object getSector() {
        return sector;
    }

    @JsonProperty("sector")
    public void setSector(Object sector) {
        this.sector = sector;
    }

    @JsonProperty("report")
    public Object getReport() {
        return report;
    }

    @JsonProperty("report")
    public void setReport(Object report) {
        this.report = report;
    }

    @JsonProperty("documents")
    public Object getDocuments() {
        return documents;
    }

    @JsonProperty("documents")
    public void setDocuments(Object documents) {
        this.documents = documents;
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

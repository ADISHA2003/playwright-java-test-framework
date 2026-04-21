
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
    "pageNumber",
    "pageSize",
    "sort",
    "offset",
    "paged",
    "unpaged"
})
@Generated("jsonschema2pojo")
public class Pageable {

    @JsonProperty("pageNumber")
    private Integer pageNumber;
    @JsonProperty("pageSize")
    private Integer pageSize;
    @JsonProperty("sort")
    private Sort sort;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("paged")
    private Boolean paged;
    @JsonProperty("unpaged")
    private Boolean unpaged;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("pageNumber")
    public Integer getPageNumber() {
        return pageNumber;
    }

    @JsonProperty("pageNumber")
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    @JsonProperty("pageSize")
    public Integer getPageSize() {
        return pageSize;
    }

    @JsonProperty("pageSize")
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @JsonProperty("sort")
    public Sort getSort() {
        return sort;
    }

    @JsonProperty("sort")
    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    @JsonProperty("offset")
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @JsonProperty("paged")
    public Boolean getPaged() {
        return paged;
    }

    @JsonProperty("paged")
    public void setPaged(Boolean paged) {
        this.paged = paged;
    }

    @JsonProperty("unpaged")
    public Boolean getUnpaged() {
        return unpaged;
    }

    @JsonProperty("unpaged")
    public void setUnpaged(Boolean unpaged) {
        this.unpaged = unpaged;
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

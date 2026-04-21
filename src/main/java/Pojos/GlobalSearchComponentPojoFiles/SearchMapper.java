
package Pojos.GlobalSearchComponentPojoFiles;

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
    "name",
    "description",
    "logo",
    "keyPoints",
    "matched"
})
@Generated("jsonschema2pojo")
public class SearchMapper {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("logo")
    private Object logo;
    @JsonProperty("keyPoints")
    private List<KeyPoint> keyPoints;
    @JsonProperty("matched")
    private Matched matched;
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("logo")
    public Object getLogo() {
        return logo;
    }

    @JsonProperty("logo")
    public void setLogo(Object logo) {
        this.logo = logo;
    }

    @JsonProperty("keyPoints")
    public List<KeyPoint> getKeyPoints() {
        return keyPoints;
    }

    @JsonProperty("keyPoints")
    public void setKeyPoints(List<KeyPoint> keyPoints) {
        this.keyPoints = keyPoints;
    }

    @JsonProperty("matched")
    public Matched getMatched() {
        return matched;
    }

    @JsonProperty("matched")
    public void setMatched(Matched matched) {
        this.matched = matched;
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

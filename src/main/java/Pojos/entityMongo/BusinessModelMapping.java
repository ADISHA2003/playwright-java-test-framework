package Pojos.entityMongo;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "BusinessModelMapping")
@Data
public class BusinessModelMapping {
    @Id
    private String id;

    private String businessModelId;
    private String sector;
    private String industry;
    private String subIndustry;
    private String category;
    private List<String> competingBusinessModels;
    private Map<String, String> levels;
    private String description;
    private Boolean geoRelevance;
    private String marketType;
    private String productionModel;
    private String growth;
    private Boolean mobileAppFirst;
    private Boolean ipRelevance;
    private String capexDriven;
    private String gestationPeriod;
    private String businessGrowthModel;
    private String nodeEnabled;
}

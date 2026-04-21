package Pojos.entityMongo;

import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
public class Taxonomy {

    @Id
    private String id;

    private String businessModelId;
    private String sector;
    private String industry;
    private String subIndustry;
    private String category;
    private List<String> competingBusinessModels;
    private boolean nodeEnabled;
    private Level levels;
    private List<String> levelList;
    private String description;
    private List<String> geoRelevance;
    private String marketType;
    private String productionModel;
    private String growth;
    private boolean mobileAppFirst;
    private String ipRelevance;
    private boolean capexDriven;
    private String gestationPeriod;

    @Data
    public class Level {
        private String level1;
        private String level2;
        private String level3;
        private String level4;
    }

}

package Pojos.entityMongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "keyEventDetails")
@Data
public class KeyEventDetailsMongoDb {

    @Id
    private String id;

    private Integer keyEventId;

    private String addedDate;

    private Date keyEventDate;

    private String keyEventGroup;

    private String keyEventType;

    private RelationshipBasicInfoForKd targetCompany;

    private List<TargetCompanyClassification> targetCompanyDetails;

    private List<RelationShipBasicInfo> relatedCompanies;

    private String headline;

    private String description;

    private String sourceName;

    private String url;

    private Integer newsType; // 1 - confirmed, 2 - rumor

    private Integer transactionId;

    private String stage;

    private Integer processedBy;

    private Integer flag;

    private Date updatedAt;

    private Date createdAt;

    @Data
    public static class TargetCompanyClassification {
        private String sector;
        private String industry;
        private String subIndustry;
    }
}
package Pojos.entityMongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "IndustryDocumentAccessMongoDb")
public class IndustryDocumentAccessMongoDb {

    @Id
    private String id;
    private Integer documentId;
    private String source;
    private String documentCategory;
    private String documentType;
    private String language;
    private String headline;
    private String documentDate;
    private String uploadedDate;
    private Integer uploadedBy;
    private String format;
    private String originalFileName;
    private String sector;
    private String md5Hash;
    private String industryGroupId;
    private String industryId;
    private String subIndustryId;
    private String macroEconomic;
    private String description;
    private String updatedAt;
    private String createdAt;

    // Getters and Setters
}


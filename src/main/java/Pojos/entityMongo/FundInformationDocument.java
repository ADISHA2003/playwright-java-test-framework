package Pojos.entityMongo;


import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "FundInformation")
public class FundInformationDocument {

    @Id
    private Integer fundInformationId; // MongoDB typically uses String for ID fields

    private Integer fundManagerId;
    private Integer fundId;
    private Integer transactionId;
    private String announcedDate;
    private Double stakeValue;
    private Integer peType;
    private Integer targetCompanyId;
    private Integer sectorId;
    private Integer industryGroupId;
    private Integer industryId;
    private Integer subIndustryId;
    private String sectorTheme;
    private String sectorTag;
    private Boolean screenable;
    private String dealType;
    private Double dealValue;
    private Date updatedAt;
    private Date createdAt;
    private String entityType;
}

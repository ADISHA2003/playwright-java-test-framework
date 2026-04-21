package Pojos.entityMongo;


import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Splits")
public class SplitsMongoDb {



    @Id
    private String id;
    private Integer companyId;
    private String companyName;
    private String companyCode;
    private Integer splitsId;
    private String entityType;
    private String dateOfAnnouncement;
    private Integer oldFaceValue;
    private Integer newFaceValue;
    private String recordDate;
    private String bookClosureStartDate;
    private String bookClosureEndDate;
    private String xsDate;
    private String modifiedDate;
    private String deleteFlag;
}
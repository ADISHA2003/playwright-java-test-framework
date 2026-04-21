package Pojos.entityMongo;


import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "RightDetails")
public class RightDetailsMongoDb {

    @Id
    private String id;
    private Integer companyId;
    private String companyName;
    private String companyCode;
    private String entityType;
    private Integer rightsDetailsId;
    private String yearEnding;
    private String ratio;
    private Double faceValueExistingInstrument;
    private Double rightsPremium;
    private String existingInstrumentName;
    private String xrDate;
    private String recordDate;
    private String modifiedDate;
}

package Pojos.entityMongo;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "dealSourcing")
public class DealSourcing {

    @Id
    private String id;  // MongoDB document ID

    private String fieldName;
    private String dealSubtype;
    private String dealFeature;
    private String dealStatus;
    private Long transactionAnnouncedDate;
    private Long transactionClosingDate;
    private Long cancelledDate;
    private String dealStage;
    private String changeInControl;
    private Double percentageSought;
    private Double evRevenue;
    private Double evEbitda;
    private Double evPat;
    private Long dealDate;
    private String dealDescription;
    private String targetCompany;
    private String dealType;
    private String buyer;
    private String seller;
    private Double dealValue;

    // Getters and Setters
}

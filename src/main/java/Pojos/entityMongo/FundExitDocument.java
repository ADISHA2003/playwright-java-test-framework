package Pojos.entityMongo;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "FundExit")
public class FundExitDocument {

    @Id
    private Integer id; // MongoDB typically uses String for IDs

    private Integer assetManagerId;
    private Integer fundId;
    private Integer transactionId;
    private Integer targetCompanyId;
    private String sector;
    private String announcedDate;
    private String transactionType;
    private Double exitDealValue;

    // No explicit getters and setters needed due to @Data
}
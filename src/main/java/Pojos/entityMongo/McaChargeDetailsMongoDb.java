package Pojos.entityMongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "McaChargeDetailsMongoDb")
public class McaChargeDetailsMongoDb {

    @Id
    private String id;
    private Integer companyId;
    private Long companyCode;
    private String companyName;
    private String entityType;
    private String cinNumber;
    private String srn;
    private Integer chargeId;
    private String chargeHolderName;
    private String dateOfCreation;
    private String dateOfModification;
    private String dateOfSatisfaction;
    private Double amount;
    private String address;
    private String createdAt;
    private String updatedAt;
    private Integer createdBy;
    private Integer updatedBy;

}

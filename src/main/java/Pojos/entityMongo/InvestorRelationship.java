package Pojos.entityMongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "investorRelationship")
public class InvestorRelationship {

    @Id
    private String id;

    private Integer investorRelationshipId;
    private Integer investorTransactionId;
    private Integer investorId;
    private String investorRelationshipType;
    private String buyerSellerStatus;
    private Double stakeValue;
    private Double totalStakeHeld;
    private Double stakeAcquireSold;
    private InvestorObjForRelationship investorObj;
    private Date updatedAt;
    private Date createdAt;

    // Constructors, getters, and setters
}

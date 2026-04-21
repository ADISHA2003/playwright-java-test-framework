package Pojos.entityMongo;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "BonusMongoDb")
public class BonusMongoDb {


    @Id
    private String id;
    private Integer companyId;
    private String companyName;
    private String entityType;
    private String companyCode;
    private Integer bonusId;
    private String yearEnding;
    private String ratio;
    private String xbDate;
    private String modifiedDate;
}

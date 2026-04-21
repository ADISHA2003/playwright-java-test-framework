package Pojos.entityMongo;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "DividendMongoDb")
public class DividendMongoDb {

    @Id
    private String id;
    private Integer companyId;
    private String companyName;
    private Long companyCode;

    private String entityType;
    private Integer dividendId;
    private String dateOfAnnouncement;
    private String interimOrFinal;
    private Byte instrumentType;
    private String instrumentTypeDescription;
    private Double percentage;
    private Double value;
    private String recordDate;
    private String bookClosureStartDate;
    private String bookClosureEndDate;
    private String remarks;
    private String modifiedDate;
    private String delFlag;
    private String xdDate;

}

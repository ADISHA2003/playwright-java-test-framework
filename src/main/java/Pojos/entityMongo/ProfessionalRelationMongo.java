package Pojos.entityMongo;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@Data
@Document(collection = "professionalRelation")
public class ProfessionalRelationMongo {
    @Id
    private String id;
    private Integer professionalRelationId;
    private Map<String, Object> company;
    private Map<String, Object> professional;
    private String title;
    private String status;
    private String fromDate;
    private String toDate;
    private String type;
    private Integer proFunction;
    private Boolean primaryCheck;
    private Integer creationTimestamp;
    private Date updatedAt;
    private Date createdAt;
}
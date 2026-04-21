package Pojos.entityMongo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "SourceNames")
public class SourceNamesMongoDb {

    @Id
    private String id;

    private Integer sourceNameId;
    private String sourceName;
    private String urlName;
    private Integer flagStatus;
    private Integer companyId;
    private Integer kdPage;
    private Date createdAt;
    private Date updatedAt;
}
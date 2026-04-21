package Pojos.entityMongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tableMetadata") 
public class TableMetadata {

    @Id
    private String id;

    private String name; 
    private boolean isActive; 

    
}


package Pojos.entityMongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "professional")
public class Professional {
    @Id
    private String id;
    private Integer professionalId;
    private Integer companyId;
    private String professionalName;
    private String firstName;
    private String middleName;
    private String lastName;
    private String biography;
    private String companyPhoneNumber;
    private String directPhoneNumber;
    private String fax;
    private String email;
    private String linkedinLink;
    private Boolean angelFlag;
    private String dinNumber;
    private Date updatedAt;
    private Date createdAt;
}

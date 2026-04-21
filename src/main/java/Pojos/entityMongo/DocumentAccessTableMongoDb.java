package Pojos.entityMongo;


import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "DocumentAccessTableMongoDb")
public class DocumentAccessTableMongoDb {

        @Id
        private String id;
        private Integer documentId;
        private String source;
        private String documentCategory;
        private String documentType;
        private String companyName;
        private String language;
        private String headline;
        private String documentDate;
        private String uploadedDate;
        private Integer uploadedBy;
        private Integer transactionId;
        private String transactionType;
        private Integer targetCompanyId;
        private Integer finYearEnd;
        private String documentFormat;
        private String originalFileName;
        private String md5Hash;
        private String updatedAt;

        // Getters and Setters
}

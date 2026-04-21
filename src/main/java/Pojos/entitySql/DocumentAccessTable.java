package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "document_access_table")
public class DocumentAccessTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Integer documentId;

    @Column(name = "source")
    private Integer source;

    @Column(name = "document_category")
    private Integer documentCategory;

    @Column(name = "document_type")
    private Integer documentType;

    @Column(name = "language", length = 1000)
    private String language;

    @Column(name = "headline", length = 2000)
    private String headline;

    @Column(name = "document_date")
    private String documentDate;

    @Column(name = "uploaded_date")
    private String uploadedDate;

    @Column(name = "uploaded_by")
    private Integer uploadedBy;

    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "transaction_type")
    private Integer transactionType;

    @Column(name = "targetcompanyid")
    private Integer targetCompanyId;

    @Column(name = "fin_year_end")
    private Integer finYearEnd;

    @Column(name = "document_format", length = 100)
    private String documentFormat;

    @Column(name = "original_file_name", length = 200)
    private String originalFileName;

    @Column(name = "md5_hash", length = 200)
    private String md5Hash;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

}


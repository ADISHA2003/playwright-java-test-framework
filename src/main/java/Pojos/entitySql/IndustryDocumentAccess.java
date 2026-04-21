package Pojos.entitySql;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "industry_document_access_table")
public class IndustryDocumentAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id", nullable = false)
    private Integer documentId;

    @Column(name = "source", nullable = false)
    private Integer source;

    @Column(name = "document_category", nullable = false)
    private Integer documentCategory;

    @Column(name = "document_type", nullable = false)
    private Integer documentType;

    @Column(name = "language", nullable = false, length = 50)
    private String language;

    @Column(name = "headline", nullable = false, length = 2000)
    private String headline;

    @Column(name = "document_date", nullable = false, length = 100)
    private String documentDate;

    @Column(name = "uploaded_date", nullable = false, length = 100)
    private String uploadedDate;

    @Column(name = "uploaded_by", nullable = false)
    private Integer uploadedBy;

    @Column(name = "format", nullable = false, length = 100)
    private String format;

    @Column(name = "original_file_name", nullable = false, length = 200)
    private String originalFileName;

    @Column(name = "sector", nullable = false)
    private Integer sector;

    @Column(name = "industry_group_id", nullable = false)
    private Integer industryGroupId;

    @Column(name = "industry_id", nullable = false)
    private Integer industryId;

    @Column(name = "sub_industry_id", nullable = false)
    private Integer subIndustryId;

    @Column(name = "macro_economic", nullable = false, length = 155)
    private String macroEconomic;

    @Column(name = "others")
    private Integer others;

    @Column(name = "md5_hash", nullable = false, length = 200)
    private String md5Hash;

    @Column(name = "image_name", length = 155)
    private String imageName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT '0000-00-00 00:00:00'")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;



    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    // Getters and Setters
}

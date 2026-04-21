package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "source_names")
public class SourceNamesMySQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sourcenameid")
    private Integer sourceNameId;

    @Column(name = "sourcename", length = 100)
    private String sourceName;

    @Column(name = "url_name", length = 200)
    private String urlName;

    @Column(name = "flag_status")
    private Integer flagStatus;

    @Column(name = "companyid")
    private Integer companyId;

    @Column(name = "kd_page")
    private Integer kdPage;

    @Column(name = "updated_at", nullable = false)
    private String updatedAt;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;
}
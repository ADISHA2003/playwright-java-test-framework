package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "professionalrelation")
public class ProfessionalRelationMysql {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professionalrelationid")
    private Integer professionalRelationId;

    @Column(name = "targetcompanyid")
    private Integer targetCompanyId;

    @Column(name = "targetprofessionalid")
    private Integer targetProfessionalId;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private String status;

    @Column(name = "from_date")
    private String fromDate;

    @Column(name = "to_date")
    private String toDate;

    @Column(name = "type")
    private String type;

    @Column(name = "profunction")
    private Integer proFunction;

    @Column(name = "primary_check")
    private Integer primaryCheck;

    @Column(name = "creation_timestamp")
    private Integer creationTimestamp;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "isUpdated")
    private Boolean isUpdated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

}
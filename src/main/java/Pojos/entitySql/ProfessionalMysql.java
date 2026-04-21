package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "professional")
public class ProfessionalMysql {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professionalid")
    private Integer professionalId;

    @Column(name = "companyid")
    private Integer companyId;

    @Column(name = "professionalname")
    private String professionalName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "biography")
    private String biography;

    @Column(name = "companyphonenumber")
    private String companyPhoneNumber;

    @Column(name = "directphonenumber")
    private String directPhoneNumber;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "linkedinlink")
    private String linkedinLink;

    @Column(name = "angel_flag")
    private Integer angelFlag;

    @Column(name = "din_number")
    private String dinNumber;

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
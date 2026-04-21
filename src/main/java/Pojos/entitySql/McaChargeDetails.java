package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "mca_charge_details")
public class McaChargeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "company_id", nullable = false)
    private Integer companyId;

    @Column(name = "cin_number", nullable = false, length = 155)
    private String cinNumber;

    @Column(name = "srn", length = 55)
    private String srn;

    @Column(name = "charge_id")
    private Integer chargeId;

    @Column(name = "charge_holder_name", length = 155)
    private String chargeHolderName;

    @Column(name = "date_of_creation")
    private String dateOfCreation;

    @Column(name = "date_of_modification")
    private String dateOfModification;

    @Column(name = "date_of_satisfaction")
    private String dateOfSatisfaction;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "address", columnDefinition = "LONGTEXT")
    private String address;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private String updatedAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

}

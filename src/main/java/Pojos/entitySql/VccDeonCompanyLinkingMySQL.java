package Pojos.entitySql;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "vcc_deon_company_linking")
public class VccDeonCompanyLinkingMySQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vcc_deon_id")
    private Integer vccDeonId;

    @Column(name = "deon_company_id")
    private Double deonCompanyId;

    @Column(name = "company_name", length = 200)
    private String companyName;

    @Column(name = "vcc_company_id")
    private Integer vccCompanyId;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "created_at")
    private String createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;


}

package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "industry_group")
public class IndustryGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "industry_group_id")
    private int industryGroupId;

    @Column(name = "sector_id", nullable = false)
    private int sectorId;

    @Column(name = "industry_group_code", length = 4, nullable = false)
    private String industryGroupCode;

    @Column(name = "industry_group_name")
    private String industryGroupName;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    // Constructors, getters, and setters
}

package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "sub_industry")
public class SubIndustry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_industry_id")
    private int subIndustryId;

    @Column(name = "sales_edge_industry_id", nullable = false)
    private int salesEdgeIndustryId;

    @Column(name = "industry_id", nullable = false)
    private int industryId;

    @Column(name = "sub_industry_code", length = 8, nullable = false)
    private String subIndustryCode;

    @Column(name = "sub_industry_name", length = 128, nullable = false)
    private String subIndustryName;

    @Column(name = "display_status")
    private int displayStatus;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    // Constructors, getters, and setters
}

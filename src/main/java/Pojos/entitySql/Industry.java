package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "industry")
public class Industry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "industry_id")
    private int industryId;

    @Column(name = "industry_group_id", nullable = false)
    private int industryGroupId;

    @Column(name = "industry_code", length = 8, nullable = false)
    private String industryCode;

    @Column(name = "industry_name")
    private String industryName;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    // Constructors, getters, and setters
}


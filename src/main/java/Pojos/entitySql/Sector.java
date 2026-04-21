package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "sectors")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sector_id")
    private int sectorId;

    @Column(name = "sector_code", length = 2, nullable = false)
    private String sectorCode;

    @Column(name = "sector_name", length = 128, nullable = false)
    private String sectorName;

    @Column(name = "companies_count", nullable = false)
    private int companiesCount;

    @Column(name = "revenue_bracket", nullable = false)
    private double revenueBracket;

    @Column(name = "updated_at", nullable = true)
    private String updatedAt;

    @Column(name = "created_at", nullable = true)
    private String createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    // Getters and setters
}

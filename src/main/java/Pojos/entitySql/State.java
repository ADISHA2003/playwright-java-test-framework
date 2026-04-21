package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "state")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stateid")
    private int stateId;

    @Column(name = "statename", length = 255, nullable = true)
    private String stateName;

    @Column(name = "countryid", nullable = true)
    private int countryId;

    @Column(name = "display", nullable = true)
    private boolean display;

    @Column(name = "region_id")
    private int regionId;

    @Column(name = "abbrevation", length = 155)
    private String abbreviation;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    // Getters and setters
}

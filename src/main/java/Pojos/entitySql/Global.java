package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "global")
public class Global {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int globalid;

    private String globalname;
    private boolean display;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    // Constructors, getters, and setters
}

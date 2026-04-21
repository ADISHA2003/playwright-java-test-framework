package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int cityId;

    @Column(name = "state_id")
    private int stateId;

    @Column(name = "city_name", columnDefinition = "varchar(155) CHARACTER SET latin1 COLLATE latin1_swedish_ci")
    private String cityName;

    @Column(name = "display_status")
    private int displayStatus;

    @Column(name = "regionid")
    private int regionId;

    @Column(name = "tier")
    private int tier;

    @Column(name = "created_at", columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private LocalDateTime updatedAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    // Getters and setters
}


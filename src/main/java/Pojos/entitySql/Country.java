package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "countryid")
    private int countryId;

    @Column(name = "countryname", length = 255, columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci")
    private String countryName;

    @Column(name = "display")
    private boolean display;

    @Column(name = "isd_code", length = 155, columnDefinition = "varchar(155) CHARACTER SET utf8 COLLATE utf8_general_ci")
    private String isdCode;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    // Constructors, Getters, Setters, and toString method remain the same...
}

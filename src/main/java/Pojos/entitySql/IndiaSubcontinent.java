package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "india_sub_continent")
public class IndiaSubcontinent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer indiasubcontinentid;
    private String subcontinentname;
    private Integer continentid;
    private boolean display;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;
}

package Pojos.entitySql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "master")
public class Master {
    @Id
    @Column(name = "masterid")
    private int masterid;

    @Column(name = "mastercontentid")
    private Integer mastercontentid;

    @Column(name = "description")
    private String description;

    @Column(name = "display")
    private Boolean display;

    @Column(name = "display_order")
    private Integer display_order;
    
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "lastUpdated")
//    private Date lastUpdated;

    // Constructors, Getters, Setters, and toString method
    // (same as before)
}

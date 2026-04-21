package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "vccedge_ic")
public class VccedgeIc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vccedge_ic_id")
    private int vccedgeIcId;

    @Column(name = "vccedge_ic")
    private String vccedgeIc;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    // Constructors, getters, and setters
    public VccedgeIc() {}

    public int getVccedgeIcId() {
        return vccedgeIcId;
    }

    public void setVccedgeIcId(int vccedgeIcId) {
        this.vccedgeIcId = vccedgeIcId;
    }

    public String getVccedgeIc() {
        return vccedgeIc;
    }

    public void setVccedgeIc(String vccedgeIc) {
        this.vccedgeIc = vccedgeIc;
    }
}

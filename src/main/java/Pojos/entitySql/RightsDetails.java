package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "rights_details")
public class RightsDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rights_details_id")
    private Integer rightsDetailsId;

    @Column(name = "company_code")
    private Double companyCode;

    @Column(name = "year_ending", length = 20)
    private String yearEnding;

    @Column(name = "ratio", length = 20)
    private String ratio;

    @Column(name = "face_value_existing_instrument")
    private Double faceValueExistingInstrument;

    @Column(name = "rights_premium")
    private Double rightsPremium;

    @Column(name = "existing_instrument_name", length = 100)
    private String existingInstrumentName;

    @Column(name = "xr_date", length = 20)
    private String xrDate;

    @Column(name = "record_date", length = 20)
    private String recordDate;

    @Column(name = "modified_date", length = 20)
    private String modifiedDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;
}

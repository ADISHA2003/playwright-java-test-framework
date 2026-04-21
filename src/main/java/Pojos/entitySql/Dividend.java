package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "dividend")
public class Dividend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dividend_id")
    private Integer dividendId;

    @Column(name = "company_code")
    private Double companyCode;

    @Column(name = "date_of_announcement", length = 20)
    private String dateOfAnnouncement;

    @Column(name = "interim_or_final", length = 10)
    private String interimOrFinal;

    @Column(name = "instrument_type")
    private Byte instrumentType;

    @Column(name = "instrument_type_description", length = 100)
    private String instrumentTypeDescription;

    @Column(name = "percentage")
    private Double percentage;

    @Column(name = "value")
    private Double value;

    @Column(name = "record_date", length = 20)
    private String recordDate;

    @Column(name = "book_closure_start_date", length = 20)
    private String bookClosureStartDate;

    @Column(name = "book_closure_end_date", length = 20)
    private String bookClosureEndDate;

    @Column(name = "remarks", length = 255)
    private String remarks;

    @Column(name = "modified_date", length = 20)
    private String modifiedDate;

    @Column(name = "del_flag", length = 5)
    private String delFlag;

    @Column(name = "xd_date", length = 20)
    private String xdDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;
}

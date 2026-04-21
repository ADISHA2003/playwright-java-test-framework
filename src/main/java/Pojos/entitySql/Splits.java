package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "splits")
public class Splits  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "splits_id")
    private Integer splitsId;

    @Column(name = "company_code", length = 12, nullable = false)
    private String companyCode;

    @Column(name = "date_of_announcement", length = 20)
    private String dateOfAnnouncement;

    @Column(name = "old_face_value", nullable = false)
    private Integer oldFaceValue;

    @Column(name = "new_face_value", nullable = false)
    private Integer newFaceValue;

    @Column(name = "record_date", length = 20)
    private String recordDate;

    @Column(name = "book_closure_start_date", length = 20)
    private String bookClosureStartDate;

    @Column(name = "book_closure_end_date", length = 20)
    private String bookClosureEndDate;

    @Column(name = "xs_date", length = 20)
    private String xsDate;

    @Column(name = "modified_date", length = 20)
    private String modifiedDate;

    @Column(name = "delete_flag", length = 5, nullable = false)
    private String deleteFlag;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;
}

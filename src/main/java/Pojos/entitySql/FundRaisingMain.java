package Pojos.entitySql;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "fund_raising_main")
public class FundRaisingMain {

    @Id
    @Column(name = "fund_raising_id", nullable = false)
    private Integer fundRaisingId;

    @Column(name = "companyid")
    private Integer companyId;

    @Column(name = "launched_date")
    private String launchedDate;

    @Column(name = "target_size")
    private Double targetSize;

    @Column(name = "amount_raised")
    private Double amountRaised;

    @Column(name = "status")
    private Integer status;

    @Column(name = "total_amount_raised")
    private Double totalAmountRaised;

    @Column(name = "fr_description", columnDefinition = "LONGTEXT")
    private String frDescription;

    @Column(name = "notes", columnDefinition = "LONGTEXT")
    private String notes;

    @Column(name = "target_size_inr", nullable = false)
    private Double targetSizeInr;

    @Column(name = "amount_raised_inr", nullable = false)
    private Double amountRaisedInr;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;
}

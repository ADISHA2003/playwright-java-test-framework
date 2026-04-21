package Pojos.entitySql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;


@Data
@Entity
@Table(name = "fund_raising_stages")
public class FundRaisingStage {

    @Id
    @Column(name = "fund_raising_stage_id", nullable = false)
    private Integer fundRaisingStageId;

    @Column(name = "companyid")
    private Integer companyId;

    @Column(name = "stage")
    private Integer stage;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "stage_date")
    private Date stageDate;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;
}

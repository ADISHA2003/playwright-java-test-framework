package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
@Table(name = "transactions")
public class TransactionMySQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionid")
    private Integer transactionId;

    @Column(name = "transactiontype")
    private Integer transactionType;

    @Column(name = "detailtransactionid")
    private Integer detailTransactionId;

    @Column(name = "targetcompanyid")
    private Integer targetCompanyId;

    @Column(name = "transaction_time")
    private Integer transactionTime;

    @Column(name = "transaction_prop")
    private Integer transactionProp;

    @Column(name = "non_mna_deal")
    private Integer nonMnaDeal;

    @Column(name = "dealsizeprop")
    private Integer dealsizeprop;

    @Column(name = "screenable")
    private Integer screenable;

    @Column(name = "exclusive_deal")
    private boolean exclusiveDeal;

    @Column(name = "created_date", columnDefinition = "varchar(255)")
    private String createdDate;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "created_at")
    private String createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    // Getters and setters
}

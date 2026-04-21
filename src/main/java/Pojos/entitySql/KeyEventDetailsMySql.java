package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "key_event_details")
@Data
public class KeyEventDetailsMySql {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_event_id")
    private Integer keyEventId;

    @Column(name = "added_date")
    private String addedDate;

    @Column(name = "key_event_date")
    private String keyEventDate;

    @Column(name = "key_event_group_id")
    private Integer keyEventGroupId;

    @Column(name = "key_event_type_id")
    private Integer keyEventTypeId;

    @Column(name = "target_company_id")
    private Integer targetCompanyId;

    @Column(name = "headline")
    private String headline;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "source")
    private Integer source;

    @Column(name = "url")
    private String url;

    @Column(name = "news_type")
    private Integer newsType;

    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "stage")
    private String stage;

    @Column(name = "processed_by")
    private Integer processedBy;

    @Column(name = "flag")
    private Integer flag;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "isUpdated")
    private Boolean isUpdated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;
}
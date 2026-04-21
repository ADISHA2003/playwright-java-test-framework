package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "key_event_types")
@Data
public class KeyEventTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_event_type_id")
    private Integer keyEventTypeId;

    @Column(name = "key_event_group_id")
    private Integer keyEventGroupId;

    @Column(name = "key_event_type")
    private String keyEventType;

    @Column(name = "keyword", columnDefinition = "TEXT")
    private String keyword;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_at")
    private Date createdAt;

}
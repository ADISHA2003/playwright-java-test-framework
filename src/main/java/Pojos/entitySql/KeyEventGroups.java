package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "key_event_groups")
@Data
public class KeyEventGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_event_group_id")
    private Integer keyEventGroupId;

    @Column(name = "key_event_group")
    private String keyEventGroup;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_at")
    private Date createdAt;

}
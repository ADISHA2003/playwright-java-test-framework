package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "key_event_relationship")
@Data
public class KeyEventRelationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_relation_id")
    private Integer keyRelationId;

    @Column(name = "key_event_id")
    private Integer keyEventId;

    @Column(name = "related_company_id")
    private Integer relatedCompanyId;

    @Column(name = "relation_type")
    private Integer relationType;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_at")
    private Date createdAt;

}
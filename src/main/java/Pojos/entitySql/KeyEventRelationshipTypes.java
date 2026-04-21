package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "key_event_relationship_types")
@Data
public class KeyEventRelationshipTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_event_relationship_type_id")
    private Integer keyEventRelationshipTypeId;

    @Column(name = "key_event_relationship_type")
    private String keyEventRelationshipType;

}
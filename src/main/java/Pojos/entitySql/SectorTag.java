package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "sector_tags")
public class SectorTag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sector_tag_id")
	private int sectorTagId;

	@Column(name = "sector_theme_id")
	private int sectorThemeId;

	@Column(name = "sector_tag_name")
	private String sectorTagName;

	@Column(name = "display_status")
	private Integer displayStatus;

	@Column(name = "updated_at")
	private String updatedAt;

	@Column(name = "created_at")
	private String createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

}

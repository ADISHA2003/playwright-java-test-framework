package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
@Table(name = "sector_themes")
public class SectorTheme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sector_theme_id")
	private int sectorThemeId;

	@Column(name = "sector_theme_name")
	private String sectorThemeName;

	@Column(name = "display_status")
	private int displayStatus;

	@Column(name = "updated_at")
	private String updatedAt;

	@Column(name = "created_at")
	private String createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

}

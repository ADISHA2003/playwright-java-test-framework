package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "investor")
public class InvestorMysql {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "investorid", nullable = false)
	private Integer investorId;

	private Integer targetcompanyid;

	private Integer relativecompanyid;

	private Integer relationshiptypeid;

	private Double percentagestake;

	private Integer staketype;

	private Double capitalCommitted;

	private Long creationTimestamp;

	@Column(name = "updated_at")
	private String updatedAt;

	@Column(name = "created_at")
	private String createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastUpdated")
	private Date lastUpdated;

}

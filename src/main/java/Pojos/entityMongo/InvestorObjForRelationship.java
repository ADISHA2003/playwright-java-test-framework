package Pojos.entityMongo;

import lombok.Data;

import java.util.Date;

@Data
public class InvestorObjForRelationship {
	private Integer targetCompanyId;
	private Integer relativeCompanyId;
	private String relationshipType;
	private Double percentageStake;
	private String stakeType;
	private Double capitalCommitted;
	private Long creationTimestamp;
	private Date updatedAt;
	private Date createdAt;

}

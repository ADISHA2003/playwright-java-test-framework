package Pojos.entityMongo;


import lombok.Data;

import java.util.Date;

@Data
public class InvestorInfo {
	private String investorId;
	private String name;
	private String investorType;
	private String relationshipType;
	private Double percentageStake;
	private String stakeType;
	private Double capitalCommitted;
	private Long creationTimestamp;
	private Date createdAt;
	private Date updatedAt;
}

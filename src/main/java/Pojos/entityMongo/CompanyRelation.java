package Pojos.entityMongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "companyRelation")
public class CompanyRelation {

	@Id
	private String id;
	private Integer investorId;
	private RelationShipBasicInfo primary;
	private RelationShipBasicInfo related;
	private String relationshipType;
	private double percentageStake;
	private String stakeType;
	private double capitalCommitted;
	private double creationTimestamp;
	private Date updatedAt;
	private Date createdAt;

}

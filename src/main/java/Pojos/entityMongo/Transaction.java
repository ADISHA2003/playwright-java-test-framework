package Pojos.entityMongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashMap;

@Data
@Document(collection = "transactions")
public class Transaction {

	@Id
	private String id;
	private Integer transactionId;
	private String transactionType;
	private Integer detailTransactionId;
	private RelationShipBasicInfo targetCompany;
	private Long transactionTime;
	private Boolean transactionProp;
	private Boolean nonMnaDeal;
	private Boolean dealSizeProp;
	private Boolean screenable;
	private Boolean exclusiveDeal;
	private String createdDate;
	private Date createdAt;
	private Date updatedAt;
	private HashMap<String, Object> details;

}

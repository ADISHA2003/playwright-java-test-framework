package Pojos.entityMongo;

import Pojos.entitySql.FundRaisingStage;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "fundRaising")
public class FundRaisingDocument {

    @Id
    private Integer fundRaisingId;

    private Integer companyid;
    private Date launchedDate;
    private Double targetSize;
    private Double amountRaised;
    private Integer status;
    private Double totalAmountRaised;
    private String frDescription;
    private String notes;
    private String entityType;
    private Double targetSizeInr;
    private Double amountRaisedInr;
    private Date updatedAt;
    private Date createdAt;
    private List<FundRaisingStage> stages;




}

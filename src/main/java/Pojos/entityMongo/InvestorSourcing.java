package Pojos.entityMongo;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "investorSourcing")
public class InvestorSourcing {

    @Id
    private String id;  // MongoDB document ID

    private String fundType;
    private String investorType;
    private String fundStatus;
    private Long launchedDate;
    private String assetManager;
    private Contact fundLocation;
    private Double fundSize;
    private String fundRaisingStage;
    private Long fundRaisingStageDate;
    private Double dryPowder;
    private Boolean sebiRegistered;
    private String fundName;
    private String focus;
    private List<String> areaOfInterest;
    private String geographicalPreference;
    private String investmentType;
    private Double investmentSize;
    private Integer numberOfInvestmentsAndDeals;//noOfDealsParticipated
    private Integer numberOfExits;
    private List<Company> companiesInvestment;
    private Long dealDate;
    private Double dealAmount;
    private String roundOfInvestment;
    private Double totalInvestment;
    private List<String> coInvestors;
    private Contact incubatorAndInvestorLocation;//investorLocation
    private String incubatorName;
    private Integer noOfCurrentIncubatees;
    private Integer noOfPriorIncubatees;
    private Double assetUnderManagement;
    private String investorName;
    private String angelName;
    private String dealType;
    private String investorsScreeningType;
    private Integer portfolioCompaniesCurrent;
    private Double amountRaised;
}
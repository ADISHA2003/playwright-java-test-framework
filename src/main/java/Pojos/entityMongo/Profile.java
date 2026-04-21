package Pojos.entityMongo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Profile {

    private String fundStatus;
    private double fundSize;
    private String companyStatus;
    private String directoryFeature;
    private double minimum;
    private double maximum;
    private String investmentStage;
    private String dealTypes;
    private String description;
    private double dealValue;
    private int stakeValue;
    private int fundingReceived;
    private String motherChildFund;
    private boolean seedIncubation;
    private boolean ventureCapital;
    private boolean privateEquity;
    private boolean investorEquity;
    private String indiaCoverage;
    private List<String> fundingStatus;
    private String investorType;
    private List<String> fundType;
    private String vccedgeIc;
    private String mcaStatus;
    private List<String> investmentType;
    private String domicile;
    private String denomination;
    private String primaryExchange;
    private Date incorporationDate;
    private String fundRegistrationId;
    private Date financialAdded;
    private Date financialValidated;
    private boolean channelPartnerFlag;
    private String fundInvestorType;
    private String valuationClass;
    private int yearOfValuation;
    private boolean digitalNativeCompany;
    private String familyOfficeType;

}

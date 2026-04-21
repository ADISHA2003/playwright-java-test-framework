package Pojos.entitySql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "company")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyMysql {
   
    @Column(name = "sc_code")
    private String scCode;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "companyid", nullable = false)
    private Integer companyId;

    @Column(name = "companyname", nullable = false, length = 255)
    private String companyName;

    @Column(name = "previous_name", nullable = false, length = 255)
    private String previousName;

    @Column(name = "dba", nullable = false, length = 128)
    private String dba;

    @Column(name = "yearfounded")
    private Integer yearFounded;

    @Column(name = "numberofemployees")
    private Integer numberOfEmployees;

    @Column(name = "companytypeid", nullable = false)
    private Integer companyTypeId;

    @Column(name = "launchdate")
    private String launchDate;

    @Column(name = "fundstatus")
    private Integer fundStatus;

    @Column(name = "fundsize")
    private Double fundSize;

    @Column(name = "companystatusid")
    private Integer companyStatusId;

    @Column(name = "registered_address_flag", nullable = false)
    private int registeredAddressFlag;

    @Column(name = "primaryaddressline1", columnDefinition = "TEXT")
    private String primaryAddressLine1;

    @Column(name = "primaryaddressline2", length = 100)
    private String primaryAddressLine2;

    @Column(name = "primaryaddressline3", length = 100)
    private String primaryAddressLine3;

    @Column(name = "countryid")
    private Integer countryId;

    @Column(name = "stateid")
    private Integer stateId;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "tier", nullable = false)
    private int tier;

    @Column(name = "pincode", length = 50)
    private String pincode;

    @Column(name = "phonenumber1", length = 50)
    private String phoneNumber1;

    @Column(name = "phonenumber2", length = 50)
    private String phoneNumber2;

    @Column(name = "faxnumber", length = 50)
    private String faxNumber;

    @Column(name = "emailid", columnDefinition = "TEXT")
    private String emailId;

    @Column(name = "businessdescription", columnDefinition = "LONGTEXT")
    private String businessDescription;

    @Column(name = "directoryfeature")
    private Integer directoryFeature;

    @Column(name = "industrytype")
    private Integer industryType;

    @Column(name = "industry_group_id")
    private Integer industryGroupId;

    @Column(name = "industry_id")
    private Integer industryId;

    @Column(name = "sub_industry_id")
    private Integer subIndustryId;

    @Column(name = "website", columnDefinition = "TEXT")
    private String website;

    @Column(name = "district", columnDefinition = "TEXT")
    private String district;

    @Column(name = "closing_date")
    private String closingDate;

    @Column(name = "sponsor", length = 64)
    private String sponsor;

    @Column(name = "minimum")
    private Double minimum;

    @Column(name = "maximum")
    private Double maximum;

    @Column(name = "stage_investment")
    private Integer stageInvestment;

    @Column(name = "areaofintrest", length = 128)
    private String areaOfInterest;

    @Column(name = "global")
    private Integer global;

    @Column(name = "continent")
    private Integer continent;

    @Column(name = "subcontinent")
    private Integer subcontinent;

    @Column(name = "specialization", length = 64)
    private String specialization;

    @Column(name = "deal_types", length = 12)
    private String dealTypes;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "financial_advisor", length = 128)
    private String financialAdvisor;

    @Column(name = "legal_advisor", length = 128)
    private String legalAdvisor;

    @Column(name = "deal_value")
    private Double dealValue;

    @Column(name = "stake_value")
    private Integer stakeValue;

    @Column(name = "source", length = 128)
    private String source;

    @Column(name = "funding_recieved")
    private Integer fundingReceived;

    @Column(name = "market_news", length = 16, nullable = false)
    private String marketNews;

    @Column(name = "created", nullable = false)
    private int created;

    @Column(name = "updated", nullable = false)
    private int updated;

    @Column(name = "mother_child_fund", length = 16, nullable = false)
    private String motherChildFund;

    @Column(name = "seed_incubation", length = 4, nullable = false)
    private String seedIncubation;

    @Column(name = "venture_capital", length = 4, nullable = false)
    private String ventureCapital;

    @Column(name = "private_equity", length = 4, nullable = false)
    private String privateEquity;

    @Column(name = "investor_equity", length = 4, nullable = false)
    private String investorEquity;

    @Column(name = "corporate_identity_number", length = 30)
    private String corporateIdentityNumber;

    @Column(name = "india_coverage", nullable = false)
    private int indiaCoverage;

    @Column(name = "funding_status", length = 50)
    private String fundingStatus;

    @Column(name = "investor_type", nullable = false)
    private int investorType;

    @Column(name = "fund_type", length = 50, nullable = false)
    private String fundType;

    @Column(name = "vccedge_ic", nullable = false)
    private int vccedgeIc;

    @Column(name = "MCA_status", length = 20, nullable = false)
    private String mcaStatus;

    @Column(name = "investment_type", length = 20)
    private String investmentType;

    @Column(name = "domicile", length = 30)
    private String domicile;

    @Column(name = "denomination", length = 30)
    private String denomination;

    @Column(name = "sector_tags", length = 128, nullable = false)
    private String sectorTags;

    @Column(name = "mobile_number", length = 155, nullable = false)
    private String mobileNumber;

    @Column(name = "company_logo", length = 255, nullable = false)
    private String companyLogo;

    @Column(name = "primary_exchange", nullable = false)
    private int primaryExchange;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "short_description", columnDefinition = "LONGTEXT", nullable = false)
    private String shortDescription;

    @Column(name = "sub_sector_tags", length = 155, nullable = false)
    private String subSectorTags;

    @Column(name = "sector_theme", length = 155, nullable = false)
    private String sectorTheme;

    @Column(name = "business_model", length = 55)
    private String businessModel;

    @Column(name = "category_coverage", length = 50, nullable = false)
    private String categoryCoverage;

    @Column(name = "logo_height", nullable = false)
    private int logoHeight;

    @Column(name = "logo_width", nullable = false)
    private int logoWidth;

    @Column(name = "mcache_company_id", nullable = false)
    private int mcacheCompanyId;

    @Column(name = "incorporationdate", nullable = false)
    private String incorporationDate;

    @Column(name = "fund_registration_id", length = 255, nullable = false)
    private String fundRegistrationId;

    @Column(name = "financial_added", nullable = false)
    private String financialAdded;

    @Column(name = "financial_validated", nullable = false)
    private String financialValidated;

    @Column(name = "channel_partner_flag", nullable = false)
    private boolean channelPartnerFlag;

    @Column(name = "fund_investor_type")
    private Integer fundInvestorType;

    @Column(name = "valuation_class", nullable = false)
    private int valuationClass;

    @Column(name = "year_of_valuation", nullable = false)
    private int yearOfValuation;

    @Column(name = "digital_native_company", nullable = false)
    private boolean digitalNativeCompany;

    @Column(name = "family_office_type", length = 55)
    private String familyOfficeType;

    @Column(name = "listing_date")
    private String listingDate;

    @Column(name = "updated_at", nullable = false)
    private String updatedAt;

    @Column(name = "created_at", nullable = false)
    private String createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;
}

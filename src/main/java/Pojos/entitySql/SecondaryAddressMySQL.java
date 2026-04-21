package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "company_secondary_adrress")
public class SecondaryAddressMySQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "companyid")
    private int companyId;

    @Column(name = "office_type")
    private String officeType;

    @Column(name = "primaryaddress1")
    private String primaryAddress1;

    @Column(name = "primaryaddress2")
    private String primaryAddress2;

    @Column(name = "primaryaddress3")
    private String primaryAddress3;

    @Column(name = "countryid")
    private int countryId;

    @Column(name = "stateid")
    private int stateId;

    @Column(name = "city")
    private String city;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "website")
    private String website;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "headquarter_address_flag")
    private int headquarterAddressFlag;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "updated_at", nullable = false)
    private String updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

    // getters and setters
}
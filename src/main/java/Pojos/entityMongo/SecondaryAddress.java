package Pojos.entityMongo;

import lombok.Data;

@Data
public class SecondaryAddress {

    private int id;
    private int companyId;
    private String officeType;
    private String primaryAddress1;
    private String primaryAddress2;
    private String primaryAddress3;
    private int countryId;
    private int stateId;
    private String city;
    private String pincode;
    private String email;
    private String phoneNumber;
    private String website;
    private String mobileNumber;
    private double latitude;
    private double longitude;
    private int headquarterAddressFlag;
    private String createdAt;
    private String updatedAt;
}
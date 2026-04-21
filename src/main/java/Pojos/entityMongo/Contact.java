package Pojos.entityMongo;

import lombok.Data;

import java.util.List;

@Data
public class Contact {
    private List<String> address;
    private List<SecondaryAddress> secondaryAddresses;
    private String pinCode;
    private String mobileNumber;
    private String phoneNumber1;
    private String phoneNumber2;
    private String faxNumber;
    private String emailId;
    private boolean registeredAddressFlag;
    private String country;
    private String state;
    private String city;
    private String district;
    private String mobile;
    private double latitude;
    private double longitude;
}

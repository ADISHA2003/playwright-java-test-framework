package Pojos.CompanyScreenerDataTablePojoFiles.CompanyTab;

import java.util.List;

import Pojos.entityMongo.SecondaryAddress;

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
    private double latitude;
    private double longitude;

    // Getters and setters
    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public List<SecondaryAddress> getSecondaryAddresses() {
        return secondaryAddresses;
    }

    public void setSecondaryAddresses(List<SecondaryAddress> secondaryAddresses) {
        this.secondaryAddresses = secondaryAddresses;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public boolean isRegisteredAddressFlag() {
        return registeredAddressFlag;
    }

    public void setRegisteredAddressFlag(boolean registeredAddressFlag) {
        this.registeredAddressFlag = registeredAddressFlag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}

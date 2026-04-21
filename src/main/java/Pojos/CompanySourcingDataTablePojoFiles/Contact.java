
package Pojos.CompanySourcingDataTablePojoFiles;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "address",
    "secondaryAddresses",
    "pinCode",
    "mobileNumber",
    "phoneNumber1",
    "phoneNumber2",
    "faxNumber",
    "emailId",
    "registeredAddressFlag",
    "country",
    "city",
    "district",
    "latitude",
    "longitude",
    "state"
})
@Generated("jsonschema2pojo")
public class Contact {

    @JsonProperty("address")
    private List<String> address;
    @JsonProperty("secondaryAddresses")
    private List<Object> secondaryAddresses;
    @JsonProperty("pinCode")
    private String pinCode;
    @JsonProperty("mobileNumber")
    private String mobileNumber;
    @JsonProperty("phoneNumber1")
    private String phoneNumber1;
    @JsonProperty("phoneNumber2")
    private String phoneNumber2;
    @JsonProperty("faxNumber")
    private String faxNumber;
    @JsonProperty("emailId")
    private String emailId;
    @JsonProperty("registeredAddressFlag")
    private Boolean registeredAddressFlag;
    @JsonProperty("country")
    private String country;
    @JsonProperty("city")
    private String city;
    @JsonProperty("district")
    private String district;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("state")
    private String state;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("address")
    public List<String> getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(List<String> address) {
        this.address = address;
    }

    @JsonProperty("secondaryAddresses")
    public List<Object> getSecondaryAddresses() {
        return secondaryAddresses;
    }

    @JsonProperty("secondaryAddresses")
    public void setSecondaryAddresses(List<Object> secondaryAddresses) {
        this.secondaryAddresses = secondaryAddresses;
    }

    @JsonProperty("pinCode")
    public String getPinCode() {
        return pinCode;
    }

    @JsonProperty("pinCode")
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @JsonProperty("mobileNumber")
    public String getMobileNumber() {
        return mobileNumber;
    }

    @JsonProperty("mobileNumber")
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @JsonProperty("phoneNumber1")
    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    @JsonProperty("phoneNumber1")
    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    @JsonProperty("phoneNumber2")
    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    @JsonProperty("phoneNumber2")
    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    @JsonProperty("faxNumber")
    public String getFaxNumber() {
        return faxNumber;
    }

    @JsonProperty("faxNumber")
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    @JsonProperty("emailId")
    public String getEmailId() {
        return emailId;
    }

    @JsonProperty("emailId")
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @JsonProperty("registeredAddressFlag")
    public Boolean getRegisteredAddressFlag() {
        return registeredAddressFlag;
    }

    @JsonProperty("registeredAddressFlag")
    public void setRegisteredAddressFlag(Boolean registeredAddressFlag) {
        this.registeredAddressFlag = registeredAddressFlag;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("district")
    public String getDistrict() {
        return district;
    }

    @JsonProperty("district")
    public void setDistrict(String district) {
        this.district = district;
    }

    @JsonProperty("latitude")
    public Double getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("longitude")
    public Double getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

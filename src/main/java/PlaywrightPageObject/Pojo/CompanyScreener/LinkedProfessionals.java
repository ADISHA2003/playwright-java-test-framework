package PlaywrightPageObject.Pojo.CompanyScreener;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkedProfessionals {
    @JsonProperty("Professionals Name")
    private String professionalsName;

    @JsonProperty("Phone")
    private String phone;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Company Name")
    private String companyName;

    @JsonProperty("Designation")
    private String designation;

    @JsonProperty("Contact")
    private String contact;

    public LinkedProfessionals() {}

    // Getters and setters
    public String getProfessionalsName() { return professionalsName; }
    public void setProfessionalsName(String professionalsName) { this.professionalsName = professionalsName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}

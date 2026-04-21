package Pojos.entityMongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "fund")
public class Fund {
    @Id
    private String id;
    private Integer companyId;
    private String website;
    private String cin;
    private String name;
    private String previousName;
    private String dba;
    private String shortDescription;
    private String businessDescription;
    private int yearFounded;
    private Date launchDate;
    private String companyLogo;
    private int logoHeight;
    private int logoWidth;
    private int mcacheCompanyId;
    private Date listingDate;
    private String sponsor;
    private Date closingDate;
    private Date updatedAt;
    private Date createdAt;
    private Profile profile;
    private OldTaxonomy oldTaxonomy;
    private List<Taxonomy> taxonomy;
    private Contact contacts;
}

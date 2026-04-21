package Pojos.entityMongo;

import lombok.Data;

import java.util.List;

@Data
public class OldTaxonomy {
    private String industryType;
    private String industryGroup;
    private String industry;
    private String subIndustry;
    private List<String> areaOfInterest;
    private List<String> sectorTags;
    private List<String> subSectorTags;
    private List<String> sectorTheme;
    private List<String> businessModel;
    private String categoryCoverage;
    private String global;
    private String continent;
    private String subcontinent;
    private String specialization;
    private Integer employees;
    private Integer tier;
}

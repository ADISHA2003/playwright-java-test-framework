package utils;

import PlaywrightPageObject.Pojo.CompanyScreener.Companies;
import PlaywrightPageObject.Pojo.CompanyScreener.LinkedDeals;
import PlaywrightPageObject.Pojo.CompanyScreener.LinkedInvestors;
import PlaywrightPageObject.Pojo.CompanyScreener.LinkedProfessionals;

import java.util.*;

public class DataExport {
    private List<Companies> companies = new ArrayList<>();
    private List<LinkedInvestors> linkedInvestors = new ArrayList<>();
    private List<LinkedDeals> linkedDeals = new ArrayList<>();
    private List<LinkedProfessionals> linkedProfessionals = new ArrayList<>();

    public DataExport() {}

    // Getters / setters
    public List<Companies> getCompanies() { return companies; }
    public void setCompanies(List<Companies> companies) { this.companies = companies; }

    public List<LinkedInvestors> getLinkedInvestors() { return linkedInvestors; }
    public void setLinkedInvestors(List<LinkedInvestors> linkedInvestors) { this.linkedInvestors = linkedInvestors; }

    public List<LinkedDeals> getLinkedDeals() { return linkedDeals; }
    public void setLinkedDeals(List<LinkedDeals> linkedDeals) { this.linkedDeals = linkedDeals; }

    public List<LinkedProfessionals> getLinkedProfessionals() { return linkedProfessionals; }
    public void setLinkedProfessionals(List<LinkedProfessionals> linkedProfessionals) { this.linkedProfessionals = linkedProfessionals; }
}

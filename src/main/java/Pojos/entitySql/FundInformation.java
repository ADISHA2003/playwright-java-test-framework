package Pojos.entitySql;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;


@Data
@Entity
@Table(name = "fund_information")
public class FundInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fund_information_id", nullable = false)
    private Integer fundInformationId;

    @Column(name = "fund_manager_id")
    private Integer fundManagerId;

    @Column(name = "fund_id")
    private Integer fundId;

    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "announced_date", length = 100)
    private String announcedDate;

    @Column(name = "stake_value")
    private Double stakeValue;

    @Column(name = "pe_type")
    private Integer peType;

    @Column(name = "targetcompanyid", nullable = false)
    private Integer targetCompanyId;

    @Column(name = "sector_id")
    private Integer sectorId;

    @Column(name = "industry_group_id")
    private Integer industryGroupId;

    @Column(name = "industry_id")
    private Integer industryId;

    @Column(name = "sub_industry_id")
    private Integer subIndustryId;

    @Column(name = "sector_theme", length = 155)
    private String sectorTheme;

    @Column(name = "sector_tag", length = 155)
    private String sectorTag;

    @Column(name = "screenable", nullable = false)
    private Boolean screenable;

    @Column(name = "deal_type", nullable = false)
    private Integer dealType;

    @Column(name = "deal_value", nullable = false)
    private Double dealValue;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
}

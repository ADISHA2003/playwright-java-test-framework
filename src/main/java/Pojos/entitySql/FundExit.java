package Pojos.entitySql;


import jakarta.persistence.*;

@Entity
@Table(name = "fund_exits")
public class FundExit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "asset_manager_id", nullable = false)
    private Integer assetManagerId;

    @Column(name = "fund_id", nullable = false)
    private Integer fundId;

    @Column(name = "transactionid", nullable = false)
    private Integer transactionId;

    @Column(name = "target_company_id", nullable = false)
    private Integer targetCompanyId;

    @Column(name = "sector_id", nullable = false)
    private Integer sectorId;

    @Column(name = "announceddate", nullable = false, length = 50)
    private String announcedDate;

    @Column(name = "transactiontype", nullable = false)
    private Integer transactionType;

    @Column(name = "exit_deal_value")
    private Double exitDealValue;

    // Getters and setters

    // Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Asset Manager ID
    public Integer getAssetManagerId() {
        return assetManagerId;
    }

    public void setAssetManagerId(Integer assetManagerId) {
        this.assetManagerId = assetManagerId;
    }

    // Fund ID
    public Integer getFundId() {
        return fundId;
    }

    public void setFundId(Integer fundId) {
        this.fundId = fundId;
    }

    // Transaction ID
    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    // Target Company ID
    public Integer getTargetCompanyId() {
        return targetCompanyId;
    }

    public void setTargetCompanyId(Integer targetCompanyId) {
        this.targetCompanyId = targetCompanyId;
    }

    // Sector ID
    public Integer getSectorId() {
        return sectorId;
    }

    public void setSectorId(Integer sectorId) {
        this.sectorId = sectorId;
    }

    // Announced Date
    public String getAnnouncedDate() {
        return announcedDate;
    }

    public void setAnnouncedDate(String announcedDate) {
        this.announcedDate = announcedDate;
    }

    // Transaction Type
    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    // Exit Deal Value
    public Double getExitDealValue() {
        return exitDealValue;
    }

    public void setExitDealValue(Double exitDealValue) {
        this.exitDealValue = exitDealValue;
    }
}

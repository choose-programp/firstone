package com.company.crm.workbench.dto;

/**
 * @author conrad
 * @date 2021/07/05
 */
public class ConvertClue {

    private String clueId;
    private String isCreateTran;
    private String amountOfMoney;
    private String tradeName;
    private String expectedClosingDate;
    private String stage;
    private String activityId;
    private String owner;
    private String createBy;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getClueId() {
        return clueId;
    }

    public void setClueId(String clueId) {
        this.clueId = clueId;
    }

    public String getIsCreateTran() {
        return isCreateTran;
    }

    public void setIsCreateTran(String isCreateTran) {
        this.isCreateTran = isCreateTran;
    }

    public String getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(String amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getExpectedClosingDate() {
        return expectedClosingDate;
    }

    public void setExpectedClosingDate(String expectedClosingDate) {
        this.expectedClosingDate = expectedClosingDate;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "ConvertClue{" +
                "clueId='" + clueId + '\'' +
                ", isCreateTran='" + isCreateTran + '\'' +
                ", amountOfMoney='" + amountOfMoney + '\'' +
                ", tradeName='" + tradeName + '\'' +
                ", expectedClosingDate='" + expectedClosingDate + '\'' +
                ", stage='" + stage + '\'' +
                ", activityId='" + activityId + '\'' +
                '}';
    }
}

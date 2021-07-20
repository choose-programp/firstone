package com.company.crm.workbench.dto;

/**
 * @author conrad
 * @date 2021/07/02
 */
public class DownActivity {
    private String owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_activity.name
     *
     * @mbggenerated Sat May 23 15:59:14 CST 2020
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_activity.start_date
     *
     * @mbggenerated Sat May 23 15:59:14 CST 2020
     */
    private String startDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_activity.end_date
     *
     * @mbggenerated Sat May 23 15:59:14 CST 2020
     */
    private String endDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_activity.cost
     *
     * @mbggenerated Sat May 23 15:59:14 CST 2020
     */
    private String cost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_activity.description
     *
     * @mbggenerated Sat May 23 15:59:14 CST 2020
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_activity.create_time
     *
     * @mbggenerated Sat May 23 15:59:14 CST 2020
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_activity.create_by
     *
     * @mbggenerated Sat May 23 15:59:14 CST 2020
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_activity.edit_time
     *
     * @mbggenerated Sat May 23 15:59:14 CST 2020
     */
    private String editTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_activity.edit_by
     *
     * @mbggenerated Sat May 23 15:59:14 CST 2020
     */
    private String editBy;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    @Override
    public String toString() {
        return "DownActivity{" +
                "owner='" + owner + '\'' +
                ", name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", cost='" + cost + '\'' +
                ", description='" + description + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createBy='" + createBy + '\'' +
                ", editTime='" + editTime + '\'' +
                ", editBy='" + editBy + '\'' +
                '}';
    }
}

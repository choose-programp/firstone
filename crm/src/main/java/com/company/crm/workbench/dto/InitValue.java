package com.company.crm.workbench.dto;

import com.company.crm.commons.utils.DateUtils;
import com.company.crm.commons.utils.UUIDUtils;

import java.util.Date;

/**
 * @author conrad
 * @date 2021/07/05
 */
public class InitValue {

    private String id;
    private String createTime;
    private String editBy;
    private String editTime;

    private String defaultString = "";


    public String getId() {
        return UUIDUtils.getUUID();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return DateUtils.formatDateTime(new Date());
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEditBy() {
        return defaultString;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public String getEditTime() {
        return defaultString;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }
}

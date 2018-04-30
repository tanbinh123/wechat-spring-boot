package com.rookiego.www.wechat.domain.internal;

import java.util.Date;

/**
 * <b>类名称:</b>SystemConfig<br/>
 * <b>类注释:</b>系统配置实体类<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>rookie<br/>
 * <b>修改人:</b>rookie<br/>
 * <b>修改时间:</b>2018年04月30日 16时08分30秒<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br/>
 */
public class SystemConfig {
    private String configKey;
    private String configValue;
    private String configScore;
    private Date createTime;
    private Date updateTime;
    private String extendInfo;

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigScore() {
        return configScore;
    }

    public void setConfigScore(String configScore) {
        this.configScore = configScore;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }
}

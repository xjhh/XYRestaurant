package com.example.xy_restaurant.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-22
 */
@TableName("sys_log")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;
    @TableField("log_user_id")
    private Integer logUserId;
    @TableField("log_operate")
    private String logOperate;
    @TableField("log_method_function")
    private String logMethodFunction;
    @TableField("log_parameter")
    private String logParameter;
    @TableField("log_terminal")
    private String logTerminal;
    @TableField("log_time")
    private Date logTime;


    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getLogUserId() {
        return logUserId;
    }

    public void setLogUserId(Integer logUserId) {
        this.logUserId = logUserId;
    }

    public String getLogOperate() {
        return logOperate;
    }

    public void setLogOperate(String logOperate) {
        this.logOperate = logOperate;
    }

    public String getLogMethodFunction() {
        return logMethodFunction;
    }

    public void setLogMethodFunction(String logMethodFunction) {
        this.logMethodFunction = logMethodFunction;
    }

    public String getLogParameter() {
        return logParameter;
    }

    public void setLogParameter(String logParameter) {
        this.logParameter = logParameter;
    }

    public String getLogTerminal() {
        return logTerminal;
    }

    public void setLogTerminal(String logTerminal) {
        this.logTerminal = logTerminal;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.logId;
    }

    @Override
    public String toString() {
        return "SysLog{" +
        ", logId=" + logId +
        ", logUserId=" + logUserId +
        ", logOperate=" + logOperate +
        ", logMethodFunction=" + logMethodFunction +
        ", logParameter=" + logParameter +
        ", logTerminal=" + logTerminal +
        ", logTime=" + logTime +
        "}";
    }
}

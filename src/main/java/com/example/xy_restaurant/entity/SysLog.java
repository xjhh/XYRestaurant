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

    //日志id
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    //操作用户id
    @TableField("log_user_id")
    private Integer logUserId;

    //操作描述
    @TableField("log_operate")
    private String logOperate;

    //方法名
    @TableField("log_method_function")
    private String logMethodFunction;

    //参数
    @TableField("log_parameter")
    private String logParameter;

    //终端
    @TableField("log_terminal")
    private String logTerminal;

    //操作状态  0成功   1失败
    @TableField("log_status")
    private Integer logStatus;

    //错误日志
    @TableField("log_error_msg")
    private String logErrorMsg;

    @TableField("log_time")
    private String logTime;

    @TableField(exist = false)
    private String logStatuName;

    public Integer getLogStatus() {
        return logStatus;
    }

    public void setLogStatus(Integer logStatus) {
        this.logStatus = logStatus;
        if(logStatus == 0){
            logStatuName = "成功";
        }else{
            logStatuName = "失败";
        }
    }

    public String getLogErrorMsg() {
        return logErrorMsg;
    }

    public void setLogErrorMsg(String logErrorMsg) {
        this.logErrorMsg = logErrorMsg;
    }

    public String getLogStatuName() {
        return logStatuName;
    }

    public void setLogStatuName(String logStatuName) {
        this.logStatuName = logStatuName;
    }

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

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
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

package com.example.xy_restaurant.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-04
 */
public class Manager extends Model<Manager> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "manager_id", type = IdType.AUTO)
    private Integer managerId;
    /**
     * 用户名
     */
    @TableField("manager_username")
    private String managerUsername;
    /**
     * 密码
     */
    @TableField("manager_password")
    private String managerPassword;
    /**
     * 权限
     */
    @TableField("manager_power")
    private Integer managerPower;


    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public Integer getManagerPower() {
        return managerPower;
    }

    public void setManagerPower(Integer managerPower) {
        this.managerPower = managerPower;
    }

    @Override
    protected Serializable pkVal() {
        return this.managerId;
    }

    @Override
    public String toString() {
        return "Manager{" +
        ", managerId=" + managerId +
        ", managerUsername=" + managerUsername +
        ", managerPassword=" + managerPassword +
        ", managerPower=" + managerPower +
        "}";
    }
}

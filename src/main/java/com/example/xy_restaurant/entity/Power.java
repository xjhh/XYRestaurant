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
public class Power extends Model<Power> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "power_id", type = IdType.AUTO)
    private Integer powerId;
    @TableField("power_depict")
    private String powerDepict;


    public Integer getPowerId() {
        return powerId;
    }

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }

    public String getPowerDepict() {
        return powerDepict;
    }

    public void setPowerDepict(String powerDepict) {
        this.powerDepict = powerDepict;
    }

    @Override
    protected Serializable pkVal() {
        return this.powerId;
    }

    @Override
    public String toString() {
        return "Power{" +
        ", powerId=" + powerId +
        ", powerDepict=" + powerDepict +
        "}";
    }
}

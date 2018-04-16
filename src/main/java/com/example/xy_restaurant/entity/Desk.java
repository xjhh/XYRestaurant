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
public class Desk extends Model<Desk> {

    private static final long serialVersionUID = 1L;

    /**
     * 桌子id
     */
    @TableId(value = "desk_id", type = IdType.AUTO)
    private Integer deskId;
    /**
     * 桌子_类型
     */
    @TableField("desk_type")
    private Integer deskType;
    /**
     * 座位号
     */
    @TableField("desk_number")
    private Integer deskNumber;
    /**
     * 桌子状态
     */
    @TableField("desk_state")
    private Integer deskState;


    public Integer getDeskId() {
        return deskId;
    }

    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }

    public Integer getDeskType() {
        return deskType;
    }

    public void setDeskType(Integer deskType) {
        this.deskType = deskType;
    }

    public Integer getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(Integer deskNumber) {
        this.deskNumber = deskNumber;
    }

    public Integer getDeskState() {
        return deskState;
    }

    public void setDeskState(Integer deskState) {
        this.deskState = deskState;
    }

    @Override
    protected Serializable pkVal() {
        return this.deskId;
    }

    @Override
    public String toString() {
        return "Desk{" +
        ", deskId=" + deskId +
        ", deskType=" + deskType +
        ", deskNumber=" + deskNumber +
        ", deskState=" + deskState +
        "}";
    }
}

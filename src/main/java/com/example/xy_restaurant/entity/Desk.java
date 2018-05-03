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
 * @since 2018-04-22
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
    private String deskNumber;
    /**
     * 桌子状态
     */
    @TableField("desk_state")
    private Integer deskState;

    @TableField(exist = false)
    private String url;

    @TableField(exist = false)
    private String color;

    @TableField(exist = false)
    private int addSize;

    public int getAddSize() {
        return addSize;
    }

    public void setAddSize(int addSize) {
        this.addSize = addSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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
        setUrl("/img/desk/desk_"+deskType+".png");
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(String deskNumber) {
        this.deskNumber = deskNumber;
    }

    public Integer getDeskState() {
        return deskState;
    }

    public void setDeskState(Integer deskState) {
        this.deskState = deskState;
        if(deskState == 0){
            color = "green";
        }else{
            color = "red";
        }
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
        ", deskUrl" + url +
        ", color" + color+
        "}";
    }
}

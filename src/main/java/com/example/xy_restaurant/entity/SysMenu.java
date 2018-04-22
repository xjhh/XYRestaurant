package com.example.xy_restaurant.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-22
 */
@TableName("sys_menu")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;
    @TableField("menu_series")
    private Integer menuSeries;
    @TableField("menu_power")
    private Integer menuPower;
    @TableField("menu_name")
    private String menuName;
    @TableField("menu_uri")
    private String menuUri;
    @TableField("menu_ico")
    private String menuIco;
    @TableField("type")
    private String type;

    @TableField(exist = false)
    private List<SysMenu> menuChilds = new ArrayList<>();

    public List<SysMenu> getMenuChilds() {
        return menuChilds;
    }

    public void addChild(SysMenu sysMenu) {
        menuChilds.add(sysMenu);
    }

    public void setMenuChilds(List<SysMenu> menuChilds) {
        this.menuChilds = menuChilds;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getMenuSeries() {
        return menuSeries;
    }

    public void setMenuSeries(Integer menuSeries) {
        this.menuSeries = menuSeries;
    }

    public Integer getMenuPower() {
        return menuPower;
    }

    public void setMenuPower(Integer menuPower) {
        this.menuPower = menuPower;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUri() {
        return menuUri;
    }

    public void setMenuUri(String menuUri) {
        this.menuUri = menuUri;
    }

    public String getMenuIco() {
        return menuIco;
    }

    public void setMenuIco(String menuIco) {
        this.menuIco = menuIco;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
        ", menuId=" + menuId +
        ", menuSeries=" + menuSeries +
        ", menuPower=" + menuPower +
        ", menuName=" + menuName +
        ", menuUri=" + menuUri +
        ", menuIco=" + menuIco +
        ", type=" + type +
        "}";
    }
}

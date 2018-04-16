package com.example.xy_restaurant.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
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
public class Purchase extends Model<Purchase> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品采购记录id
     */
    @TableId(value = "purchase_id", type = IdType.AUTO)
    private Integer purchaseId;
    /**
     * 商品采购的名称
     */
    @TableField("purchase_name")
    private String purchaseName;
    /**
     * 商品采购的单价
     */
    @TableField("purchase_price")
    private Double purchasePrice;
    /**
     * 商品采购的数量
     */
    @TableField("purchase_number")
    private Integer purchaseNumber;
    /**
     * 商品采购的时间
     */
    @TableField("purchase_time")
    private Date purchaseTime;
    /**
     * 商品采购的总价
     */
    @TableField("purchase_total")
    private Double purchaseTotal;


    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(Integer purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Double getPurchaseTotal() {
        return purchaseTotal;
    }

    public void setPurchaseTotal(Double purchaseTotal) {
        this.purchaseTotal = purchaseTotal;
    }

    @Override
    protected Serializable pkVal() {
        return this.purchaseId;
    }

    @Override
    public String toString() {
        return "Purchase{" +
        ", purchaseId=" + purchaseId +
        ", purchaseName=" + purchaseName +
        ", purchasePrice=" + purchasePrice +
        ", purchaseNumber=" + purchaseNumber +
        ", purchaseTime=" + purchaseTime +
        ", purchaseTotal=" + purchaseTotal +
        "}";
    }
}

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
 * @since 2018-04-22
 */
public class Consume extends Model<Consume> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员消费记录id
     */
    @TableId(value = "consume_id", type = IdType.AUTO)
    private Integer consumeId;
    /**
     * 会员卡号
     */
    @TableField("member_card")
    private String memberCard;
    /**
     * 桌台编号
     */
    @TableField("desk_id")
    private Integer deskId;
    /**
     * 商品id
     */
    @TableField("good_id")
    private String goodId;
    /**
     * 会员消费时间
     */
    @TableField("consume_time")
    private String consumeTime;
    /**
     * 消费状态
     */
    @TableField("consume_status")
    private Integer consumeStatus;
    /**
     * 会员消费总价
     */
    @TableField("consume_total")
    private Double consumeTotal;

    /**
     *会员名称
     */
    @TableField(exist = false)
    private String memberName;

    /**
     * 桌台编号
     */
    @TableField(exist = false)
    private String deskNumber;

    public String getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(String deskNumber) {
        this.deskNumber = deskNumber;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getConsumeStatus() {
        return consumeStatus;
    }

    public void setConsumeStatus(Integer consumeStatus) {
        this.consumeStatus = consumeStatus;
    }

    public Integer getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(Integer consumeId) {
        this.consumeId = consumeId;
    }

    public String getMemberCard() {
        return memberCard;
    }

    public void setMemberCard(String memberCard) {
        this.memberCard = memberCard;
    }

    public Integer getDeskId() {
        return deskId;
    }

    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(String consumeTime) {
        this.consumeTime = consumeTime;
    }

    public Double getConsumeTotal() {
        return consumeTotal;
    }

    public void setConsumeTotal(Double consumeTotal) {
        this.consumeTotal = consumeTotal;
    }

    @Override
    protected Serializable pkVal() {
        return this.consumeId;
    }

    @Override
    public String toString() {
        return "Consume{" +
        ", consumeId=" + consumeId +
        ", memberCard=" + memberCard +
        ", deskId=" + deskId +
        ", goodId=" + goodId +
                ", consumeTime=" + consumeTime +
                ", consumeTime=" + consumeTime +
        ", consumeTotal=" + consumeTotal +
        "}";
    }
}

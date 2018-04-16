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
    private Integer goodId;
    /**
     * 会员消费时间
     */
    @TableField("consume_time")
    private Date consumeTime;
    /**
     * 会员消费总价
     */
    @TableField("consume_total")
    private Double consumeTotal;


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

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Date getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Date consumeTime) {
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
        ", consumeTotal=" + consumeTotal +
        "}";
    }
}

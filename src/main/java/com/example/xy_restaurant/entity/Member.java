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
public class Member extends Model<Member> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员id
     */
    @TableId(value = "member_id", type = IdType.AUTO)
    private Integer memberId;
    /**
     * 会员卡号
     */
    @TableField("member_card")
    private String memberCard;
    /**
     * 会员名字
     */
    @TableField("member_name")
    private String memberName;
    /**
     * 会员密码
     */
    @TableField("member_password")
    private String memberPassword;
    /**
     * 会员手机号码
     */
    @TableField("member_phone")
    private String memberPhone;
    @TableField("member_ingral")
    private Integer memberIngral;


    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberCard() {
        return memberCard;
    }

    public void setMemberCard(String memberCard) {
        this.memberCard = memberCard;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public Integer getMemberIngral() {
        return memberIngral;
    }

    public void setMemberIngral(Integer memberIngral) {
        this.memberIngral = memberIngral;
    }

    @Override
    protected Serializable pkVal() {
        return this.memberId;
    }

    @Override
    public String toString() {
        return "Member{" +
        ", memberId=" + memberId +
        ", memberCard=" + memberCard +
        ", memberName=" + memberName +
        ", memberPassword=" + memberPassword +
        ", memberPhone=" + memberPhone +
        ", memberIngral=" + memberIngral +
        "}";
    }
}

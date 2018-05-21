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
public class Income extends Model<Income> {

    private static final long serialVersionUID = 1L;

    /**
     * 收益编号
     */
    @TableId(value = "income_id", type = IdType.AUTO)
    private Integer incomeId;
    /**
     * 纯收入
     */
    @TableField("income_net")
    private Double incomeNet;
    /**
     * 支出
     */
    @TableField("income_expend")
    private Double incomeExpend;

    /**
     * 收益金额
     */
    @TableField("income_total")
    private Double incomeTotal;
    /**
     * 收益时间
     */
    @TableField("income_data")
    private String incomeData;


    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public Double getIncomeTotal() {
        return incomeTotal;
    }

    public void setIncomeTotal(Double incomeTotal) {
        this.incomeTotal = incomeTotal;
    }

    public String getIncomeData() {
        return incomeData;
    }

    public void setIncomeData(String incomeData) {
        this.incomeData = incomeData;
    }

    public Double getIncomeNet() {
        return incomeNet;
    }

    public void setIncomeNet(Double incomeNet) {
        this.incomeNet = incomeNet;
    }

    public Double getIncomeExpend() {
        return incomeExpend;
    }

    public void setIncomeExpend(Double incomeExpend) {
        this.incomeExpend = incomeExpend;
    }

    @Override
    protected Serializable pkVal() {
        return this.incomeId;
    }

    @Override
    public String toString() {
        return "Income{" +
                "incomeId=" + incomeId +
                ", incomeNet=" + incomeNet +
                ", incomeExpend=" + incomeExpend +
                ", incomeTotal=" + incomeTotal +
                ", incomeData='" + incomeData + '\'' +
                '}';
    }
}

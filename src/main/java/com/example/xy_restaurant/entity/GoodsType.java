package com.example.xy_restaurant.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-22
 */
@TableName("goods_type")
public class GoodsType extends Model<GoodsType> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品类型编号
     */
    @TableId(value = "type_id", type = IdType.AUTO)
    private Integer typeId;
    /**
     * 商品类型名称
     */
    @TableField("type_name")
    private String typeName;


    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    protected Serializable pkVal() {
        return this.typeId;
    }

    @Override
    public String toString() {
        return "GoodsType{" +
        ", typeId=" + typeId +
        ", typeName=" + typeName +
        "}";
    }
}

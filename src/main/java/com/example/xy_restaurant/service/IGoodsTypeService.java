package com.example.xy_restaurant.service;

import com.example.xy_restaurant.entity.GoodsType;
import com.baomidou.mybatisplus.service.IService;
import com.example.xy_restaurant.entity.Tree;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-04
 */
public interface IGoodsTypeService extends IService<GoodsType> {

    Tree<GoodsType> getTree();

}

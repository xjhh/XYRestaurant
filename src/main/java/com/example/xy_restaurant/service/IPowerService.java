package com.example.xy_restaurant.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.example.xy_restaurant.entity.PageDO;
import com.example.xy_restaurant.entity.Power;
import com.baomidou.mybatisplus.service.IService;
import com.example.xy_restaurant.entity.Tree;
import com.example.xy_restaurant.util.Query;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-04
 */
public interface IPowerService extends IService<Power> {

    @Override
    List<Power> selectList(Wrapper<Power> wrapper);

//    <Power> pageSelectList(Query query);


    @Override
    Power selectById(Serializable serializable);

    @Override
    boolean insert(Power power);

    @Override
    boolean updateById(Power power);

    @Override
    boolean deleteById(Serializable serializable);

    @Override
    boolean deleteBatchIds(Collection<? extends Serializable> collection);

    Tree<Power> getTree();
}

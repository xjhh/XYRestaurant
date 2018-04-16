package com.example.xy_restaurant.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.example.xy_restaurant.entity.SysMenu;
import com.baomidou.mybatisplus.service.IService;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-04
 */
public interface ISysMenuService extends IService<SysMenu> {

    List<SysMenu> selectAll();

    @Override
    List<SysMenu> selectList(Wrapper<SysMenu> wrapper);

    @Override
    boolean updateById(SysMenu sysMenu);

    @Override
    SysMenu selectById(Serializable serializable);

    @Override
    boolean insert(SysMenu sysMenu);

    @Override
    boolean deleteById(Serializable serializable);
}

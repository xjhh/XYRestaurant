package com.example.xy_restaurant.mapper;

import com.example.xy_restaurant.entity.Manager;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiangjie
 * @since 2018-03-21
 */
public interface ManagerMapper extends BaseMapper<Manager> {

    Manager selectUserByName(String name);


    Integer selectUserByAll(Manager manager);
}

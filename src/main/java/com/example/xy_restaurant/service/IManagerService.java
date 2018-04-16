package com.example.xy_restaurant.service;

import com.example.xy_restaurant.entity.Manager;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiangjie
 * @since 2018-03-21
 */
public interface IManagerService extends IService<Manager> {

    Manager selectUserByName(String name);

    Integer selectUserByAll(Manager manager);
}

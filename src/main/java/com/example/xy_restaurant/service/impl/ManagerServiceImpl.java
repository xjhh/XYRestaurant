package com.example.xy_restaurant.service.impl;

import com.example.xy_restaurant.entity.Manager;
import com.example.xy_restaurant.mapper.ManagerMapper;
import com.example.xy_restaurant.service.IManagerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiangjie
 * @since 2018-03-21
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements IManagerService {
    Logger logger = Logger.getLogger("XyRestaurant");
    @Override
    public Manager selectUserByName(String name) {
        return baseMapper.selectUserByName(name);
    }

    @Override
    public Integer selectUserByAll(Manager manager) {
        Integer id = baseMapper.selectUserByAll(manager);
        if( id == null){

            return 0;
        }
        logger.info("useID:   "+id);
        return id;
    }
//Mapper method 'com.autoyol.mapper.trans.AccountLogMapper.getTotalIncomByMemNoLastest attempted to return null from a method with a primitive return type (int).
}

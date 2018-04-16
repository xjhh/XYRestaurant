package com.example.xy_restaurant.service.impl;

import com.example.xy_restaurant.entity.SysMenu;
import com.example.xy_restaurant.mapper.SysMenuMapper;
import com.example.xy_restaurant.service.ISysMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-04
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public List<SysMenu> selectAll() {
        return baseMapper.selectAll();
    }
}

package com.example.xy_restaurant.service.impl;

import com.example.xy_restaurant.entity.GoodsType;
import com.example.xy_restaurant.entity.Power;
import com.example.xy_restaurant.entity.Tree;
import com.example.xy_restaurant.mapper.GoodsTypeMapper;
import com.example.xy_restaurant.service.IGoodsTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-04
 */
@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType> implements IGoodsTypeService {

    @Override
    public Tree<GoodsType> getTree() {

        Tree<GoodsType> tree = new Tree<>();
        List<Tree<GoodsType>> trees = new ArrayList<Tree<GoodsType>>();
        List<GoodsType> powers = baseMapper.selectList(null);
        for (GoodsType goodsType : powers) {
            Tree<GoodsType> t = new Tree<GoodsType>();
            t.setId(goodsType.getTypeId().toString());
            t.setParentId(-1+"");
            t.setText(goodsType.getTypeName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            t.setState(state);
            trees.add(t);
        }

        tree.setId("-1");
        tree.setParentId("");
        tree.setHasParent(false);
        tree.setChildren(false);
        tree.setChecked(true);
        tree.setChildren(trees);
        tree.setText("全部");
        Map<String, Object> state = new HashMap<>(16);
        state.put("opened", true);
        tree.setState(state);
        return tree;
    }

}

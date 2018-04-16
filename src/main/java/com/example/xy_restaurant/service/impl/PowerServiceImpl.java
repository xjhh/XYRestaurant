package com.example.xy_restaurant.service.impl;

import com.example.xy_restaurant.entity.Power;
import com.example.xy_restaurant.entity.Tree;
import com.example.xy_restaurant.mapper.PowerMapper;
import com.example.xy_restaurant.service.IPowerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.xy_restaurant.util.BuildTree;
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
public class PowerServiceImpl extends ServiceImpl<PowerMapper, Power> implements IPowerService {

    @Override
    public Tree<Power> getTree() {

        Tree<Power> tree = new Tree<>();
        List<Tree<Power>> trees = new ArrayList<Tree<Power>>();
        List<Power> powers = baseMapper.selectList(null);
        for (Power power : powers) {
            Tree<Power> t = new Tree<Power>();
            t.setId(power.getPowerId().toString());
            t.setParentId(-1+"");
            t.setText(power.getPowerDepict());
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

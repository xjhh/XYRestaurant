package com.example.xy_restaurant.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.xy_restaurant.entity.*;
import com.example.xy_restaurant.util.PageUtils;
import com.example.xy_restaurant.util.PinyinUtils;
import com.example.xy_restaurant.util.ResultJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/addConsume")
public class AddConsumeController extends BaseController {

    @Override
    public String enterJsp() {
        insertLog(BaseController.STAUSE_OK, "进入点菜选择桌台界面", "", "");
        return "addConsume/addConsume";
    }

    @Override
    public String enterAddJsp(HttpServletRequest request) {
        return null;
    }

    @Override
    public String enterAddJsp(int id, HttpServletRequest request) {
        return null;
    }

    @Override
    public String enterEditJsp(@PathVariable("id") int id, HttpServletRequest request) {
        return "addConsume/edit";
    }

    @ResponseBody
    @GetMapping("/deskList")
    public PageUtils queryList(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        int status = (params.get("status") == "" || params.get("status") == "-1") ? -1 : Integer.parseInt((String) params.get("status"));
        int type = (params.get("type") == "" || params.get("type") == "-1") ? -1 : Integer.parseInt((String) params.get("type"));
        List<Desk> list = null;
        if (type == -1 && status == -1) {
            list = deskService.selectList(null);
        } else if (type != -1 && status == -1) {
            list = deskService.selectList(new EntityWrapper<Desk>().eq("desk_type", type));
        } else if (type == -1 && status != -1) {
            list = deskService.selectList(new EntityWrapper<Desk>().eq("desk_state", status));
        } else {
            list = deskService.selectList(new EntityWrapper<Desk>().eq("desk_type", type).eq("desk_state", status));
        }
        insertLog(BaseController.STAUSE_OK, "点菜选择桌台界面，获取桌台信息", params.toString(), "");
        System.out.println(list.toString());
        return new PageUtils(list, list.size());
    }

    @Override
    public String delete(int id, HttpSession session) {
        return null;
    }

    @RequestMapping("/buy/{id}")
    public String buy(@PathVariable("id") int id, HttpServletRequest request) {
        request.setAttribute("deskId", id);
        request.setAttribute("deskNumber", deskService.selectById(id).getDeskNumber());
        insertLog(BaseController.STAUSE_OK, "进入点菜选菜界面 ", "deskId : " + id, "");
        return "addConsume/buy";
    }

    @RequestMapping("buy/see/{type}")
    public String see(@PathVariable("type") int type, HttpServletRequest request) {
        request.setAttribute("isNextShow", type);
        insertLog(BaseController.STAUSE_OK, "查看选中菜品列表 ", "type : " + type, "");
        return "addConsume/seeList";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") int id, HttpServletRequest request) {
        Consume consume = consumeService.selectOne(new EntityWrapper<Consume>().eq("consume_status", 0)
                .eq("desk_id", id));
        request.setAttribute("isNextShow", 2);
        request.setAttribute("consume", consume);
        insertLog(BaseController.STAUSE_OK, "修改选中菜品列表 ", "deskId: " + id + "  " + consume.toString(), "");

        return "addConsume/seeList";
    }

    @ResponseBody
    @GetMapping("/seeGoodList")
    public List<Goods> getSeeList(@ModelAttribute QueryParam queryParam, HttpServletRequest request) {
        System.out.println("seeGoodList");
        List<Integer> idList = new ArrayList<>();
        if (null == queryParam.getName() || queryParam.getName() == "") {
            return new ArrayList<>();
        }
        String[] ids = queryParam.getName().split(",");
        for (String id : ids) {
            idList.add(Integer.valueOf(id));
        }
        List<Integer> disIdList = distinctBySetOrder(idList);
        List<Goods> goodsList = goodsService.selectBatchIds(disIdList);
        List<Goods> resultList = new ArrayList<>();
        int sum = 0;
        for (Goods goods : goodsList) {
            goods.setGoodTypeName(goodsTypeService.selectById(goods.getGoodType()).getTypeName());
            goods.setGoodBuySize(disCount(idList, goods.getGoodId()));
            goods.setGoodBuyTotal((float) (goods.getGoodPrice() * goods.getGoodBuySize()));
            sum += goods.getGoodBuyTotal();
            resultList.add(goods);
        }
        request.setAttribute("total", sum);
        insertLog(BaseController.STAUSE_OK, "获取选中菜品列表 ", queryParam.toString(), "");
        return resultList;
    }

    @ResponseBody
    @RequestMapping("/insert")
    public String insert(@RequestParam("memberCard") String memberCard, @RequestParam("deskId") int deskId, @RequestParam("goodId") String goodId) {

        Consume consume = new Consume();
        consume.setMemberCard(memberCard);
        consume.setDeskId(deskId);
        consume.setGoodId(goodId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        consume.setConsumeTime(sdf.format(new Date()));
        consume.setConsumeStatus(0);
        double total = 0;
        for (String s : goodId.split(",")) {
            total += goodsService.selectById(Integer.valueOf(s)).getGoodPrice();
        }
        consume.setConsumeTotal(total);
        if (!consumeService.insert(consume)) {
            insertLog(BaseController.STAUSE_NO, "提交点菜 ", "memberCard: " + memberCard + " deskId: " + deskId + ",goodId:" + goodId, "点菜失败");
            return ResultJson.resultMsg(false, "点菜失败");
        } else {
            Desk desk = deskService.selectById(deskId);
            desk.setDeskState(1);
            if (!deskService.updateById(desk)) {
                insertLog(BaseController.STAUSE_NO, "点菜成功，修改桌台状态 ", desk.toString(), "修改失败");
                return ResultJson.resultMsg(false, "修改失败");
            } else {
                if (!lessGoodsStock(goodId.split(","))) {
                    insertLog(BaseController.STAUSE_NO, "点菜成功，修改商品库存 ", desk.toString(), "修改失败");
                    return ResultJson.resultMsg(false, "修改失败");
                }
                insertLog(BaseController.STAUSE_OK, "点菜成功", "memberCard: " + memberCard + " deskId: " + deskId + ",goodId:" + goodId, "");
                return ResultJson.resultMsg(true, "");
            }
        }

    }

    @ResponseBody
    @GetMapping("/getTotal/{list}")
    public String getTotal(@PathVariable("list") String list) {
        String[] ids = list.split(",");
        int sum = 0;
        for (String id : ids) {
            sum += goodsService.selectById(id).getGoodPrice();
        }
        return "" + sum;
    }

    public boolean lessGoodsStock(String... goodIds) {
        List<Integer> ids = new ArrayList<>();
        for (String goodId : goodIds) {
            ids.add(Integer.valueOf(goodId));
        }
        List<Integer> disIds = distinctBySetOrder(ids);
        List<Goods> goodsList = new ArrayList<>();
        for (Integer id : disIds) {
            Goods goods = goodsService.selectById(id);
            goods.setGoodStock(goods.getGoodStock() - disCount(ids, id));
            goodsList.add(goods);
        }
        return goodsService.updateBatchById(goodsList);
    }

    @ResponseBody
    @RequestMapping("/updateGoodConsume/{id}/{list}")
    public String updateGoodConsume(@PathVariable("id") int id, @PathVariable("list") String list) {
        Consume consume = consumeService.selectById(id);
        consume.setGoodId(list);
        if (!consumeService.updateById(consume)) {
            insertLog(BaseController.STAUSE_NO, "修改选中商品列表 ", "consumeId: " + id + ", goodIds: " + list, "修改失败");
            return ResultJson.resultMsg(false, "修改失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "获取选中菜品列表 ", "consumeId: " + id + ", goodIds: " + list, "");
            return ResultJson.resultMsg(true, "");
        }

    }

    @ResponseBody
    @RequestMapping("/clearing/{id}")
    public String clearing(@PathVariable("id") int id) {
        Consume consume = consumeService.selectById(id);
        consume.setConsumeStatus(1);
        if (!consumeService.updateById(consume)) {
            insertLog(BaseController.STAUSE_NO, "结算失败", "consumeIds : " + id, "");
            return ResultJson.resultMsg(false, "结算失败");
        } else {
            Desk desk = deskService.selectById(consume.getDeskId());
            desk.setDeskState(0);
            deskService.updateById(desk);
            updateIncome(consume.getConsumeTotal());
            insertLog(BaseController.STAUSE_NO, "结算成功", "consumeIds : " + id, "");
            return ResultJson.resultMsg(true, "");
        }

    }


    public int disCount(List<Integer> detList, int a) {
        int count = 0;
        for (Integer i : detList) {
            if (i == a) {
                count++;
            }
        }
        return count;
    }

    public List<Integer> distinctBySetOrder(List<Integer> list) {
        Set<Integer> set = new HashSet<Integer>();
        List<Integer> newList = new ArrayList<Integer>();
        for (Integer t : list) {
            if (set.add(t)) {
                newList.add(t);
            }
        }
        return newList;
    }


}

package com.example.xy_restaurant.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.xy_restaurant.entity.Consume;
import com.example.xy_restaurant.entity.Goods;
import com.example.xy_restaurant.entity.Member;
import com.example.xy_restaurant.entity.QueryParam;
import com.example.xy_restaurant.util.ResultJson;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-04
 */
@Controller
@RequestMapping("/consume")
public class ConsumeController extends BaseController {

    @Override
    public String enterJsp() {
        insertLog(BaseController.STAUSE_OK, "进入消费记录管理", "", "");
        return "consume/consume";
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
    public String enterEditJsp(int id, HttpServletRequest request) {
        return null;
    }

    @Override
    public String delete(int id, HttpSession session) {
        if (!consumeService.deleteById(id)) {
            insertLog(BaseController.STAUSE_NO, "删除消费记录", "consumeID： " + id, "删除失败");
            return ResultJson.resultMsg(false, "删除失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "删除消费记录", "", "删除成功");
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @GetMapping("/list")
    public List queryList(@ModelAttribute QueryParam queryParam) {
        List<Consume> list = consumeService.selectList(new EntityWrapper<Consume>().like("member_card", queryParam.getName()));
        for (int i = 0; i < list.size(); i++) {
            Consume consume = list.get(i);
            System.out.println(consume.getMemberCard());
            System.out.println(memberService.selectOne(new EntityWrapper<Member>().eq("member_card", consume.getMemberCard())));
            consume.setMemberName((!(consume.getMemberCard().equals("0")) ?
                    memberService.selectOne(new EntityWrapper<Member>().eq("member_card", consume.getMemberCard())).getMemberName() : "非会员用户"));
            consume.setDeskNumber(deskService.selectById(consume.getDeskId()).getDeskNumber());
        }
        insertLog(BaseController.STAUSE_OK, "获取消费记录", queryParam.toString(), "");
        return list;
    }

    @RequestMapping("/see/{id}/{isDesk}")
    public String see(@PathVariable("id") int id, @PathVariable("isDesk") boolean isDesk, HttpServletRequest request) {
        Consume consume = null;
        if (!isDesk) {
            consume = consumeService.selectById(id);
            request.setAttribute("isClearing", false);
        } else {
            consume = consumeService.selectOne(new EntityWrapper<Consume>().eq("consume_status", 0)
                    .eq("desk_id", id));
            request.setAttribute("isClearing", true);
        }
        consume.setMemberName((!(consume.getMemberCard().equals("0")) ? memberService.selectOne(new EntityWrapper<Member>().eq("member_card", consume.getMemberCard())).getMemberName() : "非会员用户"));
        consume.setDeskNumber(deskService.selectById(consume.getDeskId()).getDeskNumber());
        request.setAttribute("consume", consume);
        List<Integer> idList = new ArrayList<>();
        String[] ids = consume.getGoodId().split(",");
        for (String s : ids) {
            idList.add(Integer.valueOf(s));
        }
        List<Goods> goodsList = goodsService.selectBatchIds(idList);
        for (int i = 0; i < goodsList.size(); i++) {
            Goods goods = goodsList.get(i);
            goods.setGoodBuySize(disCount(idList, goods.getGoodId()));
            goodsList.set(i, goods);
        }
        request.setAttribute("goods", goodsList);
        if (isDesk) {
            insertLog(BaseController.STAUSE_OK, "结算消费菜单", "consumeId:" + id + ",isdesk:" + isDesk, "");
        } else {
            insertLog(BaseController.STAUSE_OK, "查看消费记录详情", "consumeId:" + id + ",isdesk:" + isDesk, "");
        }
        return "consume/see";
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

    @ResponseBody
    @PostMapping("/batchRemove")
    public String deleteBatchIds(@RequestParam int[] ids) {
        String str = "";
        List<Integer> idList = new ArrayList<>();
        for (int id : ids) {
            idList.add(id);
            str += "  " + id;
        }
        if (!consumeService.deleteBatchIds(idList)) {
            insertLog(BaseController.STAUSE_NO, "批量删除消费记录", str, "删除失败");
            return ResultJson.resultMsg(false, "删除失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "批量删除消费记录", str, "");
            return ResultJson.resultMsg(true, "");
        }
    }
}


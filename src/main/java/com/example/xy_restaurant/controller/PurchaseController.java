package com.example.xy_restaurant.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.xy_restaurant.entity.Manager;
import com.example.xy_restaurant.entity.Member;
import com.example.xy_restaurant.entity.Purchase;
import com.example.xy_restaurant.entity.QueryParam;
import com.example.xy_restaurant.util.ResultJson;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@RequestMapping("/purchase")
public class PurchaseController extends BaseController {

    @Override
    public String enterJsp() {
        insertLog(BaseController.STAUSE_OK, "进入采购管理界面", "", "");
        return "purchase/purchase";
    }

    @Override
    public String enterAddJsp(HttpServletRequest request) {
        insertLog(BaseController.STAUSE_OK, "进入添加采购管理界面", "", "");
        return "purchase/add";
    }

    @Override
    public String enterAddJsp(int id, HttpServletRequest request) {
        return null;
    }

    @Override
    public String enterEditJsp(@PathVariable("id") int id, HttpServletRequest request) {
        request.setAttribute("p", purchaseService.selectById(id));
        insertLog(BaseController.STAUSE_OK, "进入修改采购界面", "id:" + id, "");
        return "purchase/edit";
    }

    @ResponseBody
    @GetMapping("/list")
    public List queryList(@ModelAttribute QueryParam queryParam) {
        System.out.println(queryParam.toString());
        List<Purchase> purchaseList = null;
        int type = queryParam.getType();
        String name = queryParam.getName();
        if (type == -1 && name != "") {
            purchaseList = purchaseService.selectList(new EntityWrapper<Purchase>().like("purchase_addr", name));
        } else if (type == -1 && name == "") {
            purchaseList = purchaseService.selectList(null);
        } else {
            purchaseList = purchaseService.selectList(new EntityWrapper<Purchase>().eq("purchase_user", type).like("purchase_addr", name));
        }
        List<Manager> managerList = managerService.selectList(null);
        for (int i = 0; i < purchaseList.size(); i++) {
            Purchase purchase = purchaseList.get(i);
            purchase.setPurchaseUserName(managerService.selectById(purchase.getPurchaseUser()).getManagerUsername());
            purchaseList.set(i, purchase);
        }
        System.out.println("====>" + purchaseList);
        return purchaseList;
    }

    @ResponseBody
    @RequestMapping("/insert")
    public String insert(@ModelAttribute Purchase purchase) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        purchase.setPurchaseTime(sdf.format(new Date()));
        if (!purchaseService.insert(purchase)) {
            insertLog(BaseController.STAUSE_NO, "添加采购", purchase.toString(), "添加失败");
            return ResultJson.resultMsg(false, "添加失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "添加采购", purchase.toString(), "");
            updateIncome(-purchase.getPurchaseTotal());
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute Purchase purchase, HttpSession session) {
        System.out.println(purchase.toString());
        if (!purchaseService.updateById(purchase)) {
            insertLog(BaseController.STAUSE_NO, "修改采购", purchase.toString(), "修改失败");
            return ResultJson.resultMsg(false, "修改失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "修改采购", purchase.toString(), "");
            return ResultJson.resultMsg(true, "");
        }
    }

    @Override
    public String delete(int id, HttpSession session) {
        if (!purchaseService.deleteById(id)) {
            insertLog(BaseController.STAUSE_NO, "删除采购", "id:" + id, "删除失败");
            return ResultJson.resultMsg(false, "删除失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "删除用户", "id:" + id, "");
            return ResultJson.resultMsg(true, "");
        }
    }
}


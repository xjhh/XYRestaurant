package com.example.xy_restaurant.controller;


import com.example.xy_restaurant.entity.PageDO;
import com.example.xy_restaurant.entity.Power;
import com.example.xy_restaurant.entity.SysMenu;
import com.example.xy_restaurant.entity.Tree;
import com.example.xy_restaurant.service.IPowerService;
import com.example.xy_restaurant.util.Query;
import com.example.xy_restaurant.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-04
 */
@Controller
@RequestMapping("/sys/power")
public class PowerController extends BaseController {

    Logger logger = Logger.getLogger("sys_menu");
    @Autowired
    IPowerService powerService;

    @Override
    public String enterJsp() {
        insertLog(BaseController.STAUSE_OK, "进入用户权限管理界面", "", "");
        return "power/power";
    }

    @Override
    public String enterAddJsp(HttpServletRequest request) {
        insertLog(BaseController.STAUSE_OK, "进入添加用户权限界面", "", "");
        return "/power/add";
    }

    @Override
    public String enterAddJsp(int id, HttpServletRequest request) {
        return null;
    }

    @Override
    public String enterEditJsp(@PathVariable("id") int id, HttpServletRequest request) {
        Power power = powerService.selectById(id);
        logger.info(power.toString());
        request.setAttribute("power", power);
        insertLog(BaseController.STAUSE_OK, "进入修改用户权限界面", "id:" + id, "");
        return "/power/edit";
    }


    @ResponseBody
    @GetMapping("/list")
    public List<Power> queryList() {
        List<Power> page = powerService.selectList(null);
        return page;
    }

    @ResponseBody
    @RequestMapping("/insert")
    public String insert(@ModelAttribute Power m) {
        logger.info("======>添加用户权限" + m.toString());
        if (!powerService.insert(m)) {
            insertLog(BaseController.STAUSE_NO, "添加用户权限", m.toString(), "添加失败");
            return ResultJson.resultMsg(false, "添加失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "添加用户权限", m.toString(), "");
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute Power power, HttpSession session) {
        logger.info("======>修改用户权限" + power.toString());
        if (!powerService.updateById(power)) {
            insertLog(BaseController.STAUSE_NO, "修改用户权限", power.toString(), "修改失败");
            return ResultJson.resultMsg(false, "修改失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "修改用户权限", power.toString(), "");
            return ResultJson.resultMsg(true, "");
        }
    }

    @Override
    public String delete(int id, HttpSession session) {
        logger.info("======>删除权限" + id);
        if (!powerService.deleteById(id)) {
            insertLog(BaseController.STAUSE_NO, "删除用户权限", "id:" + id, "删除失败");
            return ResultJson.resultMsg(false, "删除失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "删除用户权限", "id:" + id, "");
            return ResultJson.resultMsg(true, "");
        }
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
        logger.info("======>批量删除权限" + str);
        if (!powerService.deleteBatchIds(idList)) {
            insertLog(BaseController.STAUSE_NO, "批量删除权限", "ids: " + str, "批量删除失败");
            return ResultJson.resultMsg(false, "批量删除失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "批量删除权限", "ids: " + str, "");
            return ResultJson.resultMsg(true, "");
        }
    }

    @GetMapping("/tree")
    @ResponseBody
    public Tree<Power> treeJson() {
        System.out.println(" =====>>>>>>>");
        return powerService.getTree();
    }

}


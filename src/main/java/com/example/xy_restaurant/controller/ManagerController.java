package com.example.xy_restaurant.controller;


import ch.qos.logback.core.status.WarnStatus;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.xy_restaurant.entity.Manager;
import com.example.xy_restaurant.entity.Power;
import com.example.xy_restaurant.entity.QueryParam;
import com.example.xy_restaurant.util.ResultJson;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiangjie
 * @since 2018-03-21
 */
@Controller
@RequestMapping("/sys/user")
public class ManagerController extends BaseController {

    @Override
    public String enterJsp() {
        insertLog(BaseController.STAUSE_OK, "进入用户管理界面", "", "");
        return "user/user";
    }

    @Override
    public String enterAddJsp(HttpServletRequest request) {
        System.out.println(powerService.selectList(null).size());
        request.setAttribute("powers", powerService.selectList(null));
        insertLog(BaseController.STAUSE_OK, "进入添加用户界面", "", "");
        return "user/add";
    }

    @Override
    public String enterAddJsp(int id, HttpServletRequest request) {
        return null;
    }

    @Override
    public String enterEditJsp(@PathVariable("id") int id, HttpServletRequest request) {
        Manager manager = managerService.selectById(id);
        request.setAttribute("manager", manager);
        request.setAttribute("powers", powerService.selectList(null));
        insertLog(BaseController.STAUSE_OK, "进入修改用户界面", "id:" + id, "");
        return "user/edit";
    }

    @ResponseBody
    @GetMapping("/list")
    public List queryList(@ModelAttribute QueryParam queryParam) {
        System.out.println(queryParam.toString());
        List<Manager> managerList = new ArrayList<>();
        List<Manager> list = null;
        if (queryParam.getType() == -1) {
            list = managerService.selectList(null);
        } else {
            list = managerService.selectList(new EntityWrapper<Manager>().eq("manager_power", queryParam.getType()));
        }
        for (Manager manager : list) {
            manager.setPowerName(powerService.selectById(manager.getManagerPower()).getPowerDepict());
            managerList.add(manager);
        }
        return managerList;
    }

    @ResponseBody
    @RequestMapping("/insert")
    public String insert(@ModelAttribute Manager manager) {
        if (!managerService.insert(manager)) {
            insertLog(BaseController.STAUSE_NO, "添加用户", manager.toString(), "添加失败");
            return ResultJson.resultMsg(false, "添加失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "添加用户", manager.toString(), "");
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute Manager manager, HttpSession session) {
        System.out.println(manager.toString());
        if (!managerService.updateById(manager)) {
            insertLog(BaseController.STAUSE_NO, "修改用户", manager.toString(), "修改失败");
            return ResultJson.resultMsg(false, "修改失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "修改用户", manager.toString(), "");
            return ResultJson.resultMsg(true, "");
        }
    }

    @Override
    public String delete(int id, HttpSession session) {
        if (!managerService.deleteById(id)) {
            insertLog(BaseController.STAUSE_NO, "删除用户", "id:" + id, "删除失败");
            return ResultJson.resultMsg(false, "删除失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "删除用户", "id:" + id, "");
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @PostMapping("/batchRemove")
    public String deleteBatchIds(@RequestParam("ids") int[] ids) {
        String str = "";
        List<Integer> idList = new ArrayList<>();
        for (int id : ids) {
            idList.add(id);
            str += "  " + id;
        }
        System.out.println("======>批量删除权限" + str);
        if (!managerService.deleteBatchIds(idList)) {
            insertLog(BaseController.STAUSE_NO, "批量删除用户", "ids: " + str, "批量删除失败");
            return ResultJson.resultMsg(false, "批量删除失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "批量删除用户", "ids: " + str, "");
            return ResultJson.resultMsg(true, "");
        }
    }

    @RequestMapping("/resetPwd/{id}")
    public String enterResetPwd(@PathVariable("id") int id, HttpServletRequest request) {
        Manager manager = managerService.selectById(id);
        request.setAttribute("manager", manager);
        insertLog(BaseController.STAUSE_OK, "进入用户修改密码界面", "ids: " + id, "");
        return "user/resetPwd";
    }

    @ResponseBody
    @RequestMapping("/adminResetPwd")
    public String adminResetPwd(@ModelAttribute Manager manager) {

        if (!managerService.updateById(manager)) {
            insertLog(BaseController.STAUSE_NO, "修改用户密码", manager.toString(), "修改失败");
            return ResultJson.resultMsg(false, "修改失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "修改用户密码", manager.toString(), "");
            return ResultJson.resultMsg(true, "");
        }
    }

}


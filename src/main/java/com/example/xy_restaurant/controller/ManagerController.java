package com.example.xy_restaurant.controller;


import com.baomidou.mybatisplus.activerecord.Model;
import com.example.xy_restaurant.entity.Manager;
import com.example.xy_restaurant.entity.Power;
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
 * @since 2018-03-21
 */
@Controller
@RequestMapping("/sys/user")
public class ManagerController extends BaseController{

    @Override
    @RequestMapping("/user")
    public String enterJsp() {
        return "user/user";
    }

    @Override
    public String enterAddJsp(HttpServletRequest request) {
        System.out.println(powerService.selectList(null).size());
        request.setAttribute("powers", powerService.selectList(null));
        return "user/add";
    }

    @Override
    public String enterEditJsp(@PathVariable("id") int id, HttpServletRequest request) {
        Manager manager = managerService.selectById(id);
        request.setAttribute("manager", manager);
        request.setAttribute("powers", powerService.selectList(null));
        return "user/edit";
    }

    @Override
    public List queryList() {
        return managerService.selectList(null);
    }

    @ResponseBody
    @RequestMapping("/insert")
    public String insert(@ModelAttribute Manager manager) {
        if(!managerService.insert(manager)){
            System.out.println("失败");
            return ResultJson.resultMsg(false, "添加失败");
        }else{
            System.out.println("成功 ");
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute Manager manager, HttpSession session) {
        System.out.println(manager.toString());
        if(!managerService.updateById(manager)){
            return ResultJson.resultMsg(false, "修改失败");
        }else{
            return ResultJson.resultMsg(true, "");
        }
    }

    @Override
    public String delete(int id, HttpSession session) {
        if(!managerService.deleteById(id)){
            return ResultJson.resultMsg(false, "删除失败");
        }else{
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @PostMapping("/batchRemove")
    public String deleteBatchIds(@RequestParam("ids") int[] ids){
        String str = "";
        List<Integer> idList = new ArrayList<>();
        for (int id : ids) {
            idList.add(id);
            str += "  "+id;
        }
        System.out.println("======>批量删除权限"+str);
        if(!managerService.deleteBatchIds(idList)){
            return ResultJson.resultMsg(false, "删除失败");
        }else{
            return ResultJson.resultMsg(true, "");
        }
    }

    @RequestMapping("/resetPwd/{id}")
    public String enterResetPwd(@PathVariable("id") int id ,HttpServletRequest request){
        Manager manager = managerService.selectById(id);
        request.setAttribute("manager", manager);
        return "user/resetPwd";
    }

    @ResponseBody
    @RequestMapping("/adminResetPwd")
    public String adminResetPwd(@ModelAttribute Manager manager){

        if(!managerService.updateById(manager)){
            return ResultJson.resultMsg(false, "修改失败");
        }else{
            return ResultJson.resultMsg(true, "");
        }
    }



//    @RequestMapping("/edit/{id}")
//    public String edit(@PathVariable("id") int id, HttpServletRequest request){
//        Manager manager = managerService.selectById(id);
//
//        logger.info(power.toString());
//        request.setAttribute("power", power);
//        return "/power/edit";
//    }
//
//    @ResponseBody
//    @RequestMapping("/insert")
//    public String insertMenu(@ModelAttribute Power m){
//        logger.info("======>添加用户权限"+m.toString());
//        if(!powerService.insert(m)){
//            return ResultJson.resultMsg(false, "添加失败");
//        }else{
//            return ResultJson.resultMsg(true, "");
//        }
//    }
//    @ResponseBody
//    @RequestMapping("/update")
//    public String updateMenu(@ModelAttribute Power power, HttpSession session){
//        logger.info("======>修改用户权限"+power.toString());
//        if(!powerService.updateById(power)){
//            return ResultJson.resultMsg(false, "修改失败");
//        }else{
//            return ResultJson.resultMsg(true, "");
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping("/remove")
//    public String deleteMenu(@RequestParam("id") int id,HttpSession session){
//        logger.info("======>删除权限"+id);
//        if(!powerService.deleteById(id)){
//            return ResultJson.resultMsg(false, "删除失败");
//        }else{
//            return ResultJson.resultMsg(true, "");
//        }
//    }
//
//    @ResponseBody
//    @PostMapping("/batchRemove")
//    public String deleteBatchIds(@RequestParam int[] ids){
//
//        String str = "    [ p;cx';.c zx; .; "+ids[0];
//        List<Integer> idList = new ArrayList<>();
//        for (int id : ids) {
//            idList.add(id);
//            str += "  "+id;
//        }
//        logger.info("======>批量删除权限"+str);
//        if(!powerService.deleteBatchIds(idList)){
//            return ResultJson.resultMsg(false, "删除失败");
//        }else{
//            return ResultJson.resultMsg(true, "");
//        }
//    }

}


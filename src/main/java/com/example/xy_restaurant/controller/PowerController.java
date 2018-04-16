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
 *  前端控制器
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-04
 */
@Controller
@RequestMapping("/sys/power")
public class PowerController {

    Logger logger = Logger.getLogger("sys_menu");
    @Autowired
    IPowerService powerService;

    @RequestMapping("/power")
    public String powerJsp(){
        return "power/power";
    }

    @ResponseBody
    @GetMapping("/list")
    public List<Power> queryList(){
        List<Power> page = powerService.selectList(null);
        System.out.println("请求");
        return page;
    }
    @RequestMapping("/add")
    public String add(){
        return "/power/add";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, HttpServletRequest request){
        Power power = powerService.selectById(id);

        logger.info(power.toString());
        request.setAttribute("power", power);
        return "/power/edit";
    }

    @ResponseBody
    @RequestMapping("/insert")
    public String insertMenu(@ModelAttribute Power m){
        logger.info("======>添加用户权限"+m.toString());
        if(!powerService.insert(m)){
            return ResultJson.resultMsg(false, "添加失败");
        }else{
            return ResultJson.resultMsg(true, "");
        }
    }
    @ResponseBody
    @RequestMapping("/update")
    public String updateMenu(@ModelAttribute Power power, HttpSession session){
        logger.info("======>修改用户权限"+power.toString());
        if(!powerService.updateById(power)){
            return ResultJson.resultMsg(false, "修改失败");
        }else{
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @RequestMapping("/remove")
    public String deleteMenu(@RequestParam("id") int id,HttpSession session){
        logger.info("======>删除权限"+id);
        if(!powerService.deleteById(id)){
            return ResultJson.resultMsg(false, "删除失败");
        }else{
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @PostMapping("/batchRemove")
    public String deleteBatchIds(@RequestParam int[] ids){
        String str = "";
        List<Integer> idList = new ArrayList<>();
        for (int id : ids) {
            idList.add(id);
            str += "  "+id;
        }
        logger.info("======>批量删除权限"+str);
        if(!powerService.deleteBatchIds(idList)){
            return ResultJson.resultMsg(false, "删除失败");
        }else{
            return ResultJson.resultMsg(true, "");
        }
    }

    @GetMapping("/tree")
    @ResponseBody
    public Tree<Power> treeJson(){

        System.out.println(" =====>>>>>>>" );

//        System.out.println(tree.getId());
        return powerService.getTree();
    }

}


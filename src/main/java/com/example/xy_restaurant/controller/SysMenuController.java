package com.example.xy_restaurant.controller;


import com.example.xy_restaurant.entity.SysMenu;
import com.example.xy_restaurant.service.ISysMenuService;
import com.example.xy_restaurant.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
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
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController{
    Logger logger = Logger.getLogger("sys_menu");
    @Autowired
    ISysMenuService sysMenuService;


    @Override
    public String enterJsp() {
        return "/menu/menu";
    }

    @Override
    public String enterAddJsp(HttpServletRequest request) {
        return null;
    }

    /*
    添加界面
     */
    @Override
    public String enterAddJsp(@PathVariable("id") int id, HttpServletRequest request){
        request.setAttribute("pId",id);
        if(id == 0) {
            request.setAttribute("pName", "根目录");
        }else{
            request.setAttribute("pName", sysMenuService.selectById(id).getMenuName());
        }
        SysMenu sysMenu = sysMenuService.selectById(id);
        return "/menu/add";
    }

    @Override
    public String enterEditJsp(@PathVariable("id")int id, HttpServletRequest request) {
        SysMenu sysMenu = sysMenuService.selectById(id);

        logger.info(sysMenu.toString());
        request.setAttribute("menu", sysMenu);
        if(sysMenu.getMenuSeries() != 0) {
            request.setAttribute("pName", sysMenuService.selectById(sysMenu.getMenuSeries()).getMenuName());
        }else{
            request.setAttribute("pName", "");
        }
        return "/menu/edit";
    }

//    @RequestMapping("/edit/{id}")
//    public String edit(@PathVariable("id") int id, HttpServletRequest request){
//        SysMenu sysMenu = sysMenuService.selectById(id);
//
//        logger.info(sysMenu.toString());
//        request.setAttribute("menu", sysMenu);
//        if(sysMenu.getMenuSeries() != 0) {
//            request.setAttribute("pName", sysMenuService.selectById(sysMenu.getMenuSeries()).getMenuName());
//        }else{
//            request.setAttribute("pName", "");
//        }
//        return "/menu/edit";
//    }

    /*
    选择图标
     */
    @RequestMapping("/font")
    public String goFont(){
        return "/fontList";
    }


    @Override
    @ResponseBody
    @RequestMapping("/list")
    public List queryList() {
        List<SysMenu> menuLists = sysMenuService.selectList(null);
        return menuLists;
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute SysMenu sysMenu, HttpSession session) {
        logger.info("======>修改菜单" + sysMenu.toString());
        if (!sysMenuService.updateById(sysMenu)) {
            return ResultJson.resultMsg(false, "修改失败");
        } else {
            reSession(session);
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @RequestMapping("/insert")
    public String insert(@ModelAttribute SysMenu m, HttpSession session){
        logger.info("======>添加菜单"+m.toString());
        if(!sysMenuService.insert(m)){
            return ResultJson.resultMsg(false, "添加失败");
        }else{
            reSession(session);
            return ResultJson.resultMsg(true, "");
        }
    }


    @Override
    public String delete(int id, HttpSession session) {
        logger.info("======>删除菜单"+id);
        if(!sysMenuService.deleteById(id)){
            return ResultJson.resultMsg(false, "删除失败");
        }else{
            reSession(session);
            return ResultJson.resultMsg(true, "");
        }
    }


    public void reSession(HttpSession session){

        List<SysMenu> menuLists = sysMenuService.selectList(null);
        List<SysMenu> menus = new ArrayList<>();
        for (SysMenu menu : menuLists) {
            if(menu.getMenuSeries() == 0 ){
                menus.add(menu);
            }
        }
        for (int i = 0; i < menuLists.size(); i++) {
            SysMenu sysMenu = menuLists.get(i);
            if(sysMenu.getMenuSeries() != 0){
                SysMenu menu = menus.get(sysMenu.getMenuSeries()-1);
                menu.addChild(sysMenu);
                menus.set(sysMenu.getMenuSeries()-1, menu);
            }
        }
        session.setAttribute("menu", menus);
    }


}


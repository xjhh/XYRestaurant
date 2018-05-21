package com.example.xy_restaurant.controller;


import com.example.xy_restaurant.entity.SysMenu;
import com.example.xy_restaurant.service.ISysMenuService;
import com.example.xy_restaurant.util.ResultJson;
import com.google.gson.Gson;
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
public class SysMenuController extends BaseController {
    Logger logger = Logger.getLogger("sys_menu");


    @Override
    public String enterJsp() {
        insertLog(BaseController.STAUSE_OK, "进入系统菜单管理界面", "", "");
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
    public String enterAddJsp(@PathVariable("id") int id, HttpServletRequest request) {
        request.setAttribute("pId", id);
        if (id == 0) {
            request.setAttribute("pName", "根目录");
        } else {
            request.setAttribute("pName", sysMenuService.selectById(id).getMenuName());
        }
        request.setAttribute("powers", powerService.selectList(null));
        SysMenu sysMenu = sysMenuService.selectById(id);
        insertLog(BaseController.STAUSE_OK, "进入添加系统菜单界面", "id：" + id, "");
        return "/menu/add";
    }

    @Override
    public String enterEditJsp(@PathVariable("id") int id, HttpServletRequest request) {
        SysMenu sysMenu = sysMenuService.selectById(id);

        logger.info(sysMenu.toString());
        request.setAttribute("menu", sysMenu);
        if (sysMenu.getMenuSeries() != 0) {
            request.setAttribute("pName", sysMenuService.selectById(sysMenu.getMenuSeries()).getMenuName());
        } else {
            request.setAttribute("pName", "");
        }
        request.setAttribute("powers", powerService.selectList(null));
        insertLog(BaseController.STAUSE_OK, "进入修改系统菜单界面", "id：" + id, "");
        return "/menu/edit";
    }
    /*
    选择图标
     */
    @RequestMapping("/font")
    public String goFont() {
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
    @RequestMapping("/getMenu")
    public String getMenu() {

        List<SysMenu> menuLists = sysMenuService.selectList(null);
        List<SysMenu> menus = new ArrayList<>();
        for (SysMenu menu : menuLists) {
            if (menu.getMenuSeries() == 0) {
                menus.add(menu);
            }
        }
        for (int i = 0; i < menuLists.size(); i++) {
            SysMenu sysMenu = menuLists.get(i);
            if (sysMenu.getMenuSeries() != 0) {
                System.out.println(sysMenu.toString());
                SysMenu menu = menus.get(sysMenu.getMenuSeries() - 1);
                menu.addChild(sysMenu);
                menus.set(sysMenu.getMenuSeries() - 1, menu);
            }
        }
        System.out.println(new Gson().toJson(menus));
        return new Gson().toJson(menus);
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute SysMenu sysMenu, HttpSession session) {
        logger.info("======>修改菜单" + sysMenu.toString());
        if (!sysMenuService.updateById(sysMenu)) {
            insertLog(BaseController.STAUSE_NO, "修改菜单", sysMenu.toString(), "修改失败");
            return ResultJson.resultMsg(false, "修改失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "修改菜单", sysMenu.toString(), "");
            reSession(session);
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @RequestMapping("/insert")
    public String insert(@ModelAttribute SysMenu m, HttpSession session) {
        logger.info("======>添加菜单" + m.toString());
        if (!sysMenuService.insert(m)) {
            insertLog(BaseController.STAUSE_NO, "添加菜单", m.toString(), "添加失败");
            return ResultJson.resultMsg(false, "添加失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "添加菜单", m.toString(), "");
            reSession(session);
            return ResultJson.resultMsg(true, "");
        }
    }


    @Override
    public String delete(int id, HttpSession session) {
        logger.info("======>删除菜单" + id);
        if (!sysMenuService.deleteById(id)) {
            insertLog(BaseController.STAUSE_NO, "删除菜单", "id:" + id, "删除失败");
            return ResultJson.resultMsg(false, "删除失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "删除菜单", "id:" + id, "");
            reSession(session);
            return ResultJson.resultMsg(true, "");
        }
    }


    public void reSession(HttpSession session) {

        List<SysMenu> menuLists = sysMenuService.selectList(null);
        List<SysMenu> menus = new ArrayList<>();
        for (SysMenu menu : menuLists) {
            if (menu.getMenuSeries() == 0) {
                menus.add(menu);
            }
        }
        for (int i = 0; i < menuLists.size(); i++) {
            SysMenu sysMenu = menuLists.get(i);
            if (sysMenu.getMenuSeries() != 0) {
                SysMenu menu = menus.get(sysMenu.getMenuSeries() - 1);
                menu.addChild(sysMenu);
                menus.set(sysMenu.getMenuSeries() - 1, menu);
            }
        }
        session.setAttribute("menu", menus);
    }


}


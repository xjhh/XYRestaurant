package com.example.xy_restaurant.controller;

import com.example.xy_restaurant.entity.Manager;
import com.example.xy_restaurant.entity.SysMenu;
import com.example.xy_restaurant.service.IManagerService;
import com.example.xy_restaurant.service.IPowerService;
import com.example.xy_restaurant.service.ISysMenuService;
import com.example.xy_restaurant.util.ResultJson;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/xiangyue")
public class LoginController extends BaseController{
    public static Manager manager;

    @Autowired
    private IManagerService managerService;
    @Autowired
    private IPowerService powerService;
    @Autowired
    ISysMenuService sysMenuService;

    @RequestMapping("/login")
    public String onLogin(HttpServletRequest request) {
//        File file = new File(System.getProperty("user.dir"));
//        File dir = new File( LoginController.class.getClassLoader().getResource("").getPath(), "/static/goods_image");
//        System.out.println("dir is not"+    dir.exists());
        request.setAttribute("power", powerService.selectList(null));
        return "index";
    }

    @ResponseBody
    @RequestMapping("/logins")
    public String judgeLogin(@ModelAttribute Manager manager, HttpSession session) throws IOException {
        System.out.println(" ==========>>>  "+ manager.toString());
        int id = managerService.selectUserByAll(manager);
        if (id == 0) {
            insertLog(BaseController.STAUSE_NO, "登录系统");
            return ResultJson.resultMsg(false, "登录失败，请检查您的帐号或密码或身份是否正确！！！");
        } else {
            manager.setManagerId(id);
            session.setAttribute("user", manager);
            session.setAttribute("powerName", powerService.selectById(manager.getManagerPower()).getPowerDepict());
            LoginController.manager = manager;
            insertLog(BaseController.STAUSE_OK, "登录系统", manager.toString(), "");
            return ResultJson.resultMsg(true, "");
        }

    }

    @RequestMapping("/main")
    public String toMain(HttpServletRequest request, HttpSession session) {

        if(session.getAttribute("user") == null){
            request.setAttribute("power", powerService.selectList(null));
            return "index";
        }
        int power = ((Manager)session.getAttribute("user")).getManagerPower();
        List<SysMenu> menuLists = sysMenuService.selectList(null);
        List<SysMenu> menus = new ArrayList<>();
        for (SysMenu menu : menuLists) {
            if(menu.getMenuSeries() == 0 ){
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

        if(power == 1){
            List<SysMenu> menus1 = new ArrayList<>();
            for (SysMenu menu : menus) {
                if(menu.getMenuPower() == power){
                    menus1.add(menu);
                }
            }
            session.setAttribute("menu", menus1);
        }else if(power == 2 || power == 24){
            List<SysMenu> menus1 = new ArrayList<>();
            for (SysMenu menu : menus) {
                if(menu.getMenuName().equals("餐厅管理")){
                    menu.setMenuChilds(power);
                    menus1.add(menu);
                }
            }
            session.setAttribute("menu", menus1);

        }else{
            session.setAttribute("menu", menus);
        }
        insertLog(BaseController.STAUSE_OK, "进入主界面", "", "");
        return "main";
    }


    @Override
    public String enterJsp() {
        return null;
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
        return null;
    }
}

package com.example.xy_restaurant.controller;

import com.example.xy_restaurant.entity.Manager;
import com.example.xy_restaurant.entity.SysMenu;
import com.example.xy_restaurant.service.IManagerService;
import com.example.xy_restaurant.service.IPowerService;
import com.example.xy_restaurant.service.ISysMenuService;
import com.example.xy_restaurant.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/xiangyue")
public class LoginController {

    @Autowired
    private IManagerService managerService;
    @Autowired
    private IPowerService powerService;
    @Autowired
    ISysMenuService sysMenuService;

    @RequestMapping("/login")
    public String onLogin(HttpServletRequest request) {
        File file = new File(System.getProperty("user.dir"));
        File dir = new File( LoginController.class.getClassLoader().getResource("").getPath(), "/static/goods_image");
        System.out.println("dir is not"+    dir.exists());
        request.setAttribute("power", powerService.selectList(null));
        return "index";
    }

    @ResponseBody
    @RequestMapping("/logins")
    public String judgeLogin(@ModelAttribute Manager manager, HttpSession session) throws IOException {
        System.out.println(" ==========>>>  "+manager.toString());
        int id = managerService.selectUserByAll(manager);
        if (id == 0) {
            return ResultJson.resultMsg(false, "登录失败，请检查您的帐号或密码或身份是否正确！！！");
        } else {
            manager.setManagerId(id);
            session.setAttribute("user", manager);
            session.setAttribute("powerName", powerService.selectById(manager.getManagerPower()).getPowerDepict());
            return ResultJson.resultMsg(true, "");
        }

    }

    @RequestMapping("/main")
    public String toMain(HttpSession session) {

        List<SysMenu> menuLists = sysMenuService.selectList(null);
        List<SysMenu> menus = new ArrayList<>();
        for (SysMenu menu : menuLists) {
            if(menu.getMenuSeries() == 0 ){
                menus.add(menu);
            }
        }
        for (SysMenu menu : menus) {
            System.out.println(menu.toString());
        }
        for (int i = 0; i < menuLists.size(); i++) {
            SysMenu sysMenu = menuLists.get(i);
            if(sysMenu.getMenuSeries() != 0){
                System.out.println(sysMenu.toString());
                SysMenu menu = menus.get(sysMenu.getMenuSeries()-1);
                System.out.println(menu);
                menu.addChild(sysMenu);
                System.out.println(menu);
                menus.set(sysMenu.getMenuSeries()-1, menu);
            }
        }
        session.setAttribute("menu", menus);
        return "main";
    }
}

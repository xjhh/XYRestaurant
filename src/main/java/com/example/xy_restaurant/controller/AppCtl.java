package com.example.xy_restaurant.controller;

import com.example.xy_restaurant.entity.Manager;
import com.example.xy_restaurant.service.IManagerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/xiangyue/app")
public class AppCtl {

    @Autowired
    IManagerService managerService;
    @RequestMapping("/hellow")
    public String hellow(){
        return "hellow";
    }

    @RequestMapping("/login")
    public void onLogin(@ModelAttribute Manager manager, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        int id = managerService.selectUserByAll(manager);
        JsonObject jsonObject = new JsonObject();
        if(id == 0){ ;
            jsonObject.addProperty("result", false);
            jsonObject.addProperty("error_msg", "请检查您的身份或用户名密码是否正确！！！");
        }else{
            jsonObject.addProperty("result", true);
        }
        try {
            response.getWriter().append(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

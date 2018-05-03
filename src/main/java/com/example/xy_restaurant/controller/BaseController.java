package com.example.xy_restaurant.controller;

import com.baomidou.mybatisplus.activerecord.Model;
import com.example.xy_restaurant.entity.Manager;
import com.example.xy_restaurant.service.*;
import com.example.xy_restaurant.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public abstract class BaseController  {


    @Autowired
    public IManagerService managerService;
    @Autowired
    public IPowerService powerService;
    @Autowired
    public ISysMenuService sysMenuService;
    @Autowired
    IGoodsTypeService goodsTypeService;
    @Autowired
    IGoodsService goodsService;
    @Autowired
    IDeskService deskService;
    @Autowired
    IMemberService memberService;

    @RequestMapping("/jsp")
    public abstract String enterJsp();

    @RequestMapping("/add")
    public abstract String enterAddJsp(HttpServletRequest request);

    @RequestMapping("/add/{id}")
    public abstract String enterAddJsp(@PathVariable("id") int id, HttpServletRequest request);

    @RequestMapping("/edit/{id}")
    public abstract String enterEditJsp(@PathVariable("id") int id, HttpServletRequest request);

    public  List queryList(){
        return null;
    }

    public  String insert(){
        return null;
    }


    public String update(){
        return null;
    }


    @ResponseBody
    @RequestMapping("/remove")
    public abstract String delete(@RequestParam("id") int id,HttpSession session);


    public String deleteBatchIds(@RequestParam int[] ids){return null;}

}

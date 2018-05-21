package com.example.xy_restaurant.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.xy_restaurant.entity.SysLog;
import com.example.xy_restaurant.entity.SysMenu;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-22
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController extends BaseController {

    @Override
    public String enterJsp() {
        return "sysLog";
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


    @ResponseBody
    @RequestMapping("/list")
    public List queryList(@RequestParam Map<String, Object> q) {
        System.out.println("===============================" + q+"   "+q.get("id"));
        List<String> strings = new ArrayList<>();
        strings.add("log_id");
        List<SysLog> logList = null;
        if("" != q.get("id")){
            return iSysLogService.selectList(new EntityWrapper<SysLog>().orderDesc(strings).eq("log_user_id",  q.get("id")));
        }
        logList = iSysLogService.selectList(new EntityWrapper<SysLog>().orderDesc(strings));
        System.out.println(logList);
        return logList;
    }

}


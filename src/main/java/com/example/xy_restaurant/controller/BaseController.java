package com.example.xy_restaurant.controller;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.xy_restaurant.entity.Income;
import com.example.xy_restaurant.entity.Manager;
import com.example.xy_restaurant.entity.SysLog;
import com.example.xy_restaurant.service.*;
import com.example.xy_restaurant.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public abstract class BaseController {
    Manager manager;
    public static final int STAUSE_OK = 0;
    public static final int STAUSE_NO = 1;


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
    @Autowired
    IPurchaseService purchaseService;
    @Autowired
    IConsumeService consumeService;
    @Autowired
    IIncomeService iIncomeService;

    @Autowired
    ISysLogService iSysLogService;

    @RequestMapping("/jsp")
    public abstract String enterJsp();

    @RequestMapping("/add")
    public abstract String enterAddJsp(HttpServletRequest request);

    @RequestMapping("/add/{id}")
    public abstract String enterAddJsp(@PathVariable("id") int id, HttpServletRequest request);

    @RequestMapping("/edit/{id}")
    public abstract String enterEditJsp(@PathVariable("id") int id, HttpServletRequest request);

    public List queryList() {
        return null;
    }

    public String insert() {
        return null;
    }


    public String update() {
        return null;
    }


    @ResponseBody
    @RequestMapping("/remove")
    public abstract String delete(@RequestParam("id") int id, HttpSession session);


    public String deleteBatchIds(@RequestParam int[] ids) {
        return null;
    }


    public boolean updateIncome(double price) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(new Date());
        Income income = iIncomeService.selectOne(new EntityWrapper<Income>().eq("income_data", time));
        if (null == income) {
            income = new Income();
            if (price < 0) {
                income.setIncomeExpend(Math.abs(price));
            } else {
                income.setIncomeNet(price);
            }
            income.setIncomeTotal(price);
            income.setIncomeData(time);
            return iIncomeService.insert(income);
        }
        if (price < 0) {
            income.setIncomeExpend(income.getIncomeExpend() + Math.abs(price));
        } else {
            income.setIncomeNet(income.getIncomeNet() + price);
        }
        income.setIncomeTotal(income.getIncomeTotal() + price);
        return iIncomeService.updateById(income);
    }

    public void insertLog(int status, String... str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        SysLog sysLog = new SysLog();
        sysLog.setLogUserId(LoginController.manager.getManagerId());
        sysLog.setLogStatus(status);
        sysLog.setLogOperate(str[0]);
        sysLog.setLogMethodFunction(stes[2].getClassName() + "." + stes[2].getMethodName());
        sysLog.setLogParameter(str[1]);
        sysLog.setLogTerminal("PC端");
        sysLog.setLogErrorMsg(str[2]);
        sysLog.setLogTime(sdf.format(new Date()));
        iSysLogService.insert(sysLog);
    }

    public String getClassName() {

        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stes.length; i++) {
            System.out.println("<------>" + stes[i].getClassName() + "<------>" + stes[i].getMethodName() + "<------>" + stes[i].getFileName());
        }
        return stes[3].getClassName();
    }

    public String getMethodName() {


        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stes.length; i++) {
            System.out.println("<------>" + stes[i].getClassName() + "<------>" + stes[i].getMethodName() + "<------>" + stes[i].getFileName());
        }
        return stes[3].getMethodName();
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}

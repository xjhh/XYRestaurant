package com.example.xy_restaurant.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.xy_restaurant.entity.Income;
import com.example.xy_restaurant.util.ResultJson;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-04
 */
@Controller
@RequestMapping("/income")
public class IncomeController extends BaseController{

    @Override
    public String enterJsp() {
        insertLog(BaseController.STAUSE_OK, "进入餐厅收益查看界面","","");
        return "income";
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
    @RequestMapping("getData")
    public String getData(){
        List<Income> incomeList = iIncomeService.selectList(null);
        double[] nets = new double[incomeList.size()];
        double[] expends = new double[incomeList.size()];
        double[] incomes = new double[incomeList.size()];
        String[] times = new String[incomeList.size()];
         for (int i = 0; i < incomeList.size(); i++) {
            Income income = incomeList.get(i);
            nets[i] = income.getIncomeNet();
            expends[i] = Math.abs(income.getIncomeExpend());
            incomes[i] = income.getIncomeTotal();
            times[i] = income.getIncomeData();
        }
        System.out.println("---------------   "+ new Gson().toJson(new incomeArray(nets, expends, incomes, times))) ;
        return new Gson().toJson(new incomeArray(nets, expends, incomes, times));

    }

    class incomeArray{
        double[] nets ;
        double[] expends;
        double[] incomes;
        String[] times;

        public incomeArray(double[] nets, double[] expends, double[] incomes, String[] times) {
            this.nets = nets;
            this.expends = expends;
            this.incomes = incomes;
            this.times = times;
        }

        public String[] getTimes() {
            return times;
        }

        public void setTimes(String[] times) {
            this.times = times;
        }

        public double[] getNets() {
            return nets;
        }

        public void setNets(double[] nets) {
            this.nets = nets;
        }

        public double[] getExpends() {
            return expends;
        }

        public void setExpends(double[] expends) {
            this.expends = expends;
        }

        public double[] getIncomes() {
            return incomes;
        }

        public void setIncomes(double[] incomes) {
            this.incomes = incomes;
        }
    }


//    @ResponseBody
//    @RequestMapping("/add/{price}")
//    public boolean addIncome(@PathVariable("price") double price){
//        return updateIncome(price);
//    }
//    @ResponseBody
//    @RequestMapping("/less/{price}")
//    public boolean lessIncome(@PathVariable("price") double price){
//        return updateIncome(-price);
//    }
}


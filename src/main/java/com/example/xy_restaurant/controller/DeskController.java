package com.example.xy_restaurant.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.xy_restaurant.entity.Desk;
import com.example.xy_restaurant.entity.GoodsType;
import com.example.xy_restaurant.entity.Tree;
import com.example.xy_restaurant.util.PageUtils;
import com.example.xy_restaurant.util.ResultJson;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import sun.security.krb5.internal.crypto.Des;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-04
 */
@Controller
@RequestMapping("/desk")
public class DeskController extends BaseController{

    @Override
    public String enterJsp() {
        return "desk/desks";
    }

    @Override
    public String enterAddJsp(HttpServletRequest request) {
        return "desk/add";
    }

    @Override
    public String enterAddJsp(@PathVariable("id") int id, HttpServletRequest request) {

        return null;
    }

    @Override
    public String enterEditJsp(int id, HttpServletRequest request) {
        request.setAttribute("desk", deskService.selectById(id));
        return "desk/edit";
    }

    @RequestMapping("/editStatus")
    @ResponseBody
    public String editStatus(@RequestParam("id") int id, HttpServletRequest request){
        System.out.println("------   "+id);
        Desk desk = deskService.selectById(id);
        desk.setDeskState(desk.getDeskState() == 0? 1 : 0);
        System.out.println("------   "+desk);
        if(!deskService.updateById(desk)){
            return ResultJson.resultMsg(false, "修改失败");
        }else{
            return ResultJson.resultMsg(true, "修改成功");
        }
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils queryList(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        int type = (params.get("type") == ""|| params.get("type") == "-1")? -1: Integer.parseInt((String) params.get("type"));
        List<Desk> list = deskService.selectList(type == -1 ? null: new EntityWrapper<Desk>().eq("desk_type", type));
        return new PageUtils(list, list.size());
    }

    @ResponseBody
    @RequestMapping("/insert")
    public String insert(@ModelAttribute Desk desk){
//        logger.info("======>添加商品类别"+goodsType.toString());\
        List<Desk> deskList = new ArrayList<>();
        int count = deskService.selectCount(new EntityWrapper<Desk>().eq("desk_type", desk.getDeskType()));

        for (int i = 0; i < desk.getAddSize(); i++) {
            count++;
            Desk d = new Desk();
            switch (desk.getDeskType()) {
                case 2:
                    d.setDeskNumber("R"+count);
                    break;
                case 4:
                    d.setDeskNumber("S"+count);
                    break;
                case 8:
                    d.setDeskNumber("B"+count);
                    break;
                case 10:
                    d.setDeskNumber("T"+count);
                    break;
            }
            d.setDeskType(desk.getDeskType());
            d.setDeskState(0);
            deskList.add(d);
        }
        if(!deskService.insertBatch(deskList)){
            return ResultJson.resultMsg(false, "添加失败");
        }else{
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute Desk desk, HttpSession session){

        if(!deskService.updateById(desk)){
            return ResultJson.resultMsg(false, "修改失败");
        }else{
            return ResultJson.resultMsg(true, "");
        }
    }

    @Override
    public String delete(int id, HttpSession session) {
//        logger.info("======>删除类别"+id);
        if(!deskService.deleteById(id)){
            return ResultJson.resultMsg(false, "删除失败");
        }else{
            return ResultJson.resultMsg(true, "");
        }
    }

}


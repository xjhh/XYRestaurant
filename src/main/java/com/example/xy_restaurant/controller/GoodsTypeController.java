package com.example.xy_restaurant.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.xy_restaurant.entity.*;
import com.example.xy_restaurant.service.IGoodsTypeService;
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
 *  前端控制器
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-04
 */
@Controller
@RequestMapping("/goodsType")
public class GoodsTypeController extends BaseController{

    Logger logger = Logger.getLogger("GoodsType");


    @Override
    public String enterJsp() {

        insertLog(BaseController.STAUSE_OK, "进入商品类型管理界面", "", "");
        return "goods_type/goodsType";
    }

    @Override
    public String enterAddJsp(HttpServletRequest request) {
        insertLog(BaseController.STAUSE_OK, "进入添加商品类型界面", "", "");
        return  "goods_type/add";
    }

    @Override
    public String enterAddJsp(int id, HttpServletRequest request) {
        return null;
    }

    @Override
    public String enterEditJsp(@PathVariable("id") int id, HttpServletRequest request) {
        GoodsType goodsType = goodsTypeService.selectById(id);
        request.setAttribute("goodsType", goodsType);
        insertLog(BaseController.STAUSE_OK, "进入修改商品类型界面", "", "");
        return "goods_type/edit";
    }

    @ResponseBody
    @GetMapping("/list")
    public List queryList() {
        return goodsTypeService.selectList(null);
    }

    @ResponseBody
    @RequestMapping("/insert")
    public String insert(@ModelAttribute GoodsType goodsType){
        logger.info("======>添加商品类别"+goodsType.toString());
        if(!goodsTypeService.insert(goodsType)){
            insertLog(BaseController.STAUSE_NO, "添加商品类型", goodsType.toString(), "添加失败");
            return ResultJson.resultMsg(false, "添加失败");
        }else{
            insertLog(BaseController.STAUSE_OK, "添加商品类型", goodsType.toString(), "");
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute GoodsType goodsType, HttpSession session){
        logger.info("======>修改商品类别"+goodsType.toString());
        if(!goodsTypeService.updateById(goodsType)){
            insertLog(BaseController.STAUSE_NO, "修改商品类型", goodsType.toString(), "修改失败");
            return ResultJson.resultMsg(false, "修改失败");
        }else{
            insertLog(BaseController.STAUSE_OK, "修改商品类型", goodsType.toString(), "");
            return ResultJson.resultMsg(true, "");
        }
    }

    @Override
    public String delete(int id, HttpSession session) {
        logger.info("======>删除类别"+id);
        if(!goodsTypeService.deleteById(id)){
            insertLog(BaseController.STAUSE_NO, "删除商品类型", "GoodTypeID: "+id, "删除失败");
            return ResultJson.resultMsg(false, "删除失败");
        }else{
            insertLog(BaseController.STAUSE_OK, "删除商品类型",  "GoodTypeID: "+id, "");
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @PostMapping("/batchRemove")
    public String deleteBatchIds(@RequestParam int[] ids){
        String str = "";
        List<Integer> idList = new ArrayList<>();
        for (int id : ids) {
            idList.add(id);
            str += "  "+id;
        }
        logger.info("======>批量删除类别"+str);
        if(!goodsTypeService.deleteBatchIds(idList)){
            insertLog(BaseController.STAUSE_NO, "批量删除商品类型", "GoodTypeID: "+str, "批量删除失败");
            return ResultJson.resultMsg(false, "批量删除失败");
        }else{
            insertLog(BaseController.STAUSE_OK, "批量删除商品类型", "GoodTypeID: "+str, "");
            return ResultJson.resultMsg(true, "");
        }
    }

    @GetMapping("/tree")
    @ResponseBody
    public Tree<GoodsType> treeJson(){
        return goodsTypeService.getTree();
    }
}


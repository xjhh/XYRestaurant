package com.example.xy_restaurant.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.xy_restaurant.entity.Goods;
import com.example.xy_restaurant.entity.GoodsType;
import com.example.xy_restaurant.entity.Manager;
import com.example.xy_restaurant.entity.QueryParam;
import com.example.xy_restaurant.util.PinyinUtils;
import com.example.xy_restaurant.util.ResultJson;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-04
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {


    @Override
    public String enterJsp() {
        insertLog(BaseController.STAUSE_OK, "进入商品管理界面", "", "");
        return "goods/goods";
    }

    @Override
    public String enterAddJsp(HttpServletRequest request) {
        insertLog(BaseController.STAUSE_OK, "进入添加商品界面", "", "");
        request.setAttribute("goodsType", goodsTypeService.selectList(null));
        return "goods/add";
    }

    @Override
    public String enterAddJsp(int id, HttpServletRequest request) {
        return null;
    }

    @Override
    public String enterEditJsp(@PathVariable("id") int id, HttpServletRequest request) {
        insertLog(BaseController.STAUSE_OK, "进入修改商品界面", "goodsId:  " + id, "");
        request.setAttribute("goodsType", goodsTypeService.selectList(null));
        request.setAttribute("good", goodsService.selectById(id));
        return "goods/edit";
    }

    @ResponseBody
    @GetMapping("isNotStock/{id}/{num}")
    public String isNotStock(@PathVariable("id") int id, @PathVariable("num") int num) {
        Goods goods = goodsService.selectById(id);
        if (null == goods) {
            return ResultJson.resultMsg(false, "没有该商品信息");
        }
        if (goods.getGoodStock() - num < 0) {
            return ResultJson.resultMsg(false, "对不起库存不足");
        }

        return ResultJson.resultMsg(true, "");
    }

    @ResponseBody
    @GetMapping("/list")
    public List queryList(@ModelAttribute QueryParam queryParam) {
        System.out.println(queryParam.toString());
        List<Goods> goodsList = new ArrayList<>();
        List<Goods> list = null;
        if (queryParam.getType() == -1) {
            list = goodsService.selectList(null);
        } else {
            list = goodsService.selectList(new EntityWrapper<Goods>().eq("good_type", queryParam.getType()).like("good_name", queryParam.getName()));
        }
        System.out.println("------->" + list.toString());
        for (Goods good : list) {
            good.setGoodTypeName(goodsTypeService.selectById(good.getGoodType()).getTypeName());
            goodsList.add(good);
        }
        return goodsList;
    }


    @ResponseBody
    @RequestMapping("/insert")
    public String insert(@ModelAttribute Goods good) {
        List<Goods> goodsList = goodsService.selectList(null);
        List<GoodsType> goodsTypes = goodsTypeService.selectList(null);
        File path = null;
        int id = 0;
        if (goodsList.size() == 0) {
            path = new File(GoodsController.class.getClassLoader().getResource("").getPath(), "/static/goods_image/0");
        } else {
            id = goodsList.get(goodsList.size() - 1).getGoodId() + 1;
            path = new File(GoodsController.class.getClassLoader().getResource("").getPath(), "/static/goods_image/" + id);
        }
        File file = new File(path, "img.jpg");
        System.out.println("======>保存路径   " + path);
        BufferedOutputStream bos = null;

        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            bos = new BufferedOutputStream(new FileOutputStream(file));
            byte[] bytes = setblob(good.getGoodImg());
            bos.write(bytes, 0, bytes.length);
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        good.setGoodImg("/goods_image/" + id + "/img.jpg");
        for (int i = 0; i < goodsTypes.size(); i++) {
            if (good.getGoodType() == i) {
                good.setGoodNumber(PinyinUtils.getAlpha(goodsTypes.get(i).getTypeName()).substring(0, 1) + id);
                break;
            }
        }

        System.out.println("添加商品------->>" + good);
        if (!goodsService.insert(good)) {
            insertLog(BaseController.STAUSE_NO, "添加商品", good.toString(), "添加失败");
            return ResultJson.resultMsg(false, "添加失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "添加商品", good.toString(), "");
            return ResultJson.resultMsg(true, "");
        }

    }


    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute Goods good, HttpSession session) {
        System.out.println("======>修改商品" + good.toString());
        if (good.getGoodImg().indexOf("goods_image") == -1) {
            File path = new File(GoodsController.class.getClassLoader().getResource("").getPath(), "/static/goods_image/" + good.getGoodId());
            deleteFile(path);
            BufferedOutputStream bos = null;

            File file = new File(path, "img.jpg");
            try {
                bos = new BufferedOutputStream(new FileOutputStream(file));
                byte[] bytes = setblob(good.getGoodImg());
                bos.write(bytes, 0, bytes.length);
                bos.flush();
                bos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            good.setGoodImg("/goods_image/" + good.getGoodId() + "/img.jpg");
        }
        if (!goodsService.updateById(good)) {
            insertLog(BaseController.STAUSE_NO, "修改商品", good.toString(), "修改失败");
            return ResultJson.resultMsg(false, "修改失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "修改商品", good.toString(), "");
            return ResultJson.resultMsg(true, "");
        }
    }


    @Override
    public String delete(int id, HttpSession session) {
        System.out.println("======>删除类别" + id);
        if (!goodsService.deleteById(id)) {
            insertLog(BaseController.STAUSE_NO, "删除商品", "goodsID: " + id, "删除失败");
            return ResultJson.resultMsg(false, "删除失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "删除商品", "goodsID: " + id, "");
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @PostMapping("/batchRemove")
    public String deleteBatchIds(@RequestParam int[] ids) {
        String str = "";
        List<Integer> idList = new ArrayList<>();
        for (int id : ids) {
            idList.add(id);
            str += "  " + id;
        }
        System.out.println("======>批量删除类别" + str);
        if (!goodsService.deleteBatchIds(idList)) {
            insertLog(BaseController.STAUSE_NO, "批量删除商品", "goodsIDs: " + str, "删除失败");
            return ResultJson.resultMsg(false, "删除失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "批量删除商品", "goodsIDs: " + str, "");
            return ResultJson.resultMsg(true, "");
        }
    }

    public static void deleteFile(File file) {
        try {
            if (!file.isDirectory()) {
                file.delete();
            } else {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File childFile = new File(file, filelist[i]);
                    if (file.isDirectory()) {
                        deleteFile(childFile);
                    } else {
                        childFile.delete();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] setblob(String base64) throws IOException {
        System.out.println(base64);
        if (base64 != "" && base64 != null) {
            String[] strbase = base64.split("base64,");
            BASE64Decoder decoder = new BASE64Decoder();
            return decoder.decodeBuffer(strbase[1]);
        } else {
            return null;
        }
    }

}


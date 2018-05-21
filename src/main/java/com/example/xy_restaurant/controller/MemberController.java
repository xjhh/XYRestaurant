package com.example.xy_restaurant.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.xy_restaurant.entity.Member;
import com.example.xy_restaurant.entity.QueryParam;
import com.example.xy_restaurant.util.ResultJson;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
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
@RequestMapping("/member")
public class MemberController extends BaseController {

    @Override
    public String enterJsp() {
        insertLog(BaseController.STAUSE_OK, "进入会员管理界面", "", "");
        return "member/member";
    }

    @Override
    public String enterAddJsp(HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        request.setAttribute("memberCard", sdf.format(System.currentTimeMillis()));
        insertLog(BaseController.STAUSE_OK, "进入会员添加界面", "", "");
        return "member/add";
    }

    @Override
    public String enterAddJsp(int id, HttpServletRequest request) {
        return null;
    }

    @Override
    public String enterEditJsp(@PathVariable("id") int id, HttpServletRequest request) {
        request.setAttribute("member", memberService.selectById(id));
        insertLog(BaseController.STAUSE_OK, "进入会员修改界面", "id:"+id, "");
        return "member/edit";
    }

    @ResponseBody
    @GetMapping("/list")
    public List queryList(@ModelAttribute QueryParam queryParam) {
        System.out.println(queryParam.toString());
        List<Member> memberList = null;
        if (queryParam.getName() != "")
            if (queryParam.getType() == 0) {
                memberList = memberService.selectList(new EntityWrapper<Member>().eq("member_card", queryParam.getName()));
            } else if (queryParam.getType() == 1) {
                memberList = memberService.selectList(new EntityWrapper<Member>().eq("member_name", queryParam.getName()));
            } else {
                memberList = memberService.selectList(new EntityWrapper<Member>().eq("member_phone", queryParam.getName()));
            }
        else
            memberList = memberService.selectList(null);
        System.out.println("====>" + memberList);
        return memberList;
    }

    @ResponseBody
    @RequestMapping("/insert")
    public String insert(@ModelAttribute Member member) {
        if (!memberService.insert(member)) {
            insertLog(BaseController.STAUSE_NO, "添加用户", member.toString(), "添加失败");
            return ResultJson.resultMsg(false, "添加失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "添加用户", member.toString(), "");
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute Member member, HttpSession session) {
        System.out.println(member.toString());
        if (!memberService.updateById(member)) {
            insertLog(BaseController.STAUSE_NO, "修改用户", member.toString(), "修改失败");
            return ResultJson.resultMsg(false, "修改失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "修改用户", member.toString(), "");
            return ResultJson.resultMsg(true, "");
        }
    }

    @Override
    public String delete(int id, HttpSession session) {
        if (!memberService.deleteById(id)) {
            insertLog(BaseController.STAUSE_NO, "删除用户", "memberid:" + id, "删除失败");
            return ResultJson.resultMsg(false, "删除失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "删除用户", "memberid:" + id, "");
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @RequestMapping("/isNotEmpty/{id}")
    public String isNotEmpty(@PathVariable("id") String id){
        if(memberService.selectOne(new EntityWrapper<Member>().eq("member_card", id)) == null){
            return ResultJson.resultMsg(false, "没有该会员");
        }else{
            return ResultJson.resultMsg(true, "");
        }
    }

    @RequestMapping("/resetPwd/{id}")
    public String enterResetPwd(@PathVariable("id") int id, HttpServletRequest request) {
        Member member = memberService.selectById(id);
        request.setAttribute("member", member);
        insertLog(BaseController.STAUSE_OK, "进入会员修改密码界面", "ids: "+id, "");
        return "member/resetPwd";
    }

    @ResponseBody
    @RequestMapping("/memberResetPwd")
    public String adminResetPwd(@ModelAttribute Member member) {
        Member m = memberService.selectById(member.getMemberId());
        m.setMemberPassword(member.getMemberPassword());
        if (!memberService.updateById(m)) {
            insertLog(BaseController.STAUSE_NO, "修改会员密码", member.toString(), "修改失败");
            return ResultJson.resultMsg(false, "修改失败");
        } else {
            insertLog(BaseController.STAUSE_OK, "修改会员密码", member.toString(), "");
            return ResultJson.resultMsg(true, "");
        }
    }
}


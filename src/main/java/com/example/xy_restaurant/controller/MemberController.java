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
        return "member/member";
    }

    @Override
    public String enterAddJsp(HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        request.setAttribute("memberCard", sdf.format(System.currentTimeMillis()));
        return "member/add";
    }

    @Override
    public String enterAddJsp(int id, HttpServletRequest request) {
        return null;
    }

    @Override
    public String enterEditJsp(@PathVariable("id") int id, HttpServletRequest request) {
        request.setAttribute("member", memberService.selectById(id));
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
            return ResultJson.resultMsg(false, "添加失败");
        } else {
            return ResultJson.resultMsg(true, "");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(@ModelAttribute Member member, HttpSession session) {
        System.out.println(member.toString());
        if (!memberService.updateById(member)) {
            return ResultJson.resultMsg(false, "修改失败");
        } else {
            return ResultJson.resultMsg(true, "");
        }
    }

    @Override
    public String delete(int id, HttpSession session) {
        if (!memberService.deleteById(id)) {
            return ResultJson.resultMsg(false, "删除失败");
        } else {
            return ResultJson.resultMsg(true, "");
        }
    }

    @RequestMapping("/resetPwd/{id}")
    public String enterResetPwd(@PathVariable("id") int id, HttpServletRequest request) {
        Member member = memberService.selectById(id);
        request.setAttribute("member", member);
        return "member/resetPwd";
    }

    @ResponseBody
    @RequestMapping("/memberResetPwd")
    public String adminResetPwd(@ModelAttribute Member member) {
        Member m = memberService.selectById(member.getMemberId());
        m.setMemberPassword(member.getMemberPassword());
        if (!memberService.updateById(m)) {
            return ResultJson.resultMsg(false, "修改失败");
        } else {
            return ResultJson.resultMsg(true, "");
        }
    }
}


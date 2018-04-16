package com.example.xy_restaurant.service.impl;

import com.example.xy_restaurant.entity.Member;
import com.example.xy_restaurant.mapper.MemberMapper;
import com.example.xy_restaurant.service.IMemberService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiangjie
 * @since 2018-04-04
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

}

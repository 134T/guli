package com.ucenter.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.commonutils.JwtUtils;
import com.commonutils.ResultMessage;
import com.commonutils.vo.UcenterMemberPay;
import com.servicebase.exception.GuliException;
import com.ucenter.domain.UcenterMember;
import com.ucenter.domain.vo.LoginVo;
import com.ucenter.domain.vo.RegisterVo;
import com.ucenter.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/10 21:39
 * @Version: 11
 */
@Api(tags = "登陆，注册")
@RestController
@RequestMapping("/ucenterService")
public class MemberApiController {

    @Resource
    private UcenterMemberService ucenterService;

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public ResultMessage login(@RequestBody LoginVo loginVo) {
        String token = ucenterService.login(loginVo);
        return ResultMessage.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public ResultMessage register(@RequestBody RegisterVo registerVo){
        try {
            ucenterService.register(registerVo);
            return ResultMessage.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.error();
        }

    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("auth/getLoginInfo")
    public ResultMessage getLoginInfo(HttpServletRequest request){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            UcenterMember loginMember = ucenterService.getLoginInfo(memberId);
            return ResultMessage.ok().data("item", loginMember);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"error");
        }
    }

    @ApiOperation(value = "根据token字符串获取用户信息")
    @PostMapping("getInfoUc/{id}")
    public UcenterMemberPay getInfo(@PathVariable String id) {
        //根据用户id获取用户信息
        UcenterMember ucenterMember = ucenterService.getById(id);
        UcenterMemberPay memeber = new UcenterMemberPay();
        BeanUtils.copyProperties(ucenterMember,memeber);
        return memeber;
    }

    @ApiOperation(value = "统计某一天的注册人数")
    @GetMapping(value = "countregister/{day}")
    public ResultMessage registerCount(
            @PathVariable String day){
        Integer count = ucenterService.countRegisterByDay(day);
        return ResultMessage.ok().data("countRegister", count);
    }


}

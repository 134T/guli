package com.generator.controller;

import com.commonutils.ResultMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/8/12 9:31
 * @Version: 11
 */
@RestController
@RequestMapping("/eduService/user")
public class EduLoginController {

    @PostMapping("login")
    public ResultMessage login(){
        return ResultMessage.ok().data("token", "admin");
    }

    @RequestMapping("info")
    public ResultMessage info(){
        return ResultMessage.ok().data("roles", "[admin]").data("name", "admin")
                .data("avatar", "http://ww4.sinaimg.cn/large/610dc034gw1fac4t2zhwsj20sg0izahf.jpg");
    }
}

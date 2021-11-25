package com.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.commonutils.JwtUtils;
import com.commonutils.ResultMessage;
import com.order.domain.Order;
import com.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author asus
 * @since 2021-11-17
 */
@Api(tags = "订单")
@RestController
@RequestMapping("/orderService/order")
public class OrderController {
    @Resource
    private OrderService orderService;


    @ApiOperation(value = "根据课程id和用户id创建订单，返回订单id")
    @PostMapping("createOrder/{courseId}")
    public ResultMessage save(@PathVariable String courseId, HttpServletRequest request) {
        String orderId = orderService.saveOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return ResultMessage.ok().data("orderId", orderId);
    }

    @ApiOperation(value = "根据id获取订单信息")
    @GetMapping("getOrder/{orderId}")
    public ResultMessage get(@PathVariable String orderId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        Order order = orderService.getOne(wrapper);
        return ResultMessage.ok().data("item", order);
    }

    @ApiOperation(value = "根据用户id和课程id查询订单信息")
    @GetMapping("isBuyCourse/{memberid}/{id}")
    public boolean isBuyCourse(@PathVariable String memberid,
                               @PathVariable String id) {
        //订单状态是1表示支付成功
        int count = orderService.count(new QueryWrapper<Order>().eq("member_id", memberid).eq("course_id", id).eq("status", 1));
        if(count>0) {
            return true;
        } else {
            return false;
        }
    }
}


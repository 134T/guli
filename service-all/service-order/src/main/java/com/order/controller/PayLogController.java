package com.order.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.commonutils.ResultMessage;
import com.order.service.PayLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author asus
 * @since 2021-11-17
 */
@Api(tags = "支付日志表")
@RestController
@RequestMapping("/orderService/log")
public class PayLogController {
    @Resource
    private PayLogService payService;

    @ApiOperation("生成二维码")
    @GetMapping("/createNative/{orderNo}")
    public ResultMessage createNative(@PathVariable String orderNo) {
        Map map = payService.createNative(orderNo);
        return ResultMessage.ok().data(map);
    }

    @ApiOperation("支付状态")
    @GetMapping("/queryPayStatus/{orderNo}")
    public ResultMessage queryPayStatus(@PathVariable String orderNo) {
        //调用查询接口
        Map<String, String> map = payService.queryPayStatus(orderNo);
        //出错
        if (map == null) {
            return ResultMessage.error().message("支付出错");
        }
        //如果成功
        if (map.get("trade_state").equals("SUCCESS")) {
            //更改订单状态
            payService.updateOrderStatus(map);
            return ResultMessage.ok().message("支付成功");
        }

        return ResultMessage.ok().code(25000).message("支付中");
    }
}


package com.generator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/18 14:11
 * @Version: 11
 */

@Component
@FeignClient(value = "service-order", fallback = OrderClientImpl.class)
public interface OrderClient {
    //查询订单信息
    @GetMapping("/orderService/order/isBuyCourse/{memberid}/{id}")
    public boolean isBuyCourse(@PathVariable("memberid") String memberid, @PathVariable("id") String id);
}

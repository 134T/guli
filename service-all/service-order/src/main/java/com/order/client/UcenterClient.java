package com.order.client;

import com.commonutils.vo.UcenterMemberPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/18 0:25
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //根据课程id查询课程信息
    @PostMapping("/ucenterService/getInfoUc/{id}")
    UcenterMemberPay getInfo(@PathVariable String id);
}

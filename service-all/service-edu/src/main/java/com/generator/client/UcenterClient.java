package com.generator.client;

import com.commonutils.vo.UcenterMemberPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/17 15:26
 */
@FeignClient(name = "service-ucenter", fallback = UcenterClientImpl.class)
@Component
public interface UcenterClient {
    /**
     * @Description:根据用户id获取用户信息
     **/
    @PostMapping("/ucenterService/getInfoUc/{memberId}")
    UcenterMemberPay getUcenterPay(@PathVariable("memberId") String memberId);
}

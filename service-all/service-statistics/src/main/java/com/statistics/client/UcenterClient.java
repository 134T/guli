package com.statistics.client;

import com.baomidou.mybatisplus.extension.api.R;
import com.commonutils.ResultMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/19 15:35
 * @Version: 11
 */

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    @GetMapping(value = "/ucenterService/countregister/{day}")
    public ResultMessage registerCount(@PathVariable("day") String day);
}

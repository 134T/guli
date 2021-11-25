package com.generator.client;

import com.commonutils.ResultMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/8 20:03
 * @Version: 11
 */
@FeignClient(name = "service-vod", fallback = VodClientImpl.class)
@Component
public interface VodClient {
    @DeleteMapping(value = "/vodService/{videoId}")
    ResultMessage removeVideo(@PathVariable("videoId") String videoId);

    @DeleteMapping(value = "/vodService/delete-batch")
    ResultMessage removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);


}

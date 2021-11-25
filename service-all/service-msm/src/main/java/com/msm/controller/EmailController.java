package com.msm.controller;

import com.commonutils.EmailVo;
import com.commonutils.RandomUtil;
import com.commonutils.ResultMessage;
import com.msm.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/10 10:30
 * @Version: 11
 */
@Api(tags = "邮箱服务")
@RestController
@RequestMapping("/emailService")
public class EmailController {

    @Resource
    private EmailService emailService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @ApiOperation(value = "发送邮箱信息")
    @GetMapping(value = "/send/{qq}")
    public ResultMessage code(@PathVariable String qq) {

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        String code = redisTemplate.opsForValue().get(qq);
        if(!StringUtils.isEmpty(code)) {
            return ResultMessage.ok().message("第一次qq验证码是："+code);
        }


        code = RandomUtil.getFourBitRandom();
        emailService.sendMail(qq, "验证码", code);
        redisTemplate.opsForValue().set(qq, code,5, TimeUnit.MINUTES);
        return ResultMessage.ok().message("qq验证码是："+code);
    }
}

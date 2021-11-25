package com.vod.util;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/8/16 7:31
 * @Version: 11
 */
@Component
@Data
@ConfigurationProperties("aliyun.vod.file")
public class ConstantPropertiesUtil implements InitializingBean {

    private String accessKeyId;
    private String accessKeySecret;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {

        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
    }
}

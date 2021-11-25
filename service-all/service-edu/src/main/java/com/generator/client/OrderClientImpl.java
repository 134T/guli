package com.generator.client;

import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/18 14:12
 * @Version: 11
 */
@Component
public class OrderClientImpl implements OrderClient {
    @Override
    public boolean isBuyCourse(String memberid, String id) {
        return false;
    }
}

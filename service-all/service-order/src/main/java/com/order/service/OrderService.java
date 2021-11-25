package com.order.service;

import com.order.domain.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author asus
 * @since 2021-11-17
 */
public interface OrderService extends IService<Order> {
    public String saveOrder(String courseId, String memberId);

}

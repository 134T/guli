package com.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.commonutils.vo.CourseInfoCommon;
import com.commonutils.vo.UcenterMemberPay;
import com.order.client.EduClient;
import com.order.client.UcenterClient;
import com.order.domain.Order;
import com.order.mapper.OrderMapper;
import com.order.service.OrderService;
import com.order.util.OrderNoUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author asus
 * @since 2021-11-17
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private EduClient eduClient;

    @Resource
    private UcenterClient ucenterClient;

    /**
     * @Description:创建订单
     **/
    @Override
    public String saveOrder(String courseId, String memberId) {
        //远程调用课程服务，根据课程id获取课程信息
        CourseInfoCommon courseDto = eduClient.getCourseInfoDto(courseId);

        //远程调用用户服务，根据用户id获取用户信息
        UcenterMemberPay ucenterMember = ucenterClient.getInfo(memberId);

        //创建订单
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseDto.getTitle());
        order.setCourseCover(courseDto.getCover());
        order.setTeacherName("test");
        order.setTotalFee(courseDto.getPrice());
        order.setMemberId(memberId);
        order.setMobile(ucenterMember.getMobile());
        order.setNickname(ucenterMember.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}

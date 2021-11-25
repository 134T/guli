package com.order.client;

import com.commonutils.vo.CourseInfoCommon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/18 0:13
 */
@Component
@FeignClient("service-edu")
public interface EduClient {

    /**
     * @Description:根据课程id查询课程信息
     **/
    @GetMapping("/eduService/course/getDto/{courseId}")
    CourseInfoCommon getCourseInfoDto(@PathVariable("courseId") String courseId);
}

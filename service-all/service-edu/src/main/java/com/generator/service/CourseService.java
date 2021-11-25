package com.generator.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.generator.domain.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.generator.domain.course.CourseInfoForm;
import com.generator.domain.course.CourseQuery;
import com.generator.domain.front.CourseQueryVo;
import com.generator.domain.front.CourseWebVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author asus
 * @since 2021-11-03
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoFormById(String id);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    void publishCourseById(String id);

    void pageQuery(Page<Course> pageParam, CourseQuery courseQuery);

    boolean removeCourseById(String id);


    List<Course> selectByTeacherId(String id);

    Map<String, Object> pageListWeb(Page<Course> pageParam, CourseQueryVo courseQuery);

}

package com.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.generator.domain.CourseDescription;
import com.generator.mapper.CourseDescriptionMapper;
import com.generator.service.CourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author asus
 * @since 2021-11-03
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {

//    @Override
//    public void removeByCourseId(String courseId) {
//        QueryWrapper<CourseDescription> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("course_id", courseId);
//        baseMapper.delete(queryWrapper);
//    }
}

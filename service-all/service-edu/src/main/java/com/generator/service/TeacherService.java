package com.generator.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.generator.domain.EduTeacher;
import com.generator.domain.TeacherQuery;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/8/8 13:14
 */
public interface TeacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);
}

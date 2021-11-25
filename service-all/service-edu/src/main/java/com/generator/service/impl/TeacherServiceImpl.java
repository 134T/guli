package com.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.generator.mapper.EduTeacherMapper;
import com.generator.service.TeacherService;
import com.generator.domain.EduTeacher;
import com.generator.domain.TeacherQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/8/8 13:17
 * @Version: 11
 */
@Service
public class TeacherServiceImpl  extends ServiceImpl<EduTeacherMapper, EduTeacher> implements TeacherService {


    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<EduTeacher> eduTeacherQueryWrapper = new QueryWrapper<>();
        eduTeacherQueryWrapper.orderByDesc("sort");

        if (teacherQuery == null){
            baseMapper.selectPage(pageParam, eduTeacherQueryWrapper);
            return ;
        }
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)){
            eduTeacherQueryWrapper.like("name", name);
        }
        if (level != null ){

            eduTeacherQueryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)){
            eduTeacherQueryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)){
            eduTeacherQueryWrapper.le("gmt_modified", end);
        }

        eduTeacherQueryWrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(pageParam, eduTeacherQueryWrapper);

    }
}

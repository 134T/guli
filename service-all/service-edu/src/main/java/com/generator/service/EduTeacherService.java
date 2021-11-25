package com.generator.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.generator.domain.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 *
 * @author asus
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> pageListWeb(Page<EduTeacher> pageParam);
}

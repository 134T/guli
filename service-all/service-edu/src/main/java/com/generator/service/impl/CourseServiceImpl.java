package com.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.generator.domain.Course;
import com.generator.domain.CourseDescription;
import com.generator.domain.Video;
import com.generator.domain.course.CourseInfoForm;
import com.generator.domain.course.CourseQuery;
import com.generator.domain.front.CourseQueryVo;
import com.generator.domain.front.CourseWebVo;
import com.generator.mapper.CourseMapper;
import com.generator.service.ChapterService;
import com.generator.service.CourseDescriptionService;
import com.generator.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.generator.service.VideoService;
import com.servicebase.exception.GuliException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author asus
 * @since 2021-11-03
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseDescriptionService courseDescriptionService;
    @Resource
    private VideoService videoService;
    @Resource
    private ChapterService chapterService;

    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        //保存课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm, course);
        boolean resultCourseInfo = this.save(course);
        if(!resultCourseInfo){
            throw new GuliException(20001, "课程信息保存失败");
        }

        //保存课程详情信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        boolean resultDescription = courseDescriptionService.save(courseDescription);
        if(!resultDescription){
            throw new GuliException(20001, "课程详情信息保存失败");
        }

        return course.getId();
    }

    @Override
    public CourseInfoForm getCourseInfoFormById(String id) {
        Course course = this.getById(id);
        if(course == null){
            throw new GuliException(20001, "数据不存在");
        }
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course, courseInfoForm);

        CourseDescription courseDescription = courseDescriptionService.getById(id);
        if (courseDescription == null){
            courseInfoForm.setDescription("");
        }else {

            courseInfoForm.setDescription(courseDescription.getDescription());
        }

        return courseInfoForm;
    }

    @Override
    public void updateCourseInfoById(CourseInfoForm courseInfoForm) {
        //保存课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm, course);
        boolean resultCourseInfo = this.updateById(course);
        if(!resultCourseInfo){
            throw new GuliException(20001, "课程信息保存失败");
        }

        //保存课程详情信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        CourseDescription descriptionServiceById = courseDescriptionService.getById(course.getId());
        if (descriptionServiceById == null){
            courseDescriptionService.save(courseDescription);
        }else {
            courseDescriptionService.updateById(courseDescription);
        }
//        boolean resultDescription = courseDescriptionService.updateById(courseDescription);
//        if(!resultDescription){
//            throw new GuliException(20001, "课程详情信息保存失败");
//        }
    }

    @Override
    public void publishCourseById(String id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(Course.COURSE_NORMAL);
        baseMapper.updateById(course);
    }

    @Override
    public void pageQuery(Page<Course> pageParam, CourseQuery courseQuery) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");

        if (courseQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(teacherId) ) {
            queryWrapper.eq("teacher_id", teacherId);
        }

        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.eq("subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.eq("subject_id", subjectId);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public boolean removeCourseById(String courseId) {
        //根据courseId删除所有视频
        videoService.removeByCourseId(courseId);

        //根据courseId删除所有章节
        chapterService.removeByCourseId(courseId);

        //根据courseId删除描述
        courseDescriptionService.removeById(courseId);

        //删除封面 TODO 独立完成

        int result = baseMapper.deleteById(courseId);

        return result > 0;

    }

    /**
     * 根据讲师id查询当前讲师的课程列表
     * @param teacherId
     * @return
     */
    @Override
    public List<Course> selectByTeacherId(String teacherId) {

        QueryWrapper<Course> queryWrapper = new QueryWrapper<Course>();

        queryWrapper.eq("teacher_id", teacherId);
        //按照最后更新时间倒序排列
        queryWrapper.orderByDesc("gmt_modified");

        List<Course> courses = baseMapper.selectList(queryWrapper);
        return courses;
    }

    @Override
    public Map<String, Object> pageListWeb(Page<Course> pageParam, CourseQueryVo courseQuery) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseQuery.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", courseQuery.getSubjectParentId());
        }

        if (!StringUtils.isEmpty(courseQuery.getSubjectId())) {
            queryWrapper.eq("subject_id", courseQuery.getSubjectId());
        }

        if (!StringUtils.isEmpty(courseQuery.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }

        if (!StringUtils.isEmpty(courseQuery.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(courseQuery.getPriceSort())) {
            queryWrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageParam, queryWrapper);

        List<Course> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }



}

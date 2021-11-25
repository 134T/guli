package com.generator.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.commonutils.JwtUtils;
import com.commonutils.ResultMessage;
import com.commonutils.vo.CourseInfoCommon;
import com.generator.client.OrderClient;
import com.generator.domain.Course;
import com.generator.domain.chapter.ChapterVo;
import com.generator.domain.course.CourseInfoForm;
import com.generator.domain.course.CoursePublishVo;
import com.generator.domain.course.CourseQuery;
import com.generator.mapper.CourseMapper;
import com.generator.service.ChapterService;
import com.generator.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author asus
 * @since 2021-11-03
 */
@Api(tags="课程管理")
@RestController
@RequestMapping("/eduService/course")
public class CourseController {

    @Resource
    private CourseService courseService;
    @Resource
    private CourseMapper courseMapper;


    @ApiOperation(value = "新增课程")
    @PostMapping("save-course-info")
    public ResultMessage saveCourseInfo(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm){

        String courseId = courseService.saveCourseInfo(courseInfoForm);
        if(StringUtils.hasText(courseId)){
            return ResultMessage.ok().data("courseId", courseId).message("添加课程成功！");
        }else{
            return ResultMessage.error().message("添加课程失败！");
        }
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("course-info/{id}")
    public ResultMessage getById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        CourseInfoForm courseInfoForm = courseService.getCourseInfoFormById(id);
        return ResultMessage.ok().data("item", courseInfoForm);
    }

    @ApiOperation(value = "nacos调用根据课程id查询课程信息")
    @GetMapping("getDto/{courseId}")
    public CourseInfoCommon getCourseInfoDto(@PathVariable String courseId) {
        CourseInfoForm courseInfoForm = courseService.getCourseInfoFormById(courseId);
        CourseInfoCommon courseInfo = new CourseInfoCommon();
        BeanUtils.copyProperties(courseInfoForm,courseInfo);
        return courseInfo;
    }


    @ApiOperation(value = "更新课程")
    @PutMapping("update-course-info/{id}")
    public ResultMessage updateCourseInfoById(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm,

            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        courseService.updateCourseInfoById(courseInfoForm);
        return ResultMessage.ok().data("courseId",id);
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("course-publish-info/{id}")
    public ResultMessage getCoursePublishVoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        CoursePublishVo courseInfoForm = courseMapper.selectCoursePublishVoById(id);
        return ResultMessage.ok().data("item", courseInfoForm);
    }

    @ApiOperation(value = "根据id发布课程")
    @PutMapping("publish-course/{id}")
    public ResultMessage publishCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        courseService.publishCourseById(id);
        return ResultMessage.ok();
    }

    @ApiOperation(value = "分页课程列表")
    @GetMapping("{page}/{limit}")
    public ResultMessage pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    CourseQuery courseQuery){

        Page<Course> pageParam = new Page<>(page, limit);

        courseService.pageQuery(pageParam, courseQuery);
        List<Course> records = pageParam.getRecords();

        long total = pageParam.getTotal();

        return  ResultMessage.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("{id}")
    public ResultMessage removeById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        boolean result = courseService.removeCourseById(id);
        if(result){
            return ResultMessage.ok();
        }else{
            return ResultMessage.error().message("删除失败");
        }
    }
}


package com.generator.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.commonutils.JwtUtils;
import com.commonutils.ResultMessage;
import com.generator.client.OrderClient;
import com.generator.domain.Course;
import com.generator.domain.EduTeacher;
import com.generator.domain.chapter.ChapterVo;
import com.generator.domain.course.CourseInfoForm;
import com.generator.domain.front.CourseQueryVo;
import com.generator.domain.front.CourseWebVo;
import com.generator.mapper.CourseMapper;
import com.generator.service.ChapterService;
import com.generator.service.CourseService;
import com.generator.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/10 0:12
 * @Version: 11
 */
@Api(tags="前台课程名师")
@RestController
@RequestMapping("/eduService/front")
public class IndexController {

    @Resource
    private CourseService courseService;
    @Resource
    private EduTeacherService teacherService;
    @Resource
    private ChapterService chapterService;
    @Resource
    private CourseMapper courseMapper;

    @Resource
    private OrderClient orderClient;

    /**
     * @Description:查询前8条热门课程，查询前4条名师
     **/
    @GetMapping("index")
    public ResultMessage index() {
        //查询前8条热门课程
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<Course> courseList = courseService.list(wrapper);

        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);

        return ResultMessage.ok().data("courseList",courseList).data("teacherList",teacherList);
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping(value = "teacher/{page}/{limit}")
    public ResultMessage pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<EduTeacher> pageParam = new Page<>(page, limit);

        Map<String, Object> map = teacherService.pageListWeb(pageParam);

        return  ResultMessage.ok().data(map);
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping(value = "teacher/{id}")
    public ResultMessage getByTeacherId(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        //查询讲师信息
        EduTeacher teacher = teacherService.getById(id);

        //根据讲师id查询这个讲师的课程列表
        List<Course> courseList = courseService.selectByTeacherId(id);

        return ResultMessage.ok().data("teacher", teacher).data("courseList", courseList);
    }

    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "course/{page}/{limit}")
    public ResultMessage pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseQueryVo courseQuery){
        Page<Course> pageParam = new Page<Course>(page, limit);
        Map<String, Object> map = courseService.pageListWeb(pageParam, courseQuery);
        return  ResultMessage.ok().data(map);
    }


    @ApiOperation(value = "根据ID查询课程")
    @GetMapping(value = "course/{courseId}")
    public ResultMessage getByCourseId(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId, HttpServletRequest request){

        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (!StringUtils.hasText(memberId)){
            return ResultMessage.error().code(1996);
        }
        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = courseMapper.selectInfoWebById(courseId);

        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);

        //远程调用，判断课程是否被购买
        boolean buyCourse = orderClient.isBuyCourse(memberId, courseId);

        return ResultMessage.ok().data("course", courseWebVo).data("chapterVoList", chapterVoList).data("isbuyCourse",buyCourse);
    }



//    //根据课程id查询课程信息
//    @GetMapping("getDto/{courseId}")
//    public CourseInfoForm getCourseInfoDto(@PathVariable String courseId) {
//        CourseInfoForm courseInfoForm = courseService.getCourseInfo(courseId);
//        CourseInfoForm courseInfo = new CourseInfoForm();
//        BeanUtils.copyProperties(courseInfoForm,courseInfo);
//        return courseInfo;
//    }
}

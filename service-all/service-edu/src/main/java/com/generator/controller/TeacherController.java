package com.generator.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.commonutils.ResultMessage;
import com.generator.domain.TeacherQuery;
import com.generator.service.EduTeacherService;
import com.generator.service.TeacherService;
import com.generator.domain.EduTeacher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/8/7 17:46
 * @Version: 11
 */
@Api(tags="讲师管理")
@RestController
@RequestMapping("/eduService")
public class TeacherController {

    @Resource
    private EduTeacherService eduTeacherService;
    @Resource
    private TeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("getAll")
    public ResultMessage getAll(){

        List<EduTeacher> list = eduTeacherService.list(null);
        return ResultMessage.ok().data("items", list);
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("deleteId/{id}")
    public ResultMessage deleteById(@ApiParam(name = "id", value = "讲师ID", required = true)
                                  @PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        if (b){
            return ResultMessage.ok();
        } else {
            return ResultMessage.error();
        }
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("page")
    public ResultMessage pageList(@ApiParam(name="page", value = "当前页码", required = true) @RequestParam("page")Integer page,
                                  @ApiParam(name="limit", value = "每页记录数", required = true) @RequestParam("limit")Integer limit){


        Page<EduTeacher> eduTeacherPage = new Page<>(page, limit);
        eduTeacherService.page(eduTeacherPage);
        List<EduTeacher> records = eduTeacherPage.getRecords();
        long total = eduTeacherPage.getTotal();
        return ResultMessage.ok().data("total", total).data("rows", records);

    }

    @ApiOperation(value = "分页讲师列表+条件查询")
    @PostMapping("{page}/{limit}")
    public ResultMessage pageQuery(
    @ApiParam(name = "page", value = "当前页码", required = true)
    @PathVariable Long page,
    @ApiParam(name = "limit", value = "每页记录数", required = true)
    @PathVariable Long limit,
    @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
    @RequestBody(required = false) TeacherQuery teacherQuery) {

        Page<EduTeacher> pageParam = new Page<>(page, limit);
        teacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return ResultMessage.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("add")
    public ResultMessage save(@ApiParam(name = "teacher", value = "讲师对象", required = true)
                                  @RequestBody EduTeacher teacher){
        boolean save = eduTeacherService.save(teacher);
        if (save){
            return ResultMessage.ok();
        }else {
            return ResultMessage.error();
        }
    }

    @ApiOperation(value = "根据ID查询讲师")
    @PostMapping("selectById/{id}")
    public ResultMessage getById(@ApiParam(name = "id", value = "讲师ID", required = true)
                                     @PathVariable String id){

        EduTeacher byId = eduTeacherService.getById(id);
        if (byId != null){
            return ResultMessage.ok().data("item", byId);
        }else {
            return ResultMessage.error();
        }
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PostMapping("update/{id}")
    public ResultMessage updateById(
//            @ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id,
            @ApiParam(name = "teacher", value = "讲师对象", required = true)@RequestBody EduTeacher teacher){
        boolean b = eduTeacherService.updateById(teacher);
        if (b){
            return ResultMessage.ok();
        }else {
            return ResultMessage.error();
        }
    }

}

package com.generator.controller;

import com.commonutils.ResultMessage;
import com.generator.domain.subject.SubjectNestedVo;
import com.generator.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/8/20 14:52
 * @Version: 11
 */
@Api(tags="课程分类管理")
@RestController
@RequestMapping("/eduService")
public class ExcelController {

    @Resource
    private EduSubjectService eduSubjectService;

    @ApiOperation(value = "Excel批量导入")
    @PostMapping("/excel")
    public ResultMessage excel(@RequestPart("file") MultipartFile file){

        if (file.isEmpty()){
            return ResultMessage.error().message("无法上传空文件！");
        }
        eduSubjectService.importSubjectData(file, eduSubjectService);
        return ResultMessage.ok().message("文件上传成功！");
    }

    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("/classList")
    public ResultMessage nestedList(){

        List<SubjectNestedVo> subjectNestedVoList = eduSubjectService.nestedList();
        return ResultMessage.ok().data("items", subjectNestedVoList);
    }
}

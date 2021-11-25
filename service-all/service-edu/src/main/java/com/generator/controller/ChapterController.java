package com.generator.controller;


import com.commonutils.ResultMessage;
import com.generator.domain.Chapter;
import com.generator.domain.chapter.ChapterVo;
import com.generator.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author asus
 * @since 2021-11-03
 */
@Api(tags = "课程")
@RestController
@RequestMapping("/eduService/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @ApiOperation(value = "嵌套章节数据列表")
    @GetMapping("nested-list/{courseId}")
    public ResultMessage nestedListByCourseId(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){

        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);
        return ResultMessage.ok().data("items", chapterVoList);
    }

    @ApiOperation(value = "新增章节")
    @PostMapping
    public ResultMessage save(
            @ApiParam(name = "chapterVo", value = "章节对象", required = true)
            @RequestBody Chapter chapter){

        chapterService.save(chapter);
        return ResultMessage.ok();
    }

    @ApiOperation(value = "根据ID查询章节")
    @GetMapping("{id}")
    public ResultMessage getById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id){

        Chapter chapter = chapterService.getById(id);
        return ResultMessage.ok().data("item", chapter);
    }

    @ApiOperation(value = "根据ID修改章节")
    @PutMapping("{id}")
    public ResultMessage updateById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id,

            @ApiParam(name = "chapter", value = "章节对象", required = true)
            @RequestBody Chapter chapter){

        chapter.setId(id);
        chapterService.updateById(chapter);
        return ResultMessage.ok();
    }

    @ApiOperation(value = "根据ID删除章节")
    @DeleteMapping("{id}")
    public ResultMessage removeById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id){

        boolean result = chapterService.removeChapterById(id);
        if(result){
            return ResultMessage.ok();
        }else{
            return ResultMessage.error().message("删除失败");
        }
    }
}


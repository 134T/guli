package com.generator.controller;


import com.commonutils.ResultMessage;
import com.generator.domain.chapter.VideoInfoForm;
import com.generator.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author asus
 * @since 2021-11-03
 */
@Api(tags="课程视频 ")
@RestController
@RequestMapping("/eduService/video")
public class VideoController {

    @Resource
    private VideoService videoService;

    @ApiOperation(value = "新增课时")
    @PostMapping("save-video-info")
    public ResultMessage save(
            @ApiParam(name = "videoForm", value = "课时对象", required = true)
            @RequestBody VideoInfoForm videoInfoForm){

        videoService.saveVideoInfo(videoInfoForm);
        return ResultMessage.ok();
    }

    @ApiOperation(value = "根据ID查询课时")
    @GetMapping("video-info/{id}")
    public ResultMessage getVideInfoById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){

        VideoInfoForm videoInfoForm = videoService.getVideoInfoFormById(id);
        return ResultMessage.ok().data("item", videoInfoForm);
    }

    @ApiOperation(value = "更新课时")
    @PutMapping("update-video-info/{id}")
    public ResultMessage updateCourseInfoById(
            @ApiParam(name = "VideoInfoForm", value = "课时基本信息", required = true)
            @RequestBody VideoInfoForm videoInfoForm,

            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){

        videoService.updateVideoInfoById(videoInfoForm);
        return ResultMessage.ok();
    }

    @ApiOperation(value = "根据ID删除课时")
    @DeleteMapping("{id}")
    public ResultMessage removeById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){

        boolean result = videoService.removeVideoById(id);
        if(result){
            return ResultMessage.ok();
        }else{
            return ResultMessage.error().message("删除失败");
        }
    }
}


package com.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.baomidou.mybatisplus.extension.api.R;
import com.commonutils.ResultMessage;
import com.vod.service.VideoService;
import com.vod.util.AliyunVodSdkUtils;
import com.vod.util.ConstantPropertiesUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/7 13:25
 * @Version: 11
 */
@Api(tags="阿里云视频点播微服务")
@RestController
@RequestMapping("/vodService")
public class VodController {
    @Resource
    private VideoService videoService;

    @PostMapping("upload")
    public ResultMessage uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestPart("file") MultipartFile file)  {

        String videoId = videoService.uploadVideo(file);
        System.out.println("=========================="+videoId+"==================");
        return ResultMessage.ok().message("视频上传成功").data("videoId", videoId);
    }

    @DeleteMapping("{videoId}")
    public ResultMessage removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                         @PathVariable String videoId){

        videoService.removeVideo(videoId);

        return ResultMessage.ok().message("视频删除成功");
    }

    /**
     * 批量删除视频
     * @param videoIdList
     * @return
     */
    @DeleteMapping("delete-batch")
    public ResultMessage removeVideoList(
            @ApiParam(name = "videoIdList", value = "云端视频id", required = true)
            @RequestParam("videoIdList") List<String> videoIdList){

        videoService.removeVideoList(videoIdList);
        return ResultMessage.ok().message("视频删除成功");
    }
    /**
     * @Description:获取凭证
     **/
    @GetMapping("get-play-auth/{videoId}")
    public ResultMessage getVideoPlayAuth(@PathVariable("videoId") String videoId) throws Exception {

        //获取阿里云存储相关常量
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;

        //初始化
        DefaultAcsClient client = AliyunVodSdkUtils.initVodClient(accessKeyId, accessKeySecret);

        //请求
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);

        //响应
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);

        //得到播放凭证
        String playAuth = response.getPlayAuth();

        //返回结果
        return ResultMessage.ok().message("获取凭证成功").data("playAuth", playAuth);
    }
}

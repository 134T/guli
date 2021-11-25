package com.oss.controller;

import com.commonutils.ResultMessage;
import com.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/8/16 11:49
 * @Version: 11
 */
@Api(tags="阿里云文件管理")
@RestController
@RequestMapping("/ossService")
public class OssController {

    @Resource
    private FileService fileService;

    @ApiOperation(value = "文件上传")
    @PostMapping( value = "upload")
    public ResultMessage uploadPhoto(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestPart("file") MultipartFile file){

        String uploadUrl = fileService.upload(file);
        return ResultMessage.ok().message("文件上传成功").data("url", uploadUrl);
    }


}


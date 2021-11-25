package com.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/7 13:28
 */
public interface VideoService {

    String uploadVideo(MultipartFile file);

    void removeVideo(String videoId);

    void removeVideoList(List<String> videoIdList);
}

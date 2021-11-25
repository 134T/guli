package com.generator.service;

import com.generator.domain.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.generator.domain.chapter.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author asus
 * @since 2021-11-03
 */
public interface VideoService extends IService<Video> {

    boolean getCountByChapterId(String chapterId);

    void saveVideoInfo(VideoInfoForm videoInfoForm);

    VideoInfoForm getVideoInfoFormById(String id);

    void updateVideoInfoById(VideoInfoForm videoInfoForm);

    boolean removeVideoById(String id);

    void removeByCourseId(String courseId);

    void removeByChapterId(String chapterId);
}

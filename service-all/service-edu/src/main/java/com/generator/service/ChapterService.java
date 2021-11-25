package com.generator.service;

import com.generator.domain.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.generator.domain.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author asus
 * @since 2021-11-03
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> nestedList(String courseId);

    boolean removeChapterById(String id);

    void removeByCourseId(String courseId);
}

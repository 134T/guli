package com.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.generator.domain.Chapter;
import com.generator.domain.Video;
import com.generator.domain.chapter.ChapterVo;
import com.generator.domain.chapter.VideoVo;
import com.generator.mapper.ChapterMapper;
import com.generator.service.ChapterService;
import com.generator.service.VideoService;
import com.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author asus
 * @since 2021-11-03
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Resource
    private VideoService videoService;

    @Override
    public List<ChapterVo> nestedList(String courseId) {

        //最终要的到的数据列表
        ArrayList<ChapterVo> chapterVoArrayList = new ArrayList<>();

        //获取章节信息
        QueryWrapper<Chapter> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("course_id", courseId);
        queryWrapper1.orderByAsc("sort", "id");
        List<Chapter> chapters = baseMapper.selectList(queryWrapper1);

        //获取课时信息
        QueryWrapper<Video> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id", courseId);
        queryWrapper2.orderByAsc("sort", "id");
        List<Video> videos = videoService.list(queryWrapper2);

        //填充章节vo数据
        int count1 = chapters.size();
        for (int i = 0; i < count1; i++) {
            Chapter chapter = chapters.get(i);

            //创建章节vo对象
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVoArrayList.add(chapterVo);

            //填充课时vo数据
            ArrayList<VideoVo> videoVoArrayList = new ArrayList<>();
            int count2 = videos.size();
            for (int j = 0; j < count2; j++) {

                Video video = videos.get(j);
                if(chapter.getId().equals(video.getChapterId())){

                    //创建课时vo对象
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoArrayList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoArrayList);
        }

        return chapterVoArrayList;
    }

    @Override
    public boolean removeChapterById(String id) {
        //根据id查询是否存在视频，如果有则提示用户尚有子节点
//        if(videoService.getCountByChapterId(id)){
//            throw new GuliException(20001,"该分章节下存在视频课程，请先删除视频课程");
//        }

        //根据章节id删除视频
        videoService.removeByChapterId(id);


        int result = baseMapper.deleteById(id);
        return result > 0;
    }

    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        baseMapper.delete(queryWrapper);

    }
}


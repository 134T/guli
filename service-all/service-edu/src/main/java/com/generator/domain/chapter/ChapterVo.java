package com.generator.domain.chapter;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/5 11:47
 * @Version: 11
 */

@ApiModel(value = "章节信息")
@Data
public class ChapterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();
}
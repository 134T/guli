package com.generator.domain.chapter;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/5 11:48
 * @Version: 11
 */

@ApiModel(value = "课时信息")
@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;
    private String videoSourceId;
    private String videoOriginalName;
}
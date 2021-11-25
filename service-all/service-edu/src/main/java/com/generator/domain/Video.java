package com.generator.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 课程视频
 * </p>
 *
 * @author asus
 * @since 2021-11-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("edu_video")
@ApiModel(value="Video对象", description="课程视频")
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "视频ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "课程ID")
    @TableField("course_id")
    private String courseId;

    @ApiModelProperty(value = "章节ID")
    @TableField("chapter_id")
    private String chapterId;

    @ApiModelProperty(value = "节点名称")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "云端视频资源")
    @TableField("video_source_id")
    private String videoSourceId;

    @ApiModelProperty(value = "原始文件名称")
    @TableField("video_original_name")
    private String videoOriginalName;

    @ApiModelProperty(value = "排序字段")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "播放次数")
    @TableField("play_count")
    private Long playCount;

    @ApiModelProperty(value = "是否可以试听：0收费 1免费, fill = FieldFill.INSERT_UPDATE")
    @TableField(value = "is_free")
    private Boolean isFree;

    @ApiModelProperty(value = "视频时长（秒）")
    @TableField("duration")
    private Float duration;

    @ApiModelProperty(value = "Empty未上传 Transcoding转码中  Normal正常")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "视频源文件大小（字节）")
    @TableField("size")
    private Long size;

    @ApiModelProperty(value = "乐观锁")
    @TableField("version")
    private Long version;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}

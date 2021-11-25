package com.statistics.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 网站统计日数据
 * </p>
 *
 * @author asus
 * @since 2021-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("statistics_daily")
@ApiModel(value="StatisticsDaily对象", description="网站统计日数据")
public class StatisticsDaily implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "统计日期")
    @TableField("date_calculated")
    private String dateCalculated;

    @ApiModelProperty(value = "注册人数")
    @TableField("register_num")
    private Integer registerNum;

    @ApiModelProperty(value = "登录人数")
    @TableField("login_num")
    private Integer loginNum;

    @ApiModelProperty(value = "每日播放视频数")
    @TableField("video_view_num")
    private Integer videoViewNum;

    @ApiModelProperty(value = "每日新增课程数")
    @TableField("course_num")
    private Integer courseNum;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}

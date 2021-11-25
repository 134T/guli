package com.generator.domain;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author asus
 * @since 2021-11-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("edu_course")
@ApiModel(value="Course对象", description="课程")
public class Course implements Serializable {

    public static final String COURSE_NORMAL = "Normal";
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "课程讲师ID")
    @TableField("teacher_id")
    private String teacherId;

    @ApiModelProperty(value = "课程专业ID")
    @TableField("subject_id")
    private String subjectId;

    @ApiModelProperty(value = "课程专业父级ID")
    @TableField("subject_parent_id")
    private String subjectParentId;

    @ApiModelProperty(value = "课程标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    @TableField("lesson_num")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    @TableField("cover")
    private String cover;

    @ApiModelProperty(value = "销售数量")
    @TableField("buy_count")
    private Long buyCount;

    @ApiModelProperty(value = "浏览数量")
    @TableField("view_count")
    private Long viewCount;

    @ApiModelProperty(value = "乐观锁")
    @TableField("version")
    private Long version;

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}

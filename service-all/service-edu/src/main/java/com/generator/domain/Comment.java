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
 * 评论
 * </p>
 *
 * @author asus
 * @since 2021-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("edu_comment")
@ApiModel(value="Comment对象", description="评论")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "讲师ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "课程id")
    @TableField("course_id")
    private String courseId;

    @ApiModelProperty(value = "讲师id")
    @TableField("teacher_id")
    private String teacherId;

    @ApiModelProperty(value = "会员id")
    @TableField("member_id")
    private String memberId;

    @ApiModelProperty(value = "会员昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "会员头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "评论内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}

package com.commonutils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/10 19:32
 * @Version: 11
 */
@ApiModel(description = "邮箱实体")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailVo {
    @ApiModelProperty(value = "目标email 地址")
    private String to;
    @ApiModelProperty(value = "邮件主题")
    private String subject;
    @ApiModelProperty(value = "纯文本内容")
    private String text;
}

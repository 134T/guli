package com.servicebase.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/8/8 22:40
 * @Version: 11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "异常信息")
    private String msg;
}

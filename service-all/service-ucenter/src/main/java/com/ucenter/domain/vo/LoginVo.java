package com.ucenter.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/10 21:35
 * @Version: 11
 */
@Data
@ApiModel(value="登录对象", description="登录对象")
public class LoginVo {

    @ApiModelProperty(value = "qq号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;
}

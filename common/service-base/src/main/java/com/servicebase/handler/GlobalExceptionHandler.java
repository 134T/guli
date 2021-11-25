package com.servicebase.handler;

import com.commonutils.ResultMessage;
import com.servicebase.exception.GuliException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/8/8 21:48
 * @Version: 11
 */
@ControllerAdvice
@ApiModel(description = "异常类")
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    @ApiOperation(value = "处理特定异常")
    public ResultMessage error(ArithmeticException e){
        e.printStackTrace();
        return ResultMessage.error().message("执行了处理特定异常");
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public ResultMessage error(GuliException e){
        e.printStackTrace();
        return ResultMessage.error().message(e.getMsg()).code(e.getCode());
    }
}

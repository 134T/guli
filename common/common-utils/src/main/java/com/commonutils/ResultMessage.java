package com.commonutils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/8/8 8:47
 * @Version: 11
 */
@Data
@ApiModel(description = "返回处理结果")
public class ResultMessage {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    private ResultMessage(){
    }

    public static ResultMessage ok(){
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(true);
        resultMessage.setCode(ResultCode.SUCCESS);
        resultMessage.setMessage("成功");
        return resultMessage;

    }

    public static ResultMessage error(){
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setSuccess(false);
        resultMessage.setCode(ResultCode.ERROR);
        resultMessage.setMessage("失败");
        return resultMessage;

    }

    public ResultMessage success(boolean success){
        this.setSuccess(success);
        return this;
    }
    public ResultMessage message(String message){
        this.setMessage(message);
        return this;
    }
    public ResultMessage code(Integer code){
        this.setCode(code);
        return this;
    }
    public ResultMessage data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    public ResultMessage data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}

package com.generator.client;

import com.baomidou.mybatisplus.extension.api.R;
import com.commonutils.ResultMessage;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/9 4:06
 * @Version: 11
 */
@Component
public class VodClientImpl implements VodClient {
    @Override
    public ResultMessage removeVideo(String videoId) {
        return ResultMessage.error().message("删除单个视频失败！");
    }

    @Override
    public ResultMessage removeVideoList(List<String> videoIdList) {
        return ResultMessage.error().message("删除多个视频失败！");

    }
}

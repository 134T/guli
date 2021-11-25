package com.vod.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/7 11:45
 * @Version: 11
 */

public class AliyunVodSdkUtils {

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret)  {
        // 点播服务接入区域
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        return new DefaultAcsClient(profile);
    }
}

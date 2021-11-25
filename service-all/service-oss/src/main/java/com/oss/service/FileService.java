package com.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/8/16 7:42
 * @Version: 11
 */

public interface FileService {

    /**
     * @Description:上传至阿里云
     **/
    String upload(MultipartFile file);
}

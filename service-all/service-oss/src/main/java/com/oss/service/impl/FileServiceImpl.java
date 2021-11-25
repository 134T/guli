package com.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.oss.service.FileService;
import com.oss.util.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/8/16 7:46
 * @Version: 11
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file) {

        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        String fileName = file.getOriginalFilename();

        //在文件名称里添加随机的唯一值
        String s = UUID.randomUUID().toString().replaceAll("-", "");
        fileName = s+fileName;
        //获取日期
        String s1 = new DateTime().toString("yyyy/MM/dd");
        fileName=s1+"/"+fileName;


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try{

            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();
            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();
            return "https://"+bucketName+"."+endpoint+"/"+fileName;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

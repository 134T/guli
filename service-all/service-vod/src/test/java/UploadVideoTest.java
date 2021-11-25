import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/6 23:52
 * @Version: 11
 */

public class UploadVideoTest {

    /**
     * 获取视频上传地址和凭证
     * @param client 发送请求客户端
     * @return CreateUploadVideoResponse 获取视频上传地址和凭证响应数据
     * @throws Exception
     */
    public static CreateUploadVideoResponse createUploadVideo(DefaultAcsClient client) throws Exception {
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        request.setTitle("dfdb69203f2e461688fa3a01a749f913");
        request.setFileName("6 - What If I Want to Move Faster.mp4");


        return client.getAcsResponse(request);
    }

    // 请求示例
    public static void main(String[] argv) {
//        DefaultAcsClient client = AliyunVodSdkUtils.initVodClient("LTAI5tSqKHYAMTap3974FmHH", "PHQoaFaVJWuVaoVTXoyDzIKVwxnkSV");
//        CreateUploadVideoResponse response = new CreateUploadVideoResponse();
//        try {
//            response = createUploadVideo(client);
//            System.out.print("VideoId = " + response.getVideoId() + "\n");
//            System.out.print("UploadAddress = " + response.getUploadAddress() + "\n");
//            System.out.print("UploadAuth = " + response.getUploadAuth() + "\n");
//        } catch (Exception e) {
//            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
//        }
//        System.out.print("RequestId = " + response.getRequestId() + "\n");
        //初始化客户端、请求对象和相应对象
        DefaultAcsClient client = AliyunVodSdkUtils.initVodClient("LTAI5tSqKHYAMTap3974FmHH", "PHQoaFaVJWuVaoVTXoyDzIKVwxnkSV");
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        try {

            //设置请求参数
            //注意：这里只能获取非加密视频的播放地址
            request.setVideoId("dfdb69203f2e461688fa3a01a749f913");
            //获取请求响应
            response = client.getAcsResponse(request);

            //输出请求结果
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");

        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }

        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }
}

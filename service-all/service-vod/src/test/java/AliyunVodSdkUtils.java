import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @Description:
 * @Author: 坚持的力量
 * @Date: 2021/11/6 23:44
 * @Version: 11
 */

public class AliyunVodSdkUtils {

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret){
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        return new DefaultAcsClient(profile);
    }
}

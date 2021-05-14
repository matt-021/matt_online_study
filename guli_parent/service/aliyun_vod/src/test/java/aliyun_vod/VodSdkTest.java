package aliyun_vod;

import java.util.List;

import org.junit.Test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.matt.vod.Utils.AliyunVodSDKUtils;

public class VodSdkTest {
    
    String accessKeyId = "LTAI5tG4W2nBT38Lb1npeHDe";
	String accessKeySecret = "hBZAp2tsYqATYM8mqwuVrowiffUt7N";
	/**
	 * 获取视频播放凭证
	 * @throws ClientException
	 */
@Test
public void testGetVideoPlayAuth() throws ClientException {

    //初始化客户端、请求对象和相应对象
    DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKeyId, accessKeySecret);
    GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
    GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

    try {

        //设置请求参数
        request.setVideoId("59d075c322a64cb6a1521ffe65a96bd3");
        //获取请求响应
        response = client.getAcsResponse(request);

        //输出请求结果
        //播放凭证
        System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
        //VideoMeta信息
        System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
    } catch (Exception e) {
        System.out.print("ErrorMessage = " + e.getLocalizedMessage());
    }

    System.out.print("RequestId = " + response.getRequestId() + "\n");
}
/**
 * 获取视频播放地址
 * @throws ClientException
 */
@Test
public void testGetPlayInfo() throws ClientException {

//初始化客户端、请求对象和相应对象
DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKeyId, accessKeySecret);
GetPlayInfoRequest request = new GetPlayInfoRequest();
GetPlayInfoResponse response = new GetPlayInfoResponse();

try {

    //设置请求参数
    //注意：这里只能获取非加密视频的播放地址
    request.setVideoId("59d075c322a64cb6a1521ffe65a96bd3");
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
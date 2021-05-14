package com.matt.edu.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.matt.commonutils.R;

@FeignClient("service-vod")
@Component
public interface VodClient {
	//删除单个视频
	@DeleteMapping(value = "/vod/video/{videoId}")
	public R removeVideo(@PathVariable("videoId") String videoId);
	//删除多个视频
	@DeleteMapping(value = "/vod/video/delete-batch/{videoId}")
	public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
	@GetMapping("/vod/video/test")
	public R test();
}

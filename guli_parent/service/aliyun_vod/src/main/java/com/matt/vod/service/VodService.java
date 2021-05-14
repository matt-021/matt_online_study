package com.matt.vod.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;



public interface VodService {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);
    //删除阿里云视频
    void removeVideo(String videoId);
	void removeMoreAlyVideo(List<String> videoIdList);
}

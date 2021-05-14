package com.matt.edu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matt.edu.client.VodClient;
import com.matt.edu.entity.Video;
import com.matt.edu.mapper.VideoMapper;
import com.matt.edu.service.VideoService;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author zylin
 * @since 2021-04-27
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
	@Autowired
	private VodClient vodClient;
	
	@Override
	public boolean getCountByChapterId(String chapterId) {
		   QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
		    queryWrapper.eq("chapter_id", chapterId);
		    Integer count = baseMapper.selectCount(queryWrapper);
		    return null != count && count > 0;
	}

	@Override
	public void removeVideoCourseId(String courseId) {
		 //根据课程id查出所有视频id
        QueryWrapper<Video> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        wrapperVideo.select("video_source_id");
        List<Video> eduVideos = baseMapper.selectList(wrapperVideo);
        //List<EduVideo>变成List<String>
        List<String> list = new ArrayList<>();
        for (int i = 0; i < eduVideos.size(); i++) {
            String videoSourceId = eduVideos.get(i).getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)){
                list.add(videoSourceId);
            }
        }
		
		if(list.size()>0){ vodClient.deleteBatch(list); }
		 

        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
	}

}

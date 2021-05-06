package com.matt.edu.service;

import com.matt.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author zylin
 * @since 2021-04-27
 */
public interface VideoService extends IService<Video> {
	boolean getCountByChapterId(String chapterId);
}

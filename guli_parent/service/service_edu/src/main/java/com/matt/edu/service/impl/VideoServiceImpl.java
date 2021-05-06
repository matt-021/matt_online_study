package com.matt.edu.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

	@Override
	public boolean getCountByChapterId(String chapterId) {
		   QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
		    queryWrapper.eq("chapter_id", chapterId);
		    Integer count = baseMapper.selectCount(queryWrapper);
		    return null != count && count > 0;
	}

}

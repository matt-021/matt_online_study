package com.matt.edu.service.impl;

import com.matt.edu.entity.Video;
import com.matt.edu.mapper.VideoMapper;
import com.matt.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

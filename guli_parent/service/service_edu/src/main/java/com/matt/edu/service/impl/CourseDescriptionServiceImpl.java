package com.matt.edu.service.impl;

import com.matt.edu.entity.CourseDescription;
import com.matt.edu.mapper.CourseDescriptionMapper;
import com.matt.edu.service.CourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author zylin
 * @since 2021-04-27
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {

}

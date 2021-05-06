package com.matt.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matt.edu.entity.Course;
import com.matt.edu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author zylin
 * @since 2021-04-27
 */
public interface CourseMapper extends BaseMapper<Course> {
	CoursePublishVo getCoursePublishVoById(String id);
}

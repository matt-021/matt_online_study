package com.matt.edu.service;

import com.matt.edu.entity.Course;
import com.matt.edu.entity.course.CourseInfoForm;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zylin
 * @since 2021-04-27
 */
public interface CourseService extends IService<Course> {
	
	
	/**
	 * 保存课程和课程详情信息
	 * @param courseInfoForm
	 * @return 新生成的课程id
	 */
	String saveCourseInfo(CourseInfoForm courseInfoForm);

	CourseInfoForm getCourseInfoFormById(String id);

}

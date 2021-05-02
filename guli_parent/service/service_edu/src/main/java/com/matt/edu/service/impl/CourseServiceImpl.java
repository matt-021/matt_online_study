package com.matt.edu.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.matt.edu.entity.Course;
import com.matt.edu.entity.CourseDescription;
import com.matt.edu.entity.course.CourseInfoForm;
import com.matt.edu.mapper.CourseMapper;
import com.matt.edu.service.CourseDescriptionService;
import com.matt.edu.service.CourseService;
import com.matt.service_base.exception.MattException;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zylin
 * @since 2021-04-27
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

	@Autowired
	private CourseDescriptionService courseDescriptionService;

	@Override
	public String saveCourseInfo(CourseInfoForm courseInfoForm) {

	    //保存课程基本信息
	    Course course = new Course();
	    course.setStatus(Course.COURSE_DRAFT);
	    BeanUtils.copyProperties(courseInfoForm, course);
	    boolean resultCourseInfo = this.save(course);
	    if(!resultCourseInfo){
	        throw new MattException(20001, "课程信息保存失败");
	    }

	    //保存课程详情信息
	    CourseDescription courseDescription = new CourseDescription();
	    courseDescription.setDescription(courseInfoForm.getDescription());
	    courseDescription.setId(course.getId());
	    boolean resultDescription = courseDescriptionService.save(courseDescription);
	    if(!resultDescription){
	        throw new MattException(20001, "课程详情信息保存失败");
	    }

	    return course.getId();
	}

	@Override
	public CourseInfoForm getCourseInfoFormById(String id) {
		  Course course = this.getById(id);
		    if(course == null){
		        throw new MattException(20001, "数据不存在");
		    }
		    CourseInfoForm courseInfoForm = new CourseInfoForm();
		    BeanUtils.copyProperties(course, courseInfoForm);

		    CourseDescription courseDescription = courseDescriptionService.getById(id);
		    if(course != null){
		        courseInfoForm.setDescription(courseDescription.getDescription());
		    }

		    return courseInfoForm;
	}

	@Override
	public void updateCourseInfo(CourseInfoForm courseInfoVo) {
		 //1 修改课程表
        Course eduCourse = new Course();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if(update == 0) {
            throw new MattException(20001,"修改课程信息失败");
        }

        //2 修改描述表
        CourseDescription description = new CourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);
	}

}

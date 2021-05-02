package com.matt.edu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.excel.util.StringUtils;
import com.matt.commonutils.R;
import com.matt.edu.entity.course.CourseInfoForm;
import com.matt.edu.service.CourseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author zylin
 * @since 2021-04-27
 */
@Api(description = "课程管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/edu/course")
public class CourseController {
	@Autowired
	private CourseService courseService;

	@ApiOperation(value = "新增课程")
	@PostMapping("saveCourseInfo")
	public R saveCourseInfo(
			@ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
			@RequestBody CourseInfoForm courseInfoForm){

		String courseId = courseService.saveCourseInfo(courseInfoForm);
		if(!StringUtils.isEmpty(courseId)){
			return R.ok().data("courseId", courseId);
		}else{
			return R.error().message("保存失败");
		}
	}
	
	@ApiOperation(value = "根据ID查询课程")
	@GetMapping("course-info/{id}")
	public R getById(
	    @ApiParam(name = "id", value = "课程ID", required = true)
	    @PathVariable String id){

	    CourseInfoForm courseInfoForm = courseService.getCourseInfoFormById(id);
	    return R.ok().data("item", courseInfoForm);
	}

}


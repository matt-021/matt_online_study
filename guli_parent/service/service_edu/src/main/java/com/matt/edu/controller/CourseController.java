package com.matt.edu.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matt.commonutils.R;
import com.matt.edu.entity.Course;
import com.matt.edu.entity.course.CourseInfoForm;
import com.matt.edu.entity.course.CourseQuery;
import com.matt.edu.entity.vo.CoursePublishVo;
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
	 //修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoForm courseInfoVo) {
    	  courseService.updateCourseInfo(courseInfoVo);
          return R.ok();
    }
    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("course-publish-info/{id}")
    public R getCoursePublishVoById(
        @ApiParam(name = "id", value = "课程ID", required = true)
        @PathVariable String id){

        CoursePublishVo courseInfoForm = courseService.getCoursePublishVoById(id);
        return R.ok().data("publishCourse", courseInfoForm);
    }
    //课程最终发布
    //修改课程状态
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        Course eduCourse = new Course();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");//设置课程发布状态
        courseService.updateById(eduCourse);
        return R.ok();
    }
    //课程列表
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public R pageCourseCondition(@PathVariable long current, @PathVariable long limit,
                                 @RequestBody(required = false)CourseQuery courseQuery) {

        Page<Course> page = new Page<>(current,limit);
        QueryWrapper<Course> wrapper = new QueryWrapper();

        if(!StringUtils.isEmpty(courseQuery.getTitle())) {
            wrapper.like("title",courseQuery.getTitle());
        }

        if(!StringUtils.isEmpty(courseQuery.getStatus())) {
            wrapper.eq("status",courseQuery.getStatus());
        }

        if(!StringUtils.isEmpty(courseQuery.getBegin())) {
            wrapper.ge("gmt_create",courseQuery.getBegin());
        }

        if(!StringUtils.isEmpty(courseQuery.getEnd())) {
            wrapper.le("gmt_modified",courseQuery.getEnd());
        }

        this.courseService.page(page,wrapper);
        long total = page.getTotal(); //总记录数
        List<Course> records = page.getRecords();  //list集合

        return R.ok().data("total",total).data("rows",records);
    }
    
    //删除课程
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        this.courseService.removeCourse(courseId);
        return R.ok();
    }



}


package com.matt.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.matt.commonutils.R;
import com.matt.edu.entity.Course;
import com.matt.edu.entity.Teacher;
import com.matt.edu.service.CourseService;
import com.matt.edu.service.TeacherService;
import io.swagger.annotations.Api;

/**
 * @author 86180
 * 用户前台首页 获取 热门课程和教师数据
 */
@Api(description = "用户前台首页")
@RestController
@RequestMapping("/edu/index")
@CrossOrigin
public class IndexController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;

   //查询前8条热门课程，查询前4条名师
    @GetMapping("index")
    public R index() {
        //查询前8条热门课程
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<Course> eduList = courseService.list(wrapper);

        //查询前4条名师
        QueryWrapper<Teacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<Teacher> teacherList = teacherService.list(wrapperTeacher);

        return R.ok().data("eduList",eduList).data("teacherList",teacherList);
    }
}
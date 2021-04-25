package com.matt.edu.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.matt.commonutils.R;
import com.matt.edu.entity.subject.OneSubject;
import com.matt.edu.service.SubjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author zylin
 * @since 2021-04-25
 */
@Api(description="课程分类")
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
public class SubjectController {
	@Autowired
	private SubjectService subjectService;
	
	@ApiOperation("新增课程分类")
	@PostMapping("addSubject")
	public R addSubject(@ApiParam("cxcel文件")MultipartFile file) {
		subjectService.saveSubject(file);
		return R.ok();
	}
	//课程分类列表（树形）
    @GetMapping("getAllSubject")
    public R getAllSubject() {
        //list集合泛型是一级分类
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }

}


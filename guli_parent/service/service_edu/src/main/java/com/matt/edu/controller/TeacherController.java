package com.matt.edu.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matt.commonutils.R;
import com.matt.edu.entity.Teacher;
import com.matt.edu.entity.vo.TeacherQuery;
import com.matt.edu.service.TeacherService;
import com.matt.service_base.exception.MattException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zylin
 * @since 2021-03-26
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/edu/teacher")
@CrossOrigin
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	//查询所有讲师信息
	@ApiOperation(value = "所有讲师列表")
	@GetMapping("findAll")
	public R teacherList(){
		try {
			int a = 10/0;
		} catch (Exception e) {
			throw new MattException(250,"自定义异常");
		}
		
		List<Teacher> list = teacherService.list(null);
		return R.ok().data("items", list);
	}
	//逻辑删除
	@ApiOperation(value = "根据ID删除讲师")
	@DeleteMapping("{id}")
	public R remove(@ApiParam(name = "id", value = "讲师ID", required = true)
							@PathVariable String id) {
		boolean flag = teacherService.removeById(id);
		return R.ok();
	}
	@ApiOperation(value = "分页讲师列表")
	@GetMapping("{page}/{limit}")
	public R pageList(
	    @ApiParam(name = "page", value = "当前页码", required = true)
	    @PathVariable Long page,

	    @ApiParam(name = "limit", value = "每页记录数", required = true)
	    @PathVariable Long limit){

	    Page<Teacher> pageParam = new Page<>(page, limit);

	    teacherService.page(pageParam, null);
	    List<Teacher> records = pageParam.getRecords();
	    long total = pageParam.getTotal();

	    return  R.ok().data("total", total).data("rows", records);
	}
	
	@ApiOperation(value = "多条件分页查询")
	@PostMapping("teacherQuery/{page}/{limit}")
	public R teacherQuery(@ApiParam(name = "page",value = "当前页码",required = true)
						  @PathVariable long page,
						  
						  @ApiParam(name = "limit",value = "每页记录数",required = true)
	  					  @PathVariable long limit,
	  					  
	  					  @RequestBody(required = false) TeacherQuery teacherQuery) {
		
		//分页
		Page<Teacher> pageParam = new Page<>(page, limit);

		//多条件查询
		String name = teacherQuery.getName();
		Integer level = teacherQuery.getLevel();
		String begin = teacherQuery.getBegin();
		String end = teacherQuery.getEnd();
		QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
		if (!StringUtils.isEmpty(name)) {
			queryWrapper.like("name", name);
		}

		if (!StringUtils.isEmpty(level) ) {
			queryWrapper.eq("level", level);
		}

		if (!StringUtils.isEmpty(begin)) {
			queryWrapper.ge("gmt_create", begin);
		}

		if (!StringUtils.isEmpty(end)) {
			queryWrapper.le("gmt_create", end);
		}
		 //排序
		queryWrapper.orderByDesc("gmt_create");
		
	    teacherService.page(pageParam, queryWrapper);
	    List<Teacher> records = pageParam.getRecords();
	    long total = pageParam.getTotal();

	    return  R.ok().data("total", total).data("rows", records);
	}
	
	
	@ApiOperation(value = "新增讲师")
	@PostMapping("add")
	public R save(
			@ApiParam(name = "teacher", value = "讲师对象", required = true)
			@RequestBody Teacher teacher){

		teacherService.save(teacher);
		return R.ok();
	}
	
	@ApiOperation(value = "根据ID查询讲师")
	@GetMapping("{id}")
	public R getById(
			@ApiParam(name = "id", value = "讲师ID", required = true)
			@PathVariable String id){

		Teacher teacher = teacherService.getById(id);
		return R.ok().data("item", teacher);
	}
	
	@ApiOperation(value = "根据ID修改讲师")
	@PutMapping("{id}")
	public R updateById(
	    @ApiParam(name = "id", value = "讲师ID", required = true)
	    @PathVariable String id,

	    @ApiParam(name = "teacher", value = "讲师对象", required = true)
	    @RequestBody Teacher teacher){

	    teacher.setId(id);
	    teacherService.updateById(teacher);
	    return R.ok();
	}

}


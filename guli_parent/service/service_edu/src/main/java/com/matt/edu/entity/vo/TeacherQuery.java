package com.matt.edu.entity.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "Teacher多条件查询对象", description = "讲师多条件查询对象封装")

public class TeacherQuery implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "教师名称,模糊查询")
	private String name;

	@ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
	private Integer level;
    
	@ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
	private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

	@ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
	private String end;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
	
}
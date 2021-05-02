package com.matt.edu.entity.course;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "课程基本信息", description = "课程管理新增课程中基本信息的表单对象")
public class CourseInfoForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "课程ID")
	@TableId(value = "id", type = IdType.INPUT)
	private String id;

	@ApiModelProperty(value = "课程讲师ID")
	private String teacherId;
	
	

	@ApiModelProperty(value = "二级分类id")
	private String subjectId;
	
	@ApiModelProperty(value = "一级分类级ID")
    private String subjectParentId;

	@ApiModelProperty(value = "课程标题")
	private String title;

	@ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
	private BigDecimal price;

	@ApiModelProperty(value = "总课时")
	private Integer lessonNum;

	@ApiModelProperty(value = "课程封面图片路径")
	private String cover;

	@ApiModelProperty(value = "课程简介")
	private String description;

	public String getId() {
		return id;
	}
	public String getSubjectParentId() {
		return subjectParentId;
	}

	public void setSubjectParentId(String subjectParentId) {
		this.subjectParentId = subjectParentId;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getLessonNum() {
		return lessonNum;
	}

	public void setLessonNum(Integer lessonNum) {
		this.lessonNum = lessonNum;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cover == null) ? 0 : cover.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lessonNum == null) ? 0 : lessonNum.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
		result = prime * result + ((subjectParentId == null) ? 0 : subjectParentId.hashCode());
		result = prime * result + ((teacherId == null) ? 0 : teacherId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseInfoForm other = (CourseInfoForm) obj;
		if (cover == null) {
			if (other.cover != null)
				return false;
		} else if (!cover.equals(other.cover))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lessonNum == null) {
			if (other.lessonNum != null)
				return false;
		} else if (!lessonNum.equals(other.lessonNum))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (subjectId == null) {
			if (other.subjectId != null)
				return false;
		} else if (!subjectId.equals(other.subjectId))
			return false;
		if (subjectParentId == null) {
			if (other.subjectParentId != null)
				return false;
		} else if (!subjectParentId.equals(other.subjectParentId))
			return false;
		if (teacherId == null) {
			if (other.teacherId != null)
				return false;
		} else if (!teacherId.equals(other.teacherId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}


	
}
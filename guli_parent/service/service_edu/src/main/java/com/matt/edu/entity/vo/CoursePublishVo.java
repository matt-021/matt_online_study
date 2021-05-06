package com.matt.edu.entity.vo;



public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public Integer getLessonNum() {
		return lessonNum;
	}
	public void setLessonNum(Integer lessonNum) {
		this.lessonNum = lessonNum;
	}
	public String getSubjectLevelOne() {
		return subjectLevelOne;
	}
	public void setSubjectLevelOne(String subjectLevelOne) {
		this.subjectLevelOne = subjectLevelOne;
	}
	public String getSubjectLevelTwo() {
		return subjectLevelTwo;
	}
	public void setSubjectLevelTwo(String subjectLevelTwo) {
		this.subjectLevelTwo = subjectLevelTwo;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cover == null) ? 0 : cover.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lessonNum == null) ? 0 : lessonNum.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((subjectLevelOne == null) ? 0 : subjectLevelOne.hashCode());
		result = prime * result + ((subjectLevelTwo == null) ? 0 : subjectLevelTwo.hashCode());
		result = prime * result + ((teacherName == null) ? 0 : teacherName.hashCode());
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
		CoursePublishVo other = (CoursePublishVo) obj;
		if (cover == null) {
			if (other.cover != null)
				return false;
		} else if (!cover.equals(other.cover))
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
		if (subjectLevelOne == null) {
			if (other.subjectLevelOne != null)
				return false;
		} else if (!subjectLevelOne.equals(other.subjectLevelOne))
			return false;
		if (subjectLevelTwo == null) {
			if (other.subjectLevelTwo != null)
				return false;
		} else if (!subjectLevelTwo.equals(other.subjectLevelTwo))
			return false;
		if (teacherName == null) {
			if (other.teacherName != null)
				return false;
		} else if (!teacherName.equals(other.teacherName))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
    
}

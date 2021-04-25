package com.matt.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
/*
 * ecxel 模板对应类
 */
public class SubjectData {
	 @ExcelProperty(index = 0)
	 private String oneSubjectName;//一级分类
	 @ExcelProperty(index = 1)
	 private String twoSubjectName;//二级分类
	public String getOneSubjectName() {
		return oneSubjectName;
	}
	public void setOneSubjectName(String oneSubjectName) {
		this.oneSubjectName = oneSubjectName;
	}
	public String getTwoSubjectName() {
		return twoSubjectName;
	}
	public void setTwoSubjectName(String twoSubjectName) {
		this.twoSubjectName = twoSubjectName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oneSubjectName == null) ? 0 : oneSubjectName.hashCode());
		result = prime * result + ((twoSubjectName == null) ? 0 : twoSubjectName.hashCode());
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
		SubjectData other = (SubjectData) obj;
		if (oneSubjectName == null) {
			if (other.oneSubjectName != null)
				return false;
		} else if (!oneSubjectName.equals(other.oneSubjectName))
			return false;
		if (twoSubjectName == null) {
			if (other.twoSubjectName != null)
				return false;
		} else if (!twoSubjectName.equals(other.twoSubjectName))
			return false;
		return true;
	}
	 
	 
}

package com.matt.edu.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/*[       {  
    id: 1,  
    label: 'Level one 1',  
    children: [{  
      id: 4,  
      label: 'Level two 1-1',  
    }]  
  }, {  
    id: 2,  
    label: 'Level one 2',  
    children: [{  
      id: 5,  
      label: 'Level two 2-1'  
    }, {  
      id: 6,  
      label: 'Level two 2-2'  
    }]  
  }  
]*/ 
//一级分类  根据前端所需的数据格式 设计的封装类

public class OneSubject {
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

	public List<TwoSubject> getChildren() {
		return children;
	}

	public void setChildren(List<TwoSubject> children) {
		this.children = children;
	}

	private String id;
    private String title;

    //一个一级分类有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		OneSubject other = (OneSubject) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
    
}

package com.matt.edu.listenter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.matt.edu.entity.Subject;
import com.matt.edu.entity.excel.SubjectData;
import com.matt.edu.service.SubjectService;
import com.matt.service_base.exception.MattException;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
	
	private SubjectService subjectService;
	
	public  SubjectExcelListener() {
		// TODO Auto-generated constructor stub
	}
	public SubjectExcelListener(SubjectService subjectService) {
		this.subjectService = subjectService;
	}
	@Override
	public void invoke(SubjectData data, AnalysisContext context) {
		if(data == null) {
            throw new MattException(20001,"文件数据为空");
        }

		//获取一级分类
		String oneSubjectName = data.getOneSubjectName();
	
		//查询一级分类是否存在，无则新增到数据库
		Subject oneSubj = exisit(oneSubjectName);
		if(oneSubj==null) {
			oneSubj = new Subject();
			oneSubj.setParentId("0");
			oneSubj.setTitle(data.getOneSubjectName());//一级分类名称
            subjectService.save(oneSubj);
		}
		//获取一级分类id值
        String pid = oneSubj.getId();
        //添加二级分类
        //判断二级分类是否重复
        Subject twoSubject = existTwoSubject(subjectService, data.getTwoSubjectName(), pid);
        if(twoSubject == null) {
        	twoSubject = new Subject();
        	twoSubject.setParentId(pid);
        	twoSubject.setTitle(data.getTwoSubjectName());//二级分类名称
            subjectService.save(twoSubject);
        }

	
		
		
		
	}

	private Subject existTwoSubject(SubjectService subjectService2, String twoSubjectName, String pid) {
		
	  //查询已有二级分类
		QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("title",twoSubjectName);
		queryWrapper.eq("parent_id",pid);
		Subject oneSubjList = subjectService.getOne(queryWrapper);
		return oneSubjList;
	}
	private Subject exisit(String oneSubjectName) {
		QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("parent_id", 0);
		queryWrapper.eq("title", oneSubjectName);
		Subject twoSubj = subjectService.getOne(queryWrapper);
		return twoSubj;
	}
	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		// TODO Auto-generated method stub
		
	}

}

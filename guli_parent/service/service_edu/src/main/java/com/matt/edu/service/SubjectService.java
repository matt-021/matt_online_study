package com.matt.edu.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.service.IService;
import com.matt.edu.entity.Subject;
import com.matt.edu.entity.subject.OneSubject;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zylin
 * @since 2021-04-25
 */
public interface SubjectService extends IService<Subject> {

	void saveSubject(MultipartFile file);

	List<OneSubject> getAllOneTwoSubject();

	
}

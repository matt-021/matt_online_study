package com.matt.edu.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.matt.edu.entity.Chapter;
import com.matt.edu.entity.chapter.ChapterVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zylin
 * @since 2021-04-27
 */
public interface ChapterService extends IService<Chapter> {
	//获取章节和小节数据列表
	List<ChapterVo> nestedList(String courseId);
	boolean removeChapterById(String id);
}

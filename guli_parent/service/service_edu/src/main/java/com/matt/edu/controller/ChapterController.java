package com.matt.edu.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matt.commonutils.R;
import com.matt.edu.entity.Chapter;
import com.matt.edu.entity.chapter.ChapterVo;
import com.matt.edu.service.ChapterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author zylin
 * @since 2021-04-27
 */
@Api(description="课程章节管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/edu/chapter")
public class ChapterController {

	@Autowired
	private ChapterService chapterService;
	
	@ApiOperation(value = "嵌套章节数据列表")
	@GetMapping("nested-list/{courseId}")
	public R nestedListByCourseId(
			@ApiParam(name = "courseId", value = "课程ID", required = true)
			@PathVariable String courseId){

		List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);
		return R.ok().data("items", chapterVoList);
	}
	
	@ApiOperation(value = "新增章节")
	@PostMapping
	public R save(
	    @ApiParam(name = "chapterVo", value = "章节对象", required = true)
	    @RequestBody Chapter chapter){

	    chapterService.save(chapter);
	    return R.ok();
	} 
	@ApiOperation(value = "根据ID查询章节")
	@GetMapping("{id}")
	public R getById(
	    @ApiParam(name = "id", value = "章节ID", required = true)
	    @PathVariable String id){

	    Chapter chapter = chapterService.getById(id);
	    return R.ok().data("item", chapter);
	}
	@ApiOperation(value = "根据ID修改章节")
	@PutMapping("{id}")
	public R updateById(
	    @ApiParam(name = "id", value = "章节ID", required = true)
	    @PathVariable String id,

	    @ApiParam(name = "chapter", value = "章节对象", required = true)
	    @RequestBody Chapter chapter){

	    chapter.setId(id);
	    chapterService.updateById(chapter);
	    return R.ok();
	}
	@ApiOperation(value = "根据ID删除章节")
	@DeleteMapping("{id}")
	public R removeById(
	    @ApiParam(name = "id", value = "章节ID", required = true)
	    @PathVariable String id){

	    boolean result = chapterService.removeChapterById(id);
	    if(result){
	        return R.ok();
	    }else{
	        return R.error().message("删除失败");
	    }
	}

}


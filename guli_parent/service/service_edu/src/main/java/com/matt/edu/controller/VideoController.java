package com.matt.edu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matt.commonutils.R;
import com.matt.edu.entity.Video;
import com.matt.edu.service.VideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author zylin
 * @since 2021-04-27
 */
@Api(description="课程小节")
@RestController
@RequestMapping("/edu/video")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    //添加小节
    @ApiOperation(value = "添加小节")
    @PostMapping("addVideo")
    public R addVideo(@RequestBody Video eduVideo) {
    	System.out.println("---------");	
    	System.out.println(eduVideo);
        videoService.save(eduVideo);
        return R.ok();
    }

    //删除小节
    // TODO 后面这个方法需要完善：删除小节时候，同时把里面视频删除
    @ApiOperation(value = "删除小节")
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        videoService.removeById(id);
        return R.ok();
    }
  //根据小节id查询
    @ApiOperation(value = "根据小节id查询")
    @GetMapping("/getVideoInfo/{id}")
    public R getVideoInfo(@PathVariable String id) {
        Video eduVideo = this.videoService.getById(id);
        return R.ok().data("eduVideo",eduVideo);
    }
    //修改小节 TODO
}


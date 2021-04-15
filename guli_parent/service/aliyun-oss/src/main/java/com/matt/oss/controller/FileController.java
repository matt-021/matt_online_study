package com.matt.oss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.matt.commonutils.R;
import com.matt.oss.service.FileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description="阿里云文件管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/aliyunoss/file")
public class FileController {

	@Autowired
	private FileService fileService;

	/**
	 * 文件上传
	 *
	 * @param file
	 */
	@ApiOperation(value = "文件上传")
	@PostMapping("upload")
	public R upload(
			@ApiParam(name = "file", value = "文件", required = true)
			@RequestParam("file") MultipartFile file) {

		String uploadUrl = fileService.upload(file);
		//返回r对象
		return R.ok().message("文件上传成功").data("url", uploadUrl);

	}
	@RequestMapping("test")
	public R test() {
		return R.ok();
	}
}
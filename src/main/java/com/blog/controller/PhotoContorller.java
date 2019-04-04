package com.blog.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.blog.entities.Photo;
import com.blog.service.PhotoService;
import com.blog.util.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("photo")
public class PhotoContorller {

	// 图片类型
	private static List<String> fileTypes = new ArrayList<String>();
	//上传目录
	private String DirectoryName = "picture/photo";
	
	@Autowired
	private PhotoService photoService;
	
	static {
		fileTypes.add(".jpg");
		fileTypes.add(".jpeg");
		fileTypes.add(".bmp");
		fileTypes.add(".gif");
		fileTypes.add(".png");
	}

	@RequestMapping("uploadImagePage")
	public String uploadImagePage() {
		return "uploadPhoto";
	}
	
	@RequestMapping("photoList")
	public String photoList(@RequestParam(defaultValue="1", required=false)Integer pageNum, 
					Map<String, Object> map) {
		PageHelper.startPage(pageNum, 16);
		List<Photo> photos = photoService.getAllPhoto();
		PageInfo<Photo> pageInfo = new PageInfo<Photo>(photos);
		map.put("pageInfo", pageInfo);
		return "photoList";
	}
	
	@RequestMapping("deletePhoto")
	public String deletePhoto(HttpServletRequest request, Photo photo) {
		String url = photo.getUrl();
		String path = null;
		try {
			path = new File(this.getClass().getResource("/").getPath())
			.getParentFile().getParentFile().getParentFile().getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File file = new File(path+url);
		if(file.exists()) {
			file.delete();
			photoService.deletePhoto(photo.getPhotoId());
		}
		return "redirect:photoList";
	}
	
	@ResponseBody
	@RequestMapping("uploadImage")
	public Message uploadImage(HttpServletRequest request) throws IllegalStateException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		
		MultipartHttpServletRequest multiRequest = multipartResolver
				.resolveMultipart(request);
		String photoDesc = multiRequest.getParameter("photoDesc");
		System.out.println("数据："+photoDesc);
		
		// 图片名称
		String fileName = null;
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					System.out.println("myFileName: "+myFileName);
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						// 获得图片的原始名称
						String originalFilename = file.getOriginalFilename();
						// 获得图片后缀名称,如果后缀不为图片格式，则不上传
						String suffix = originalFilename.substring(
								originalFilename.lastIndexOf("."))
								.toLowerCase();
						if (!fileTypes.contains(suffix)) {
							continue;
						}
						// 获得上传路径的绝对路径地址(/upload)-->
						String realPath = request.getSession()
								.getServletContext()
								.getRealPath("/" + DirectoryName);
						System.out.println("realPath: "+realPath);
						// 如果路径不存在，则创建该路径
						File realPathDirectory = new File(realPath);
						if (realPathDirectory == null
								|| !realPathDirectory.exists()) {
							realPathDirectory.mkdirs();
						}
						// 重命名上传后的文件名 111112323.jpg
						fileName = UUID.randomUUID().toString().substring(0, 8) + suffix;
						// 定义上传路径 .../upload/111112323.jpg
						
						System.out.println("DirectoryName: "+DirectoryName);
						//存储图片地址
						photoService.addPhoto(new Photo("/Blog/"+DirectoryName +"/"+ fileName, photoDesc));
						
						System.out.println("realPathDirectory"+realPathDirectory);
						File uploadFile = new File(realPathDirectory + "/"
								+ fileName);
						System.out.println("uploadFile: "+uploadFile);
						file.transferTo(uploadFile);
					}
				}
				// 记录上传该文件后的时间
				// int finaltime = (int) System.currentTimeMillis();
				// System.out.println(finaltime - pre);
			}
		}
		return Message.getSuccessMessage();
	}
}

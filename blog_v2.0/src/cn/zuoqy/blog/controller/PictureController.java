package cn.zuoqy.blog.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PictureController {

	/**
	 * 下载图片
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/pic/down.do")
	public void down(HttpServletRequest request, HttpServletResponse response) {
		String img = request.getParameter("path");
		String path = request.getSession().getServletContext().getRealPath(img);
		File resource = new File(path);
		response.setHeader("Content-Disposition", "attachment;filename=img.jpg");
		try {
			InputStream is = new FileInputStream(resource);
			OutputStream os = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ( (len=is.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
			is.close();
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

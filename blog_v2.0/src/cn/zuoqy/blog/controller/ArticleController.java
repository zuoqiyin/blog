package cn.zuoqy.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zuoqy.blog.model.Article;
import cn.zuoqy.blog.service.ArticleService;
import cn.zuoqy.blog.util.DataBase;

/**
 * @author zuoqiyin
 * @date 2017-10-30
 * @Description: ArticleController
 * @version:1.0
 */
@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	/**
	 * 获取文章
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/art/get.do")
	@ResponseBody
	public DataBase get(HttpServletRequest request) {
		DataBase db = new DataBase();
		String aid = request.getParameter("aid");
		try {
			Article art = articleService.getArticle(aid);
			db.setCode(1);
			db.setItem(art);
		} catch (Exception e) {
			e.printStackTrace();
			db.setCode(0);
			db.setMsg("请求异常,请稍后重试!");
		}
		return db;
	}
	
	/**
	 * 查询文章列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/art/select.do")
	@ResponseBody
	public DataBase select(HttpServletRequest request) {
		DataBase db = new DataBase();
		String type = request.getParameter("type");
		String page = request.getParameter("page");
		String size = request.getParameter("size");
		if (page==null) page="1";
		if (size==null) size="5";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		params.put("index", ((Integer.parseInt(page)-1)*Integer.parseInt(size)));
		params.put("size", Integer.parseInt(size));
		try {
			List<Article> arts = articleService.selectArticle(params);
			int count = articleService.getCount(params);
			db.setCode(1);
			db.setItem(arts);
			db.setCount(count/Integer.parseInt(size)==0?count/Integer.parseInt(size):count/Integer.parseInt(size)+1);
		} catch (Exception e) {
			e.printStackTrace();
			db.setCode(0);
			db.setItem("请求异常,请稍后重试");
		}
		return db;
	}
	
}

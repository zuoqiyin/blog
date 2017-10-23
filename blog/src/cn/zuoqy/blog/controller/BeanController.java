package cn.zuoqy.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zuoqy.blog.model.Bean;
import cn.zuoqy.blog.service.BeanService;

/**
 * @author zuoqiyin
 * @date 2017-9-20
 * @Description: beanController
 * @version:1.0
 */
@Controller
public class BeanController {

	@Autowired
	private BeanService beanService;
	
	@RequestMapping("/main.do")
	public String selectMainList(Model model) {
		try {
			model.addAttribute("beans", beanService.selectBeanMain());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "main";
	}
	
	@RequestMapping("/bean/type.do")
	public String selectMainList(Model model, HttpServletRequest request) {
		String type = request.getParameter("type");
		try {
			model.addAttribute("beans", beanService.selectBeanType(type));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "type";
	}
	
	@RequestMapping("/bean/getBean.do")
	public String getBean(Model model, HttpServletRequest request) {
		if ("comment".equals(request.getParameter("flag"))) model.addAttribute("flag", "comment");//评论
		
		try {
			Bean bean = beanService.getBean(request.getParameter("id"));
			model.addAttribute("bean", bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "page";
	}
}

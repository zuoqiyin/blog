package cn.zuoqy.blog.service;

import java.util.List;

import cn.zuoqy.blog.model.Bean;

/**
 * @author zuoqiyin
 * @date 2017-9-20
 * @Description: beanService
 * @version:1.0
 */
public interface BeanService {

	public List<Bean> selectBeanMain() throws Exception;
	
	public Bean getBean(String id) throws Exception;
	
	public List<Bean> selectBeanType(String type) throws Exception;
}

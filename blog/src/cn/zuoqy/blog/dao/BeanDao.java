package cn.zuoqy.blog.dao;

import java.util.List;
import java.util.Map;

import cn.zuoqy.blog.model.Bean;

/**
 * @author zuoqiyin
 * @date 2017-9-20
 * @Description: beanDao
 * @version:1.0
 */
public interface BeanDao {

	/**
	 * 首页文章推荐
	 * @return
	 * @throws Exception
	 */
	public List<Bean> selectBeanMain() throws Exception;
	
	public List<Bean> selectBeanType(Map<String, String> params) throws Exception;
	
	public Bean getBean(Map<String, String> params) throws Exception;
	
	public int updateBean(Map<String, String> params) throws Exception;
}

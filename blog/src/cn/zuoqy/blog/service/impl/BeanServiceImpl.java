package cn.zuoqy.blog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zuoqy.blog.dao.BeanDao;
import cn.zuoqy.blog.model.Bean;
import cn.zuoqy.blog.service.BeanService;

/**
 * @author zuoqiyin
 * @date 2017-9-20
 * @Description: beanServiceImpl
 * @version:1.0
 */
@Service("beanService")
public class BeanServiceImpl implements BeanService {

	@Autowired
	private BeanDao beanDao;
	
	@Override
	public List<Bean> selectBeanMain() throws Exception {
		return beanDao.selectBeanMain();
	}

	@Override
	public Bean getBean(String id) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		Bean bean = beanDao.getBean(params);
		int viewCount = Integer.parseInt(bean.getViewCount())+1;
		params.put("viewCount",viewCount+"");
		beanDao.updateBean(params);
		return bean;
	}

	@Override
	public List<Bean> selectBeanType(String type) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		return beanDao.selectBeanType(params);
	}

}

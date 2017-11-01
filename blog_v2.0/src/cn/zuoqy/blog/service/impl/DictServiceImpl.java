package cn.zuoqy.blog.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zuoqy.blog.dao.DictDao;
import cn.zuoqy.blog.model.Dict;
import cn.zuoqy.blog.service.DictService;

/**
 * @author zuoqiyin
 * @date 2017-10-27
 * @Description: dictServiceImpl
 * @version:1.0
 */
@Service
public class DictServiceImpl implements DictService{

	@Autowired
	private DictDao dictDao;

	@Override
	public Dict getVerify() throws Exception {
		ResourceBundle rb = ResourceBundle.getBundle("system");
		int vid = new Random().nextInt(Integer.parseInt(rb.getString("verifyCount")));
		Map<String, String> params = new HashMap<String, String>();
		params.put("dictType", "verify");
		params.put("flag", vid+"");
		return dictDao.selectDict(params).get(0);
	}
	
}

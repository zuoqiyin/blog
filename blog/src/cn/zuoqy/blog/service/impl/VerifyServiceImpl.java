package cn.zuoqy.blog.service.impl;

import java.util.Random;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zuoqy.blog.dao.VerifyDao;
import cn.zuoqy.blog.model.Verify;
import cn.zuoqy.blog.service.VerifyService;

@Service("verifyService")
public class VerifyServiceImpl implements VerifyService{

	@Autowired
	private VerifyDao verifyDao;
	
	@Override
	public Verify getVerify() throws Exception {
		ResourceBundle rb = ResourceBundle.getBundle("system");
		int vid = new Random().nextInt(Integer.parseInt(rb.getString("verifyCount")))+1;
		return verifyDao.getVerify(vid+"");
	}

}

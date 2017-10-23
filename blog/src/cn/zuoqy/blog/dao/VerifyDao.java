package cn.zuoqy.blog.dao;

import cn.zuoqy.blog.model.Verify;

/**
 * @author zuoqiyin
 * @date 2017-9-22
 * @Description: verifyDao
 * @version:1.0
 */
public interface VerifyDao {
	
	public Verify getVerify(String vid) throws Exception;
}

package cn.zuoqy.blog.dao;

import java.util.List;
import java.util.Map;

import cn.zuoqy.blog.model.Dict;

/**
 * @author zuoqiyin
 * @date 2017-10-27
 * @Description: dictDao
 * @version:1.0
 */
public interface DictDao {
	
	public List<Dict> selectDict(Map<String, String> params) throws Exception; 

}

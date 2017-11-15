package cn.zuoqy.blog.dao;

import java.util.List;
import java.util.Map;

import cn.zuoqy.blog.model.Article;

/**
 * @author zuoqiyin
 * @date 2017-10-30
 * @Description: articleDao
 * @version:1.0
 */
public interface ArticleDao {

	public List<Article> selectArticle(Map<String, Object> params) throws Exception;
	
	public int getCount(Map<String, Object> params) throws Exception;
	
	public int updateArticle(Map<String, Object> params) throws Exception;
}

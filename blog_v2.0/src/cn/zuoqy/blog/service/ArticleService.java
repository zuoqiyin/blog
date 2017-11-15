package cn.zuoqy.blog.service;

import java.util.List;
import java.util.Map;

import cn.zuoqy.blog.model.Article;

/**
 * @author zuoqiyin
 * @date 2017-10-30
 * @Description: articleService
 * @version:1.0
 */

public interface ArticleService {

	public List<Article> selectArticle(Map<String, Object> params) throws Exception;
	
	public Article getArticle(String aid) throws Exception;
	
	public int getCount(Map<String, Object> params) throws Exception;
	
	public void addCom(String aid) throws Exception;
}

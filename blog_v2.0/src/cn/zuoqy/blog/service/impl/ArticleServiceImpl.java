package cn.zuoqy.blog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zuoqy.blog.dao.ArticleDao;
import cn.zuoqy.blog.model.Article;
import cn.zuoqy.blog.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public List<Article> selectArticle(Map<String, Object> params)
			throws Exception {
		return articleDao.selectArticle(params);
	}

	@Override
	public Article getArticle(String aid) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("index", 0);
		params.put("size", 1);
		params.put("aid", aid);
		Article art = articleDao.selectArticle(params).get(0);
		params.clear();
		params.put("aid", aid);
		params.put("viewCount", (Integer.parseInt(art.getViewCount())+1)+"");
		articleDao.updateArticle(params);
		return art;
	}

	@Override
	public int getCount(Map<String, Object> params) throws Exception {
		return articleDao.getCount(params);
	}

	@Override
	public void addCom(String aid) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("index", 0);
		params.put("size", 1);
		params.put("aid", aid);
		Article art = articleDao.selectArticle(params).get(0);
		params.clear();
		params.put("aid", aid);
		params.put("comCount", (Integer.parseInt(art.getComCount())+1)+"");
		articleDao.updateArticle(params);
	}
}

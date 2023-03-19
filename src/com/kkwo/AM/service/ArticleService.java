package com.kkwo.AM.service;


import java.util.List;

import com.kkwo.AM.container.Container;
import com.kkwo.AM.dao.ArticleDao;
import com.kkwo.AM.dto.Article;

public class ArticleService {
	ArticleDao articleDao;
	
	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public int setNewId() {
		return articleDao.setNewId();
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public boolean isConvertibleToInt(String string) {
		return articleDao.isConvertibleToInt(string);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}
	
	public void add(Article article) {
		articleDao.add(article);
	}

	public void remove(Article articleToDelete) {
		articleDao.remove(articleToDelete);
	}
}

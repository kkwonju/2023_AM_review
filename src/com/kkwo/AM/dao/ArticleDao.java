package com.kkwo.AM.dao;

import java.util.ArrayList;
import java.util.List;

import com.kkwo.AM.dto.Article;

public class ArticleDao extends Dao {
	public List<Article> articles;

	public ArticleDao() {
		articles = new ArrayList<>();
	}

	@Override
	public int getLastId() {
		return lastId;
	}

	public int setNewId() {
		int newId = lastId + 1;
		return newId;
	}

	public void add(Article article) {
		articles.add(article);
		lastId++;
	}
}
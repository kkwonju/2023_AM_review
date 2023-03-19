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
	
	private int getArticleByIndex(int id) {
		int i = 0;
		
		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public Article getArticleById(int id) {
		int index = getArticleByIndex(id);
		
		if (index != -1) {
			return articles.get(index);
		}
		return null;
	}

	public boolean isConvertibleToInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void remove(Article articleToDelete) {
		articles.remove(articleToDelete);
	}
}
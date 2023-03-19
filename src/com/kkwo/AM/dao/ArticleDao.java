package com.kkwo.AM.dao;

import java.util.ArrayList;
import java.util.List;

import com.kkwo.AM.dto.Article;

public class ArticleDao {
	public List<Article> articles;
	
	public ArticleDao() {
		articles = new ArrayList<>();
	}
}

package com.kkwo.AM.dto;

public class Article extends dto{
	public int hit;
	public String title;
	public String content;

	public Article(int id, String title, String content, String regDate, String updateDate) {
		this(id, 0, title, content, regDate, updateDate);
	}

	public Article(int id, int hit, String title, String content, String regDate, String updateDate) {
		this.id = id;
		this.hit = hit;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.updateDate = updateDate;
	}
}

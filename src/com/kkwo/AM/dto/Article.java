package com.kkwo.AM.dto;

public class Article extends dto{
	public int hit;
	public int memberId;
	public String title;
	public String content;

	public Article(int articleId, int memberId, String title, String content, String regDate, String updateDate) {
		this(articleId, 0, memberId, title, content, regDate, updateDate);
	}

	public Article(int articleId, int hit, int memberId, String title, String content, String regDate, String updateDate) {
		this.id = articleId;
		this.hit = hit;
		this.memberId = memberId;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.updateDate = updateDate;
	}
}

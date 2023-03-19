package com.kkwo.AM.container;

import com.kkwo.AM.dao.ArticleDao;
import com.kkwo.AM.dao.MemberDao;
import com.kkwo.AM.service.ArticleService;
import com.kkwo.AM.service.MemberService;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	
	public static ArticleService articleService;
	public static MemberService memberService;	
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		
		articleService = new ArticleService();
		memberService = new MemberService();
	}
}

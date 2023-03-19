package com.kkwo.AM.container;

import com.kkwo.AM.dao.ArticleDao;
import com.kkwo.AM.dao.MemberDao;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
	}
}

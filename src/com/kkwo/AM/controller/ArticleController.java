package com.kkwo.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kkwo.AM.container.Container;
import com.kkwo.AM.dto.Article;
import com.kkwo.AM.dto.Member;
import com.kkwo.AM.util.Util;

public class ArticleController extends Controller {
	private List<Article> articles;
	private Scanner sc;
	private String actionMethodName;
	private String command;
	
	int lastArticleId = 3;

	public ArticleController(Scanner sc) {
		this.articles = Container.articleDao.articles;
		this.sc = sc;
	}

	@Override
	public void doAction(String actionMethodName, String command) {
		this.actionMethodName = actionMethodName;
		this.command = command;
		
		switch (actionMethodName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("해당 기능은 존재하지 않습니다");
			break;
		}
	}

	public void doWrite() {
		int id = lastArticleId + 1;
		int memberId = loginedMember.id;
		System.out.print("제목 : ");
		String title = sc.nextLine();
		System.out.print("내용 : ");
		String content = sc.nextLine();
		String regDate = Util.getNowDateTimeStr();
		Article article = new Article(id, memberId, title, content, regDate, regDate);
		articles.add(article);
		System.out.println(id + "번 게시글이 생성되었습니다");
		lastArticleId++;
	}

	public void showList() {
		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다");
			return;
		}

		String searchKeyword = command.substring("article list".length()).trim();
		List<Article> forPrintArticles = articles;
		if (searchKeyword.length() > 0) {
			System.out.println("searchKeyword : " + searchKeyword);
			forPrintArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					forPrintArticles.add(article);
				}
			}
			if (forPrintArticles.size() == 0) {
				System.out.println("검색 결과가 없습니다");
				return;
			}
		}
		
		System.out.println("번호 // 제목 // 조회 // 작성자");
		for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
			
			String writerName = null;			
			Article article = forPrintArticles.get(i);
			
			List<Member> members = MemberController.members;
			
			for(Member member : members) {
				if(member.id == article.memberId) {
					writerName = member.userName;
					break;
				}
			}
			System.out.printf("  %d  //  %s  //  %d  //  %s  \n", article.id, article.title, article.hit, writerName);
		}
	}

	public void showDetail() {
		String[] commandDiv = command.split(" ");
		if (commandDiv.length < 3) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		if (isConvertibleToInt(commandDiv[2]) == false) {
			System.out.println("3번째에 게시물 번호를 입력해주세요");
			return;
		}
		
		int id = Integer.parseInt(commandDiv[2]);
		Article foundArticle = getArticleById(id);
		
		if (foundArticle == null) {
			System.out.println(id + "번 게시글이 존재하지 않습니다");
			return;
		}
		
		String writerName = null;
		List<Member> members = MemberController.members;
		
		for(Member member : members) {
			if(member.id == foundArticle.memberId) {
				writerName = member.userName;
			}
		}
		System.out.println("번호 : " + foundArticle.id);
		System.out.println("조회 수 : " + foundArticle.hit);
		System.out.println("작성자 : " + writerName);		
		System.out.println("제목 : " + foundArticle.title);
		System.out.println("내용 : " + foundArticle.content);
		System.out.println("작성 시각 : " + foundArticle.regDate);
		System.out.println("수정 시각 : " + foundArticle.updateDate);
		foundArticle.hit++;
	}

	public void doModify() {
		String[] commandDiv = new String[3];
		commandDiv = command.split(" ");
		if (commandDiv.length < 3) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		if (isConvertibleToInt(commandDiv[2]) == false) {
			System.out.println("3번째에 게시물 번호를 입력해주세요");
			return;
		}
		
		int id = Integer.parseInt(commandDiv[2]);
		Article foundArticle = getArticleById(id);
		
		if (foundArticle == null) {
			System.out.println(id + "번 게시글이 존재하지 않습니다");
			return;
		}
		System.out.print("제목 : ");
		String newTitle = sc.nextLine();
		System.out.print("내용 : ");
		String newContent = sc.nextLine();
		String updateDate = Util.getNowDateTimeStr();
		foundArticle.title = newTitle;
		foundArticle.content = newContent;
		foundArticle.updateDate = updateDate;
		System.out.println(id + "번 게시글이 수정되었습니다"); // 사용자 입력 id값
	}

	public void doDelete() {
		String[] commandDiv = command.split(" ");
		if (commandDiv.length < 3) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		if (isConvertibleToInt(commandDiv[2]) == false) {
			System.out.println("3번째에 게시물 번호를 입력해주세요");
			return;
		}
		int id = Integer.parseInt(commandDiv[2]);
		Article foundArticle = getArticleById(id);
		if (foundArticle == null) {
			System.out.println(id + "번 게시글이 존재하지 않습니다");
			return;
		}
		articles.remove(foundArticle);
		System.out.println(id + "번 게시글이 삭제되었습니다");
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

	private Article getArticleById(int id) {
		int index = getArticleByIndex(id);
		if (index != -1) {
			return articles.get(index);
		}
		return null;
	}

	private boolean isConvertibleToInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void makeTestData() {
		System.out.println("Article 테스트 데이터가 생성되었습니다");
		articles.add(new Article(1, 10, 3, "제목 1", "내용 1", Util.getNowDateTimeStr(), Util.getNowDateTimeStr()));
		articles.add(new Article(2, 20, 2, "제목 2", "내용 2", Util.getNowDateTimeStr(), Util.getNowDateTimeStr()));
		articles.add(new Article(3, 30, 2, "제목 3", "내용 3", Util.getNowDateTimeStr(), Util.getNowDateTimeStr()));
	}
}

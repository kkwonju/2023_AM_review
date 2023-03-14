import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Article> articles = new ArrayList<>();
	static List<Member> members = new ArrayList<>();

	public static void main(String[] args) {

		/* 테스트 데이터 생성 */
		makeTestData();

		/* 프로그램 시작 */
		System.out.println("== 프로젝트 시작 ==");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 3;
		int lastMemberId = 0;

		while (true) {
			System.out.print("명령어 > ");
			String command = sc.nextLine().trim();

			int foundIndex = -1;

			/* 회원가입 */
			if (command.equals("member join")) {
				int id = lastMemberId + 1;
				System.out.println("회원가입");
				String loginId = null;
				while (true) {
					System.out.print("아이디 : ");
					loginId = sc.nextLine();
					if (isJoinableLoginId(loginId) != null) {
						System.out.println("이미 사용중인 아이디입니다");
						continue;
					}
					System.out.println("멋진 아이디네요!");
					break;
				}
				System.out.print("비밀번호 : ");
				String loginPw = sc.nextLine();
				System.out.print("비밀번호 확인 : ");
				String loginPwConfirm = sc.nextLine();

				System.out.print("이름 : ");
				String userName = sc.nextLine();

				String userRegDate = Util.getNowDateTimeStr();
				members.add(new Member(loginId, loginPw, userName, userRegDate));

				/* 회원 정보 출력 */
			} else if (command.equals("member")) {
				for (Member member : members) {
					System.out.println("로그인 아이디 : " + member.loginId);
					System.out.println("로그인 비밀번호 " + member.loginPw);
					System.out.println("유저 이름 : " + member.userName);
					System.out.println("가입 일자 : " + member.userRegDate);
				}

				/* 게시글 목록 및 검색 */
			} else if (command.startsWith("article list")) {
				/* 게시글이 없을 때 */
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}

				/* 게시글이 있을 때 */
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
						continue;
					}
				}
				System.out.println("번호 // 제목 // 조회");
				for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
					Article article = forPrintArticles.get(i);
					System.out.printf("  %d  //  %s  //  %d \n", article.id, article.title, article.hit);
				}

				/* 게시글 작성 */
			} else if (command.equals("article write")) {

				int id = lastArticleId + 1;
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String content = sc.nextLine();
				String regDate = Util.getNowDateTimeStr();

				Article article = new Article(id, title, content, regDate, regDate);
				articles.add(article);

				System.out.println(id + "번 게시글이 생성되었습니다");
				lastArticleId++;

				/* 게시글 상세보기 */
			} else if (command.startsWith("article detail")) {

				/* 명령어 끊어서 비교 */
				String[] comDiv = command.split(" "); // ** new 없이 바로
				
				if (comDiv.length < 3) {
					System.out.println("명령어를 확인해주세요");
					continue;
				}

				int id = Integer.parseInt(comDiv[2]);

				Article foundArticle = getArticleById(id);
				
				if (foundArticle == null) {
					System.out.println(id + "번 게시글이 존재하지 않습니다");
					continue;
				}
				System.out.println("번호 : " + foundArticle.id);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.content);
				System.out.println("조회 수 : " + foundArticle.hit);
				System.out.println("작성 시각 : " + foundArticle.regDate);
				System.out.println("수정 시각 : " + foundArticle.updateDate);
				foundArticle.hit++;

				/* 게시글 수정 */
			} else if (command.startsWith("article modify")) {

				String[] comDiv = new String[3];

				comDiv = command.split(" ");

				if (comDiv.length < 3) {
					System.out.println("명령어를 확인해주세요");
					continue;
				}
				int id = Integer.parseInt(comDiv[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.println(id + "번 게시글이 존재하지 않습니다");
					continue;
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

				/* 게시글 삭제 */
			} else if (command.startsWith("article delete")) {
				String[] comDiv = command.split(" ");

				if (comDiv.length < 3) {
					System.out.println("명령어를 확인해주세요");
					continue;
				}

				int id = Integer.parseInt(comDiv[2]);

				foundIndex = getArticleByIndex(id);

				if (foundIndex == -1) {
					System.out.println(id + "번 게시글이 존재하지 않습니다");
					continue;
				}
				articles.remove(foundIndex);
				System.out.println(id + "번 게시글이 삭제되었습니다");

				/* 프로그램 종료 */
			} else if (command.equals("exit")) {
				break;

				/* 명령어 잘못 입력 */
			} else {
				System.out.println("일치하는 명령어가 없습니다");
			}

		}
		System.out.println("== 프로그램 종료 ==");
		
		sc.close();

	}

	/* 회원 찾기 by loginId */
	private static Member isJoinableLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	/* 게시글 찾기 by index */
	private static int getArticleByIndex(int id) {
		int i = 0;
		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}

		return -1;
	}

	/* 게시글 찾기 by id */
	private static Article getArticleById(int id) {

//		for (Article article : articles) {
//			if (article.id == id) {
//				return article;
//			}
//		}

		int index = getArticleByIndex(id);

		/* 게시글이 있을 때 */
		if (index != -1) {
			return articles.get(index);
		}
		/* 게시글이 없을 때 */
		return null;
	}

	/* 테스트 데이터 생성 */
	private static void makeTestData() {
		articles.add(new Article(1, "제목 1", "내용 1", Util.getNowDateTimeStr(), Util.getNowDateTimeStr()));
		articles.add(new Article(2, "제목 2", "내용 2", Util.getNowDateTimeStr(), Util.getNowDateTimeStr()));
		articles.add(new Article(3, "제목 3", "내용 3", Util.getNowDateTimeStr(), Util.getNowDateTimeStr()));
	}
}

class Member {
	String loginId;
	String loginPw;
	String userName;
	String userRegDate;

	Member(String loginId, String loginPw, String userName, String userRegDate) {
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.userName = userName;
		this.userRegDate = userRegDate;
	}
}

class Article {
	int id;
	String title;
	String content;
	String regDate;
	String updateDate;
	int hit;

	Article(int id, String title, String content, String regDate, String updateDate) {
		this(id, title, content, regDate, updateDate, 0);
	}

	Article(int id, String title, String content, String regDate, String updateDate, int hit) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.hit = hit;
	}
}

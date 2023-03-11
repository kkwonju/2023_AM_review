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

		while (true) {
			System.out.print("명령어 > ");
			String command = sc.nextLine();

			Article foundArticle = null;
			int foundIndex = -1;

			/* 게시글 목록 */
			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
				} else {
					System.out.println("번호 // 제목 // 조회");
					for (int i = articles.size() - 1; i >= 0; i--) {
						foundArticle = articles.get(i);
						System.out.printf("  %d  //  %s  //  %d \n", foundArticle.id, foundArticle.title,
								foundArticle.hit);
					}
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
				String[] comDiv = new String[3];

				comDiv = command.split(" ");

				if (comDiv.length < 3) {
					System.out.println("명령어를 확인해주세요");
					continue;
				}

				int id = Integer.parseInt(comDiv[2]);

				foundArticle = getArticleById(id);
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

				foundArticle = getArticleById(id);
				if (foundArticle == null) {
					System.out.println(id + "번 게시글이 존재하지 않습니다");
					continue;
				}
				System.out.print("제목 : ");
				foundArticle.title = sc.nextLine();
				System.out.print("내용 : ");
				foundArticle.content = sc.nextLine();
				foundArticle.updateDate = Util.getNowDateTimeStr();

				System.out.println(foundArticle.id + "번 게시글이 수정되었습니다");

				/* 게시글 삭제 */
			} else if (command.startsWith("article delete")) {
				String[] comDiv = new String[3];
				comDiv = command.split(" ");

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
				System.out.println("== 프로그램 종료 ==");
				break;

				/* 회원가입 */
			} else if (command.equals("member join")) {
				System.out.println("회원가입");

				System.out.print("아이디 : ");
				String loginId = sc.nextLine();
				System.out.print("비밀번호 : ");
				String loginPw = sc.nextLine();
				System.out.print("비밀번호 확인 : ");
				String loginPwConfirm = sc.nextLine();

				System.out.print("이름 : ");
				String userName = sc.nextLine();

				String userRegDate = Util.getNowDateTimeStr();
				members.add(new Member(loginId, loginPw, userName, userRegDate));

				/* 회원 정보 출력 */
			} else if(command.equals("member")){
				for(Member member : members) {
					System.out.println(member.loginId);					
					System.out.println(member.loginPw);					
					System.out.println(member.userName);
					System.out.println(member.userRegDate);
				}
				
				/* 명령어 잘못 입력 */
			} else {
				System.out.println("일치하는 명령어가 없습니다");
			}

		}
		sc.close();
	}

	/* 게시글 찾기 by index */
	static int getArticleByIndex(int id) {
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
	static Article getArticleById(int id) {

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
	static void makeTestData() {
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

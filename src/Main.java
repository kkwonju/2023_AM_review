import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		/* 프로그램 시작 */
		System.out.println("== 프로젝트 시작 ==");
		Scanner sc = new Scanner(System.in);

		List<Article> articles = new ArrayList<>();

		int lastArticleId = 0;

		while (true) {
			System.out.print("명령어 > ");
			String command = sc.nextLine();

			Article foundArticle = null;

			/* 게시글 목록 */
			if (command.equals("article list")) {
				if (lastArticleId == 0) {
					System.out.println("게시글이 없습니다");
				} else {
					System.out.println("번호 // 제목 // 조회");
					for (int i = articles.size() - 1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.printf("  %d  //  %s  //  %d \n", article.id, article.title, article.hit);
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

				/* 입력된 id값과 배열 위치 비교 */
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundArticle = articles.get(i);
						break;
					}
				}
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

				/* 입력된 id값과 배열 위치 비교 */
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundArticle = articles.get(i);
						break;
					}
				}
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
				int foundIndex = -1;

				/* 입력된 id값과 배열 위치 비교 */
				/* ArrayList 순회하며 일치하는 객체의 index와 같은 값을 foundIndex에 저장 */
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundIndex = i;
						break;
					}
				}
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

				/* 명령어 잘못 입력 */
			} else {
				System.out.println("일치하는 명령어가 없습니다");
			}

		}
		sc.close();
	}
}

class Article {
	int id;
	String title;
	String content;
	String regDate;
	String updateDate;
	int hit;
	
	Article(int id, String title, String content, String regDate, String updateDate){
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

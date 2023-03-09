import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		/* 프로그램 시작 */
		System.out.println("== 프로젝트 시작 ==");
		Scanner sc = new Scanner(System.in);
		Article[] articles = new Article[2];
		
		int lastArticleId = 0;
		
		while (true) {
			System.out.print("명령어 > ");
			String command = sc.nextLine();
			
			/* 게시글 목록 */
			if(command.equals("article list")){
				if(lastArticleId == 0) {
					System.out.println("게시글이 없습니다");
				} else {
					System.out.println("번호 / 제목");
					for(int i = articles.length-1; i >= 0; i--) {
						System.out.printf("%d / %s\n", articles[i].id, articles[i].title);					
					}
				}
			
			/* 게시글 작성 */
			} else if(command.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String content = sc.nextLine();
				
				articles[id-1] = new Article(id, title, content);
				System.out.println(id + "번 게시글이 생성되었습니다");
				
				lastArticleId++;
				
			/* 게시글 상세보기 */
			} else if(command.equals("article detail")) {
				System.out.println("게시글 상세보기");
			
			/* 게시글 수정 */
			} else if(command.equals("article update")) {
				System.out.println("게시글 수정");
				
			/* 게시글 삭제 */
			} else if(command.equals("article delete")) {
				System.out.println("게시글 삭제");
				
			/* 프로그램 종료 */
			} else if(command.equals("exit")) {
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
	
	Article() {
		
	}
	
	Article(int id, String title, String content){
		this.id = id;
		this.title = title;
		this.content = content;
	}
}

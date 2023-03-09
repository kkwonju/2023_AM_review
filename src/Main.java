import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		/* 프로그램 시작 */
		System.out.println("== 프로젝트 시작 ==");
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.print("명령어 > ");
			String command = sc.nextLine();
			
			/* 게시글 목록 */
			if(command.equals("article list")){
				System.out.println("게시물 목록");
			
			/* 게시글 작성 */
			} else if(command.equals("article write")) {
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String content = sc.nextLine();
				
				System.out.println("1번 게시물이 생성되었습니다");
				System.out.println(title + " : " + content);
				
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

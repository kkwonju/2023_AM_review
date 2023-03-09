import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로젝트 시작 ==");
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.print("명령어 > ");
			String command = sc.nextLine();
			
			if(command.equals("article list")){
				System.out.println("게시물 목록");
				
			} else if(command.equals("article write")) {
				System.out.println("게시글 작성");
				
			} else if(command.equals("article detail")) {
				System.out.println("게시글 상세보기");
				
			} else if(command.equals("article update")) {
				System.out.println("게시글 수정");
				
			} else if(command.equals("article delete")) {
				System.out.println("게시글 삭제");
				
			} else if(command.equals("exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			} else {
				System.out.println("일치하는 명령어가 없습니다");
			}
			
		}
		sc.close();
	}
}

package com.kkwo.AM;

import java.util.List;
import java.util.Scanner;

import com.kkwo.AM.controller.ArticleController;
import com.kkwo.AM.controller.MemberController;
import com.kkwo.AM.dto.Article;
import com.kkwo.AM.dto.Member;

public class App {
	List<Article> articles;
	List<Member> members;

	public App() {
	}

	public void start() {
		System.out.println("== 프로젝트 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		ArticleController articleController = new ArticleController(sc);
		MemberController memberController = new MemberController(sc);
		
		articleController.makeTestData();
		memberController.makeTestData();
		
		while (true) {
			System.out.print("명령어 > ");
			String command = sc.nextLine().trim();
			
			if(command.equals("member join")){
				memberController.doJoin();
			
			} else if (command.equals("member login")) {
				memberController.doLogin();
			
			} else if (command.equals("member logout")) {
				memberController.doLogout();
			
			} else if (command.startsWith("article list")) {
				articleController.showList(command);
				 
			} else if (command.equals("article write")) {
				articleController.doWrite();

			} else if (command.startsWith("article detail")) {
				articleController.showDetail(command);
				
			} else if (command.startsWith("article modify")) {
				articleController.doModify(command);

			} else if (command.startsWith("article delete")) {
				articleController.doDelete(command);

			} else if (command.equals("exit")) {
				break;
			} else {
				System.out.println("일치하는 명령어가 없습니다");
			}
		}
		System.out.println("== 프로그램 종료 ==");
		sc.close();
	}
}

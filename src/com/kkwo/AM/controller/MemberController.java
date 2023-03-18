package com.kkwo.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kkwo.AM.dto.Member;
import com.kkwo.AM.util.Util;

public class MemberController {
	private List<Member> members;
	private Scanner sc;
	int lastMemberId = 3;

	public MemberController(Scanner sc) {
		this.members = new ArrayList<>();
		this.sc = sc;
	}

	public void doJoin() {
		int id = lastMemberId + 1;
		System.out.println("회원가입");
		String loginId = null;
		String loginPw = null;
		String userName = null;
		while (true) {
			System.out.print("아이디 : ");
			loginId = sc.nextLine().trim();
			if (loginId.length() == 0) {
				System.out.println("필수 정보입니다");
				continue;
			}
			if (isJoinableLoginId(loginId) != null) {
				System.out.println("이미 사용중인 아이디입니다");
				continue;
			}
			System.out.println("멋진 아이디네요!");
			break;
		}
		while (true) {
			System.out.print("비밀번호 : ");
			loginPw = sc.nextLine().trim();
			if (loginPw.length() == 0) {
				System.out.println("필수 정보입니다");
				continue;
			}
			System.out.print("비밀번호 확인 : ");
			String loginPwConfirm = sc.nextLine();
			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호가 일치하지 않습니다");
				continue;
			}
			break;
		}
		while (true) {
			System.out.print("이름 : ");
			userName = sc.nextLine().trim();
			if(userName.length() == 0) {
				System.out.println("필수 정보입니다");
				continue;
			}
			break;
		}
		String regDate = Util.getNowDateTimeStr();
		members.add(new Member(id, regDate, regDate, loginId, loginPw, userName));
		System.out.println(id + "번 회원이 가입되었습니다");
		lastMemberId++;
	}

	public void showProfile() {
		for (Member member : members) {
			System.out.println("로그인 아이디 : " + member.loginId);
			System.out.println("로그인 비밀번호 " + member.loginPw);
			System.out.println("유저 이름 : " + member.userName);
			System.out.println("가입 일자 : " + member.regDate);
		}
	}

	private Member isJoinableLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public void makeTestData() {
		System.out.println("Member 테스트 데이터가 생성되었습니다");
		members.add(new Member(1, Util.getNowDateTimeStr(), Util.getNowDateTimeStr(), "test1", "0000", "kkwo1"));
		members.add(new Member(2, Util.getNowDateTimeStr(), Util.getNowDateTimeStr(), "test2", "0000", "kkwo2"));
		members.add(new Member(3, Util.getNowDateTimeStr(), Util.getNowDateTimeStr(), "test3", "0000", "kkwo3"));
	}
}

package com.kkwo.AM.dto;

public class Member extends dto{
	public String loginId;
	public String loginPw;
	public String userName;

	public Member(int id, String regDate, String updateDate, String loginId, String loginPw, String userName) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.userName = userName;
	}
}
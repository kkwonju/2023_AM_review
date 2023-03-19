package com.kkwo.AM.service;

import java.util.List;

import com.kkwo.AM.container.Container;
import com.kkwo.AM.dao.MemberDao;
import com.kkwo.AM.dto.Member;

public class MemberService {
	MemberDao memberDao;

	public MemberService() {
		memberDao = Container.memberDao;
	}

	public List<Member> getMembers() {
		return memberDao.getMembers();
	}

	public int setNewId() {
		return memberDao.setNewId();
	}

	public Object isJoinableLoginId(String loginId) {
		return memberDao.isJoinableLoginId(loginId);
	}

	public void add(Member member) {
		memberDao.add(member);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}
}

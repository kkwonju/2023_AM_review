package com.kkwo.AM.dao;

import java.util.ArrayList;
import java.util.List;

import com.kkwo.AM.dto.Member;

public class MemberDao extends Dao {
	public List<Member> members;

	public MemberDao() {
		members = new ArrayList<>();
	}

	@Override
	public int getLastId() {
		return lastId;
	}

	public int setNewId() {
		int newId = lastId + 1;
		return newId;
	}

	public void add(Member member) {
		members.add(member);
		lastId++;
	}

	public List<Member> getMembers() {
		return members;
	}

	public Member isJoinableLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public Member getMemberByLoginId(String loginId) {
		for(Member member : members) {
			if(member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}
}

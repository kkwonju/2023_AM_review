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

}

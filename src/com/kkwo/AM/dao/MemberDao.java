package com.kkwo.AM.dao;

import java.util.ArrayList;
import java.util.List;

import com.kkwo.AM.dto.Member;

public class MemberDao {
	public List<Member> members;
	
	public MemberDao() {
		members = new ArrayList<>();
	}
}

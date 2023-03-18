package com.kkwo.AM.controller;

import com.kkwo.AM.dto.Member;

public abstract class Controller {
	
	public static Member loginedMember = null;
	
	public static boolean isLogined() {
		if(loginedMember == null) {
			return false;
		}
		return true;
	}
	
	abstract void makeTestData();
}
package com.lin.baseTest;

import java.util.Date;

public class Test {

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));

		Long uid = new Date().getTime(); 
		Date date = new Date(); 
		System.out.println(uid);
		System.out.println(date);
	}

}

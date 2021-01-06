package com.company.poly;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {

		// 컨테이너 구동
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		TV lg = (TV) ctx.getBean("lg");

		lg.trunOn();
		lg.soundUp();
		lg.soundDown();
		lg.trunOff();

		TV tv = (TV) ctx.getBean("samsung");
		System.out.println(lg==tv?"같은 객체":"다른 객체");
	}
}
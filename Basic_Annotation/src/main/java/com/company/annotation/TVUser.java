package com.company.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {

		// 컨테이너 구동
		ApplicationContext ctx = new ClassPathXmlApplicationContext("Config2.xml");
		TV lg = (TV) ctx.getBean("tv");

		lg.trunOn();
		lg.soundUp();
		lg.soundDown();
		lg.trunOff();

	}
}
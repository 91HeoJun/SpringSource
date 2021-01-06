package com.company.student;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentMain {

	public static void main(String[] args) {

//		1. 일반 개발 방식
//		StudentInfo info = new StudentInfo(new Student("김삿갓", "30", "3학년", "2반"));
//		info.getStudentInfo();
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config2.xml");
		
		StudentInfo info = (StudentInfo) ctx.getBean("info");
		info.getStudentInfo();

	}
}
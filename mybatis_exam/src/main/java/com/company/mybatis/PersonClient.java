package com.company.mybatis;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.PersonVO;
import com.company.service.PersonService;

public class PersonClient {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		PersonService service = (PersonService) ctx.getBean("person");
		
		service.insertPerson("Heo0119", "허진");

		service.updatePerson("kim4321", "킴닭");
		
		service.deletePerson("Heo911116");
		
		System.out.println(service.get("kim4321"));
		
		
		List<PersonVO> list = service.list();
		for (PersonVO vo : list) {
			System.out.println(vo);
		}

		
	}

}
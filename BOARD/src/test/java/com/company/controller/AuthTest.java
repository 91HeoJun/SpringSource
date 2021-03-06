package com.company.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class) // 테스트 클래스로서 스프링 안에서 실행
public class AuthTest {

	@Autowired
	private DataSource ds;

	@Test // 테스트 메소드(필수조건 : public, void, 파라미터는 없음)
	public void test() {
		log.info("---- 테스트 호출 ... ----");
		
		String sql = "insert into spring_member_auth(userid, auth) values(?, ?)";
		
		for (int i = 0; i < 100; i++) {
			try(Connection con = ds.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql)) {
				
				if (i < 80) {
					pstmt.setString(1, "user" + i);
					pstmt.setString(2, "ROLE_MEMBER");
					
				} else if(i < 90){
					pstmt.setString(1, "manager" + i);
					pstmt.setString(2, "ROLE_MANAGER");					
				} else {
					pstmt.setString(1, "admin" + i);
					pstmt.setString(2, "ROLE_ADMIN");				
				}
				pstmt.executeUpdate();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}

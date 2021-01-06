package com.company.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public int insert(MemberVO member) {
		System.out.println("====> jdbcTemplate insert");
			String sql = "insert into member(userid, password, name, gender, email) values(?, ?, ?, ?, ?)";

			int result = jdbcTemplate.update(sql, member.getUserid(), member.getPassword(), member.getName(),  member.getGender(), member.getEmail());
			
		return result;
	}
	
	public int update(MemberVO member) {
		System.out.println("====> jdbcTemplate update");
			String sql = "update member set password=? where userid=?";

			int result = jdbcTemplate.update(sql, member.getPassword(), member.getUserid());
			
		return result;
	}
	
	public int delete(MemberVO member) {
		System.out.println("====> jdbcTemplate delete");
			String sql = "delete from member where userid=? and password=?";
			
			int result = jdbcTemplate.update(sql, member.getUserid(), member.getPassword());
			
		return result;
	}
	
	public MemberVO getRow(MemberVO member) {
		System.out.println("====> jdbcTemplate getRow");
			String sql = "select * from member where userid=? and password=?";
			
			Object args[]= {member.getUserid(), member.getPassword()};
			
		return jdbcTemplate.queryForObject(sql, args, new MemberRowMapper());
	}
	
	public List<MemberVO> getList(){
		System.out.println("====> jdbcTemplate getList");

		String sql = "select * from member";

		return jdbcTemplate.query(sql, new MemberRowMapper());
	}
}

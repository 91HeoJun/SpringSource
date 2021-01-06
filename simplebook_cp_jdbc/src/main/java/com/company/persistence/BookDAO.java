package com.company.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.BookVO;

@Repository
public class BookDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	//삽입
	public int insert(BookVO vo) {
	System.out.println("====> jdbcTemplate insert");
		
		String sql = "insert into bookTBL values(?, ?, ?, ?)";
		int result = jdbcTemplate.update(sql, vo.getCode(), vo.getTitle(), vo.getWriter(), vo.getPrice());
			
	return result;		
	}
	
	// 삭제
	public int delete(int code) {
		System.out.println("====> jdbcTemplate delete");
		String sql = "delete from bookTBL where code=?";
		
		return jdbcTemplate.update(sql, code);	
	}
	
	// 수정
	public int update(BookVO vo) { // 가격만 변경
		System.out.println("====> jdbcTemplate update");
		String sql = "update bookTBL set price=? where code=?";
		
		int result = jdbcTemplate.update(sql, vo.getPrice(), vo.getCode());
			
		return result;		
	}
	
	// 단일조회
	public BookVO getRow(int code) {
		System.out.println("====> jdbcTemplate getRow");
		String sql = "select * from bookTBL where code=?";
		
		Object args[]= {code};
			
		return jdbcTemplate.queryForObject(sql, args, new BookRowMapper());
	}

	// 전체조회
	public List<BookVO> getList() {
		System.out.println("====> jdbcTemplate getList");

		String sql = "select * from bookTBL";
			
		return jdbcTemplate.query(sql, new BookRowMapper());
	}
}
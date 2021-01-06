package com.company.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.company.domain.BoardVO;


//@Repository : 빈을 생성하는 역할(= new BoardDAO)
@Repository
public class BoardDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 삽입
	public int insert(BoardVO vo) {
		System.out.println("===> Spring JDBC insert");

			String sql = "insert into spring_board(bno, title, content, writer) values(seq_board.nextval,?,?,?)";

			int result = jdbcTemplate.update(sql, vo.getTitle(), vo.getContent(), vo.getWriter());
		
			return result;
	}

	// 전체조회
	public List<BoardVO> getList() {
		System.out.println("===> Spring JDBC getList");
		
			String sql = "select * from spring_board";

		return jdbcTemplate.query(sql, new BoardRowMapper());
	}

	// Update
	public int UpdateBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC update");
		
			String sql = "update spring_board set title=?, content=?, updatedate=sysdate where bno=?";
			
			int result = jdbcTemplate.update(sql, vo.getTitle(), vo.getContent(), vo.getBno());
			
		return result;
	}
	
	// Delete
	public int DeleteBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC delete");
		
			String sql = "delete from spring_board where bno=?";
			
			int result = jdbcTemplate.update(sql, vo.getBno());
			
		return result;
	}
	
	// 개별조회
	public BoardVO getRow(int bno) {
		System.out.println("===> Spring JDBC getRow");

			String sql = "select * from spring_board where bno=?";

			Object[] args = {bno};
			
			BoardVO vo = jdbcTemplate.queryForObject(sql, args, new BoardRowMapper());

		return vo;
	}

}

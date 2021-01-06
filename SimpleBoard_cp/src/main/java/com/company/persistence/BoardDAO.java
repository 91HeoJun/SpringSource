package com.company.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.domain.BoardVO;


//@Repository : 빈을 생성하는 역할(= new BoardDAO)
@Repository
public class BoardDAO {
	
	@Autowired
	private DataSource ds;
	

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 삽입
	public int insert(BoardVO vo) {
		int result = 0;

		try {
			String sql = "insert into spring_board(bno, title, content, writer) values(seq_board.nextval,?,?,?)";
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			result = pstmt.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 전체조회
	public List<BoardVO> getList() {
		List<BoardVO> list = new ArrayList<>();

		try {
			con = ds.getConnection();
			String sql = "select * from spring_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegdate(rs.getDate("regdate"));
				vo.setUpdatedate(rs.getDate("updatedate"));

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// Update
	public int UpdateBoard(BoardVO vo) {
		int result = 0;
		try {
			con = ds.getConnection();
			String sql = "update spring_board set title=?, content=?, updatedate=sysdate where bno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getBno());
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// Delete
	public int DeleteBoard(BoardVO vo) {
		int result = 0;
		
		try {
			String sql = "delete from spring_board where bno=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getBno());
			result = pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 개별조회
	public BoardVO getRow(int bno) {
		BoardVO vo = null;
		
		try {
			String sql = "select * from spring_board where bno=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setRegdate(rs.getDate("regdate"));
				vo.setUpdatedate(rs.getDate("updatedate"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

}

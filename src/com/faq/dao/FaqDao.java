package com.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.common.JDBCTemplate;
import com.faq.dto.FaqDto;

public class FaqDao extends JDBCTemplate {
	
	//전체 조회
	public List<FaqDto> faq_selectAll(){
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<FaqDto> res = new ArrayList<>();
		
		String sql = " SELECT * FROM L_FAQ";
		
		try {
			stmt = con.createStatement();
			System.out.println("03. query 준비 : " + sql);
			
			rs = stmt.executeQuery(sql);
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				FaqDto faq = new FaqDto();
				faq.setFaq_no(rs.getInt(1));
				faq.setFaq_title(rs.getString(2));
				faq.setFaq_content(rs.getString(3));
										
				res.add(faq);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
			
		} finally {
			close(rs);
			close(stmt);
			close(con);
			System.out.println("05. db 종료 \n");
		}
		return res;
	}
	
	//추가
	public int faq_insert(FaqDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = "INSERT INTO L_FAQ VALUES((SELECT COUNT(NOTICE_NO) FROM L_NOTICE) + 1, ?, ?)";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getFaq_title());
			pstm.setString(2, dto.getFaq_content());
			System.out.println("03. query 준비 : " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
			
		} finally {
			close(pstm);
			close(con);
			System.out.println("05. db 종료\n");
		}
		return res;
	}
	
	//수정
	public int faq_update(FaqDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " UPDATE L_FAQ SET FAQ_TITLE=?, FAQ_CONTENT=? WHERE FAQ_NO=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getFaq_title());
			pstm.setString(2, dto.getFaq_content());
			pstm.setInt(3, dto.getFaq_no());
			System.out.println("03. query 준비 : " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
			if(res>0) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 오류");
			e.printStackTrace();
			
		} finally {
			close(pstm);
			close(con);
			System.out.println("05. db 종료 \n");
		}
		return res;
	}
	
	//삭제
	public int faq_delete(int faq_no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " DELETE FROM L_FAQ WHERE FAQ_NO=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, faq_no);
			System.out.println("03. query 준비  : " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
			
		} finally {
			close(pstm);
			close(con);
			System.out.println("05. db 종료 \n");
		}
		return res;
	}
}

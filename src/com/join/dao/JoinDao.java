package com.join.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.common.JDBCTemplate;
import com.join.dto.JoinDto;

public class JoinDao extends JDBCTemplate {
	public String idChk(Connection con, String user_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String res = null;
		String sql = "SELECT * FROM L_MEMBER WHERE USER_ID = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			System.out.println("3. 쿼리문 준비: " + sql);
			
			rs = pstmt.executeQuery();
			System.out.println("4. 쿼리문 실행 및 리턴");
			
			while(rs.next()) {
				res = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			System.out.println("5. db종료\n");
		}
		return res;
	}
	public int join(Connection con, JoinDto dto) {
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "INSERT INTO L_MEMBER VALUES(?, ?, ?, ?, NULL, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getUser_pw());
			pstmt.setString(3, dto.getUser_name());
			pstmt.setString(4, dto.getNick_name());
			pstmt.setString(5, dto.getUser_addr());
			pstmt.setString(6, dto.getUser_phone());
			pstmt.setString(7, dto.getUser_email());
			pstmt.setString(8, dto.getMember());
			pstmt.setString(9, dto.getBiz_num());
			pstmt.setString(10,  dto.getUser_addr_de());
			System.out.println("3. 쿼리문 준비: " + sql);
			
			res = pstmt.executeUpdate();
			System.out.println("4. 쿼리문 실행 및 리턴");
		} catch (SQLException e) {
			System.out.println("3/4 단게 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			System.out.println("5. db종료\n");
		}
		return res;
	}
}

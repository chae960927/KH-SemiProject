package com.report.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.common.JDBCTemplate;
import com.login.dto.UserDto;
import com.report.dto.reportDto;

public class reportDao extends JDBCTemplate {
	
	public UserDto getRepUser(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		UserDto res = new UserDto();
		String sql = "SELECT * FROM L_MEMBER WHERE USER_ID = ?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			System.out.println("3. 쿼리문 준비: " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("4. 쿼리문 실행 및 리턴");
			
			while(rs.next()) {
				res.setUser_id(rs.getString(1));
				res.setUser_pw(rs.getString(2));
				res.setUser_name(rs.getString(3));
				res.setNick_name(rs.getString(4));
				res.setUser_img(rs.getString(5));
				res.setUser_addr(rs.getString(6));
				res.setUser_phone(rs.getString(7));
				res.setUser_email(rs.getString(8));
				res.setMember(rs.getString(9));
				res.setBiz_num(rs.getString(10));
				res.setUser_addr_de(rs.getString(11));
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5. db종료\n");
		}
		return res;
	}
	
	
	public int insertRep(reportDto dto) {
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "INSERT INTO L_REPORT VALUES((SELECT COUNT(REP_NO)+1 FROM L_REPORT), ?, ?, ?, ?, ?, 0, SYSDATE, ?, ?)";
		Connection con = getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getRep_name());
			pstmt.setString(2, dto.getRep_id());
			pstmt.setString(3, dto.getRep_member());
			pstmt.setString(4, dto.getRep_content());
			pstmt.setString(5, dto.getRep_reason());
			pstmt.setInt(6, dto.getRep_listnumber());
			pstmt.setInt(7, dto.getBoard());
			
			System.out.println("3. 쿼리문 준비: " + sql);
			
			res = pstmt.executeUpdate();
			System.out.println("4. 쿼리문 실행 및 리턴");
		} catch (SQLException e) {
			System.out.println("3/4 단게 오류");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
			System.out.println("5. db종료\n");
		}
		return res;
	}
	
	public List<reportDto> selectAll(){
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<reportDto> res = new ArrayList<>();
		
		String sql = " SELECT * FROM L_REPORT";
		
		try {
			stmt = con.createStatement();
			System.out.println("03. query 준비 : " + sql);
			
			rs = stmt.executeQuery(sql);
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				reportDto report = new reportDto();
				report.setRep_no(rs.getInt(1));
				report.setRep_name(rs.getString(2));
				report.setRep_id(rs.getString(3));
				report.setRep_member(rs.getString(4));
				report.setRep_content(rs.getString(5));
				report.setRep_reason(rs.getString(6));
				report.setRep_push(rs.getInt(7));
				report.setRep_regdate(rs.getDate(8));
				report.setRep_listnumber(rs.getInt(9));
				report.setBoard(rs.getInt(10));
										
				res.add(report);
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
		
	
	public List<UserDto> userAllList(){
		Connection con = getConnection();
		String sql  = "SELECT * FROM L_MEMBER ";
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<UserDto> res = new ArrayList<UserDto>();
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("03.query준비 : "+ sql);
			rs = pstm.executeQuery();
			System.out.println("04.query실행 및 리턴");
			while(rs.next()) {
				UserDto dto  = new UserDto();
				dto.setUser_id(rs.getString("USER_ID"));
				dto.setUser_pw(rs.getString("USER_PW"));
				dto.setUser_name(rs.getString("USER_NAME"));
				dto.setNick_name(rs.getString("NICK_NAME"));
				dto.setUser_img(rs.getString("USER_IMG"));
				dto.setUser_addr(rs.getString("USER_ADDR"));
				dto.setUser_phone(rs.getString("USER_PHONE"));
				dto.setUser_email(rs.getString("USER_EMAIL"));
				dto.setMember(rs.getString("MEMBER"));
				dto.setBiz_num(rs.getString("BIZ_NUM"));
				res.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 오류");
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05. db연결종료");
		}
		
		return res;
	}
	public int updateRole(String id, String role) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		String sql = "UPDATE L_MEMBER SET MEMBER=? WHERE USER_ID=?";
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, role);
			pstm.setString(2,id);
			System.out.println("03. query 준비 : "+sql);
			res= pstm.executeUpdate();
			System.out.println("04.query실행 및 리턴");
			if(res>0) {
				commit(con);
			}else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("05.db종료");
		}
		
		
		return res;
	}
	
	public int report_delete(int rep_no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = "DELETE FROM L_REPORT WHERE REP_NO=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, rep_no);
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

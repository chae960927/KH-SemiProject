package com.mypage_public.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.common.JDBCTemplate;
import com.mypage_public.dto.MypagePublicDto;
import com.pay.dto.PayDto;

public class MypagePublicDao extends JDBCTemplate {
	public MypagePublicDto selectOne(String user_id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MypagePublicDto res = new MypagePublicDto();
		String sql = "SELECT * FROM L_MEMBER WHERE USER_ID = ?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user_id);
			System.out.println("3. 쿼리문 준비: " + sql);
			
			rs = pstm.executeQuery();
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
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return res;
	}
	
	public int user_update(MypagePublicDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "UPDATE L_MEMBER SET USER_NAME=?, NICK_NAME=?, USER_PHONE=?, USER_EMAIL=?, USER_ADDR=?, USER_ADDR_DE=? WHERE USER_ID=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getUser_name());
			pstm.setString(2, dto.getNick_name());
			pstm.setString(3, dto.getUser_phone());
			pstm.setString(4, dto.getUser_email());
			pstm.setString(5, dto.getUser_addr());
			pstm.setString(6, dto.getUser_addr_de());
			pstm.setString(7, dto.getUser_id());
			System.out.println("3. 쿼리문 준비: " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("4. 쿼리문 실행 및 리턴");
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료 \n");
		}
		return res;
	}
	
	public int deleteUser(String user_id, String user_pw) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "DELETE FROM L_MEMBER WHERE USER_ID = ? AND USER_PW = ?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user_id);
			pstm.setString(2, user_pw);
			System.out.println("3. 쿼리문 준비: " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("4. 쿼리문 실행 및 리턴");
			
		} catch (SQLException e) {
			System.out.println("3/4 단게 오류");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료\n");
		}
		
		return res;
	}
	
	public int updatePw(String user_id, String user_pw) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "UPDATE L_MEMBER SET USER_PW = ? WHERE USER_ID = ?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user_pw);
			pstm.setString(2, user_id);
			System.out.println("3. 쿼리문 준비: " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("4. 쿼리문 실행 및 리턴");
			
		} catch (SQLException e) {
			System.out.println("3/4 단게 오류");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료\n");
		}
		
		return res;
	}
	
	//프로필 업로드
	public int imgUpload(String user_id, String user_img) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "UPDATE L_MEMBER SET USER_IMG = ? WHERE USER_ID = ?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user_img);
			pstm.setString(2, user_id);
			System.out.println("3. 쿼리문 준비: " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("4. 쿼리문 실행 및 리턴");
			
		} catch (SQLException e) {
			System.out.println("3/4 단게 오류");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료\n");
		}
		
		return res;
	}
	
	//결제 테이블에서 본인 기업에 해당 하는 컬럼 select
	public List<PayDto> selectCompany(String user_id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM L_PAY WHERE PAY_COMPANY = ?";
		List<PayDto> res = new ArrayList<PayDto>();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user_id);
			System.out.println("3. 쿼리문 준비: " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("4. 쿼리문 실행 및 리턴");
			
			while(rs.next()) {
				PayDto tmp = new PayDto();
				tmp.setPay_no(rs.getString(1));
				tmp.setPay_user(rs.getString(2));
				tmp.setPay_company(rs.getString(3));
				tmp.setPay_name(rs.getString(4));
				tmp.setPay_money(rs.getInt(5));
				tmp.setPay_info(rs.getString(6));
				tmp.setPay_regdate(rs.getDate(7));
				
				res.add(tmp);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4 단게 오류");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료\n");
		}
		
		return res;
	}
}

package com.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.common.JDBCTemplate;
import com.login.dto.UserDto;

public class UserDao extends JDBCTemplate {
	
	public UserDto login(String id, String pw) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM L_MEMBER WHERE USER_ID=? AND USER_PW=? ";
		UserDto dto = new UserDto();

		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			System.out.println("3. query 준비"+sql);
			
			rs = ps.executeQuery();
			System.out.println("4. query 실행 및 리턴");
			while(rs.next()) {
				dto.setUser_id(rs.getString(1));
				dto.setUser_pw(rs.getString(2));
				dto.setUser_name(rs.getString(3));
				dto.setNick_name(rs.getString(4));
				dto.setUser_img(rs.getString(5));
				dto.setUser_addr(rs.getString(6));
				dto.setUser_phone(rs.getString(7));
				dto.setUser_email(rs.getString(8));
				dto.setMember(rs.getString(9));
				dto.setBiz_num(rs.getString(10));
				dto.setUser_addr_de(rs.getString(11));
			}

		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally {
			if(dto.getUser_id()!=null) {
				close(rs);
			}
			close(ps);
			close(con);
			System.out.println("5. db 종료\n");
		}
		
		return dto;
	}
	
	
	
}


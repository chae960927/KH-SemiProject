package com.pay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.common.JDBCTemplate;
import com.pay.dto.PayDto;

public class PayDao extends JDBCTemplate {
	//결제정보 집어넣기
	public int insert_pay(PayDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "INSERT INTO L_PAY VALUES(?, ?, ?, ?, ?, ?, SYSDATE)";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getPay_no());
			pstm.setString(2, dto.getPay_user());
			pstm.setString(3, dto.getPay_company());
			pstm.setString(4, dto.getPay_name());
			pstm.setInt(5, dto.getPay_money());
			pstm.setString(6, dto.getPay_info());
			System.out.println("3. 쿼리문 준비: " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("4. 쿼리문 실행 및 리턴");
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("5. db 종료\n");
		}
		return res;
	}
	
	//결제정보 꺼내오기
	public List<PayDto> select_pay(String user_id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<PayDto> res = new ArrayList<PayDto>();
		String sql = "SELECT * FROM L_PAY WHERE PAY_USER = ?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user_id);
			System.out.println("3. 쿼리문 준비: " + sql);
			
			rs = pstm.executeQuery();
			
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
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5. db 종료\n");
		}
		return res;
	}
}

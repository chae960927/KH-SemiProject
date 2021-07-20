package com.join.controller;

import java.sql.Connection;

import com.common.JDBCTemplate;
import com.join.dao.JoinDao;
import com.join.dto.JoinDto;

public class JoinController extends JDBCTemplate {
	private JoinDao dao = new JoinDao();
	
	public String idChk(String user_id) {
		Connection con = getConnection();
		String res = dao.idChk(con, user_id);
		
		close(con);
		return res;
	}
	public int join(JoinDto dto) {
		Connection con = getConnection();
		int res = dao.join(con, dto);
		
		if(res > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		return res;
	}
}

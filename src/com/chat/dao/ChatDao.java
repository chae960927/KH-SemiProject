package com.chat.dao;

import static com.common.JDBCTemplate.close;
import static com.common.JDBCTemplate.commit;
import static com.common.JDBCTemplate.getConnection;
import static com.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chat.dto.ChatDto;

public class ChatDao {
	
	public int insert(ChatDto dto) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		int res=0;
		String sql = " INSERT INTO L_CHAT VALUES(?, ?, ?, ?, SYSDATE) ";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getChat_num());
			ps.setString(2, dto.getFrom_user());
			ps.setString(3, dto.getTo_user());
			ps.setString(4, dto.getContent());
			System.out.println("03. query 준비"+sql);
			
			res = ps.executeUpdate();
			System.out.println("04. query 실행");
			if(res>0) {
				commit(con);
			}else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Chat insert 에러");
		}finally {
			close(ps);
			close(con);
		}
		return res;
	}
	
	public List<ChatDto> selectAll(ChatDto dto){
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ChatDto> list = new ArrayList<ChatDto>();
		String sql = " SELECT * FROM L_CHAT WHERE CHAT_NUM = ? AND ((FROM_USER=? AND TO_USER=?) OR (FROM_USER=? AND TO_USER=?)) ORDER BY CHAT_TIME ";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getChat_num());
			ps.setString(2, dto.getFrom_user());
			ps.setString(3, dto.getTo_user());
			ps.setString(4, dto.getTo_user());
			ps.setString(5, dto.getFrom_user());
			System.out.println("03. query 준비"+sql);
			
			rs = ps.executeQuery();
			System.out.println("04. query 실행");
			while(rs.next()) {
				
				ChatDto li = new ChatDto();
		
				li.setFrom_user(rs.getString("FROM_USER"));
				li.setContent(rs.getString(4));
				li.setChat_time(rs.getDate(5));
				
				list.add(li);
			}
			
		} catch (SQLException e) {
			System.out.println("Chat selectAll 에러");
			e.printStackTrace();
		}finally {
			if(rs!=null) {close(rs);}
			close(ps);
			close(con);
		}
		return list;
	}
}
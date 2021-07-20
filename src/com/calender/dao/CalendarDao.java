package com.calender.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.calender.dto.CalendarDto;
import com.common.JDBCTemplate;
public class CalendarDao extends JDBCTemplate {
	public int insert(CalendarDto dto) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		int res = 0;
		String sql = " INSERT INTO L_CALENDAR VALUES((SELECT MAX(CAL_NUM) FROM L_CALENDAR) + 1, ?, ?, ?, ?) ";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getCal_title());
			ps.setString(2, dto.getCal_start());
			ps.setString(3, dto.getCal_end());
			ps.setString(4,dto.getCal_user());
			System.out.println("03. query 준비"+sql);
			
			res = ps.executeUpdate();
			System.out.println("04. query 실행");
			if(res>0) {
				commit(con);
			}else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("calendar insert 에러");
			e.printStackTrace();
		}finally {
			close(ps);
			close(con);
			System.out.println("05. 종료");
		}
		
		
		return res;
	}
	
	public List<CalendarDto> select(String id){
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CalendarDto> list = new ArrayList<CalendarDto>();
		String sql = "SELECT CAL_NUM, CAL_TITLE, CAL_START, CAL_END FROM L_CALENDAR WHERE CAL_USER = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			System.out.println("03. query 준비"+sql);
			
			rs = ps.executeQuery();
			System.out.println("04. query 실행");
			while(rs.next()) {
				CalendarDto dto = new CalendarDto(rs.getString(2), rs.getString(3), rs.getString(4));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("Calendar select 실패");
			e.printStackTrace();
		}finally {
			if(rs!=null) {close(rs);}
			close(ps);
			close(con);
			System.out.println("05. 종료");
		}
		return list;
	}
	
	public int selectOne(CalendarDto dto) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT CAL_NUM FROM L_CALENDAR WHERE CAL_TITLE=? AND CAL_START=? AND CAL_END=?";
		int res = 0;
		System.out.println(dto.getCal_start());
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getCal_title());
			ps.setString(2, dto.getCal_start());
			ps.setString(3, dto.getCal_end());
			System.out.println("03. query 준비"+sql);
			
			rs = ps.executeQuery();
			System.out.println("04. query 실행");
			while(rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("selectOne 에러");
			e.printStackTrace();
		}finally {
			if(rs!=null) {close(rs);}
			close(ps);
			close(con);
			System.out.println("05. 종료");
		}
		return res;
	}
	
	public int update(CalendarDto dto, int num) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		String sql = " UPDATE L_CALENDAR SET CAL_TITLE=?, CAL_START=?, CAL_END=? WHERE CAL_NUM=?";
		System.out.println(num);
		int res=0;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1,dto.getCal_title() );
			ps.setString(2, dto.getCal_start());
			ps.setString(3, dto.getCal_end());
			ps.setInt(4, num);
			System.out.println("03.query 준비"+sql);
			
			res = ps.executeUpdate();
			System.out.println("04. query 실행");
			if(res>0) {
				commit(con);
			}else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("update 에러");
			e.printStackTrace();
		}finally {
			close(ps);
			close(con);
			System.out.println("05. 종료");
		}
		
		return res;
	}
	
	public int delete(CalendarDto dto) {
		Connection con = getConnection();
		PreparedStatement ps = null;
		int res = 0;
		String sql = "DELETE L_CALENDAR WHERE CAL_TITLE=? AND CAL_START=? AND CAL_END=?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getCal_title());
			ps.setString(2, dto.getCal_start());
			ps.setString(3, dto.getCal_end());
			System.out.println("03. query 준비"+sql);
			
			res = ps.executeUpdate();
			System.out.println("04. query 실행");
			if(res>0) {
				commit(con);
			}else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("delete 에러");
			e.printStackTrace();
		}finally {
			close(ps);
			close(con);
			System.out.println("05. 종료");
		}
		
		return res;
	}
}
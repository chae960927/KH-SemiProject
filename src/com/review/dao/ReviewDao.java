package com.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.common.JDBCTemplate;
import com.review.dto.ReviewDto;

public class ReviewDao extends JDBCTemplate {
	
	public int insert_rev(Connection con, ReviewDto dto) {
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "insert into L_REVIEW values((select max(view_no) from L_REVIEW) + 1, ?, ?, ?, SYSDATE)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getCons_no());
			pstmt.setString(2, dto.getView_writer());
			pstmt.setInt(3, dto.getView_num());
			System.out.println("3. 쿼리문 준비: "+ sql);
			
			res = pstmt.executeUpdate();
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
			close(pstmt);
			System.out.println("5. db 종료\n");
		}
		
		return res;
	}
	
	public List<ReviewDto> selectAll(Connection con) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM L_REVIEW ORDER BY CONS_NO DESC";
		List<ReviewDto> list = new ArrayList<ReviewDto>();
		
		try {
			pstmt = con.prepareStatement(sql);
			System.out.println("3. 쿼리문 준비: " + sql);
			rs = pstmt.executeQuery();
			System.out.println("4. 쿼리문 실힝 및 리턴");
			
			while(rs.next()) {
				ReviewDto dto = new ReviewDto(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getDate(5));
				
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			System.out.println("5. db 종료\n");
		}
		
		return list;
	}
}

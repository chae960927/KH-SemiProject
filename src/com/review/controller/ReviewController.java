package com.review.controller;

import java.sql.Connection;
import java.util.List;

import com.common.JDBCTemplate;
import com.review.dao.ReviewDao;
import com.review.dto.ReviewDto;

public class ReviewController extends JDBCTemplate {
	ReviewDao dao = new ReviewDao();
	//별점 추가
	public int insert_rev(ReviewDto dto) {
		Connection con = getConnection();
		int res = dao.insert_rev(con, dto);
		
		close(con);
		return res;
	}
	
	//별점 목록 조회
	public List<ReviewDto> selectAll() {
		Connection con = getConnection();
		List<ReviewDto> res = dao.selectAll(con);
		
		close(con);
		return res;
	}
}

package com.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.common.JDBCTemplate;
import com.cons.dto.ConDto;
import com.notice.dto.NoticeDto;

public class NoticeDao extends JDBCTemplate {
	
	//글상세보기 + 조회수 증가(detail)
	//하나의 서비스 안에 여러가지 DB관련 로직이 섞여 있다.
	public NoticeDto notice_viewDetail(int notice_no) {
		//둘중에 하나라고 잘못되면 처리가 안 된다.
		int res = updateReadCount(notice_no);
		
		if(res > 0) {
			//true이면 noticeDao에 notice_selectOne를 실행하겠다.
			return notice_selectOne(notice_no);
		} else {
			//false이면 null을 리턴하겠다.
			return null;
		}
	}
	
	//조회수 증가(detail 클릭시)
	public int updateReadCount(int notice_no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " UPDATE L_NOTICE SET NOTICE_PUSH = NOTICE_PUSH + 1 WHERE NOTICE_NO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, notice_no);
			System.out.println("03. query 준비 : " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
			
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
	
	//페이지 처리
	public int getAllCount() {
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT COUNT(*) FROM L_NOTICE ";
		int count = 0;
		
		try {
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return count;
	}

	//전체 조회
	public List<NoticeDto> notice_selectAll(int page){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<NoticeDto> res = new ArrayList<>();
		
		String sql = "SELECT * FROM (SELECT ROWNUM AS ROW_NUM, A.* FROM (SELECT * FROM L_NOTICE ORDER BY NOTICE_NO DESC) A) WHERE ROW_NUM<=? AND ROW_NUM>?";
		int pageP = (page-1)*10;
		int pageN = page*10;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, pageN);
			pstm.setInt(2, pageP);
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				NoticeDto notice = new NoticeDto();
				notice.setNotice_no(rs.getInt(2));
				notice.setNotice_cat(rs.getString(3));
				notice.setNotice_writer(rs.getString(4));
				notice.setNotice_title(rs.getString(5));
				notice.setNotice_content(rs.getString(6));
				notice.setNotice_push(rs.getInt(7));
				notice.setNotice_regdate(rs.getDate(8));
										
				res.add(notice);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
			
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05. db 종료 \n");
		}
		return res;
	}
	
	//하나만 조회(detail)
	public NoticeDto notice_selectOne(int notice_no){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		NoticeDto res = new NoticeDto();
		
		String sql = " SELECT * FROM L_NOTICE WHERE NOTICE_NO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, notice_no);
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			if(rs.next()) {
				res.setNotice_no(rs.getInt(1));
				res.setNotice_cat(rs.getString(2));
				res.setNotice_writer(rs.getString(3));
				res.setNotice_title(rs.getString(4));
				res.setNotice_content(rs.getString(5));
				res.setNotice_push(rs.getInt(6));
				res.setNotice_regdate(rs.getDate(7));
			}
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
			
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05. db 종료\n");
		}
		
		return res;
	}
	
	//검색 조회
	public List<NoticeDto> selectSearch(String search) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<NoticeDto> res = new ArrayList<NoticeDto>();
		String sql = "SELECT * FROM L_NOTICE WHERE (NOTICE_TITLE LIKE '%" + search + "%') OR (NOTICE_CONTENT LIKE '%" + search + "%') ORDER BY NOTICE_NO DESC";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. 쿼리문 준비: " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("4. 쿼리문 실행 및 리턴");
			while(rs.next()) {
				NoticeDto notice = new NoticeDto();
				notice.setNotice_no(rs.getInt(1));
				notice.setNotice_cat(rs.getString(2));
				notice.setNotice_writer(rs.getString(3));
				notice.setNotice_title(rs.getString(4));
				notice.setNotice_content(rs.getString(5));
				notice.setNotice_push(rs.getInt(6));
				notice.setNotice_regdate(rs.getDate(7));
										
				res.add(notice);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("5. db 종료\n");
		}
		return res;
	}
	
	//추가
	public int notice_insert(NoticeDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = "INSERT INTO L_NOTICE VALUES((SELECT COUNT(NOTICE_NO) FROM L_NOTICE) + 1, ?, ?, ?, ?, ?, SYSDATE)";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getNotice_cat());
			pstm.setString(2, dto.getNotice_writer());
			pstm.setString(3, dto.getNotice_title());
			pstm.setString(4, dto.getNotice_content());
			pstm.setInt(5, dto.getNotice_push());
			System.out.println("03. query 준비 : " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
			
		} finally {
			close(pstm);
			close(con);
			System.out.println("05. db 종료\n");
		}
		return res;
	}
	
	//수정
	public int notice_update(NoticeDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " UPDATE L_NOTICE SET NOTICE_TITLE=?, NOTICE_CONTENT=? WHERE NOTICE_NO=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getNotice_title());
			pstm.setString(2, dto.getNotice_content());
			pstm.setInt(3, dto.getNotice_no());
			System.out.println("03. query 준비 : " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
			if(res>0) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 오류");
			e.printStackTrace();
			
		} finally {
			close(pstm);
			close(con);
			System.out.println("05. db 종료 \n");
		}
		return res;
	}
	
	//삭제
	public int notice_delete(int notice_no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " DELETE FROM L_NOTICE WHERE NOTICE_NO=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, notice_no);
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

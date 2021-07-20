package com.cons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.common.JDBCTemplate;
import com.cons.dto.ConDto;
import com.info.dto.InfoDto;
import com.sup.dto.SupDto;

public class ConDao extends JDBCTemplate {
	
	//글상세보기 + 조회수 증가(detail)
	//하나의 서비스 안에 여러가지 DB관련 로직이 섞여 있다.
	public ConDto con_viewDetail(int con_no) {
		//둘중에 하나라고 잘못되면 처리가 안 된다.
		int res = updateReadCount(con_no);
		
		if(res > 0) {
			//true이면 conDao에 con_selectOne를 실행하겠다.
			return con_selectOne(con_no);
		} else {
			//false이면 null을 리턴하겠다.
			return null;
		}
	}
	
	//조회수 증가(detail 클릭시)
	public int updateReadCount(int con_no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " UPDATE L_CONS SET CONS_PUSH = CONS_PUSH + 1 WHERE CONS_NO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, con_no);
			System.out.println("03. query 준비 : " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
			
		} finally {
			close(pstm);
			close(con);
			
		}
		
		return res;
	}
	
	//페이지 처리
	public int getAllCount() {
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT COUNT(*) FROM L_CONS ";
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
	public List<ConDto> con_selectAll(int page){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ConDto> res = new ArrayList<>();
		
		String sql = "SELECT * FROM (SELECT ROWNUM AS ROW_NUM, A.* "
				+ "FROM (SELECT * FROM L_CONS ORDER BY CONS_NO DESC) A) "
				+ "WHERE ROW_NUM<=? AND ROW_NUM>?";
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
				ConDto cond = new ConDto();
				cond.setcon_no(rs.getInt(2));
				cond.setcon_writer(rs.getString(3));
				cond.setcon_title(rs.getString(4));
				cond.setcon_content(rs.getString(5));
				cond.setUser_img(rs.getString(6));
				cond.setCon_push(rs.getInt(7));
				cond.setcon_regdate(rs.getDate(8));
										
				res.add(cond);
				
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
	public ConDto con_selectOne(int con_no){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ConDto res = new ConDto();
		
		String sql = " SELECT * FROM L_CONS WHERE CONS_NO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, con_no);
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			if(rs.next()) {
				res.setcon_no(rs.getInt(1));
				res.setcon_writer(rs.getString(2));
				res.setcon_title(rs.getString(3));
				res.setcon_content(rs.getString(4));
				res.setUser_img(rs.getString(5));
				res.setCon_push(rs.getInt(6));
				res.setcon_regdate(rs.getDate(7));
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
	
	//본인이 작성한 문의글만 출력
	public List<ConDto> selectMyself(int page, String user_id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM AS ROW_NUM, A.* FROM (SELECT * FROM L_CONS ORDER BY CONS_NO DESC) A) WHERE CONS_WRITER = ? AND ROW_NUM<=? AND ROW_NUM>? ORDER BY CONS_NO DESC";
		int pageP = (page-1)*10;
		int pageN = page*10;
		List<ConDto> res = new ArrayList<ConDto>();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user_id);
			pstm.setInt(2, pageN);
			pstm.setInt(3, pageP);
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				ConDto cond = new ConDto();
				cond.setcon_no(rs.getInt(2));
				cond.setcon_writer(rs.getString(3));
				cond.setcon_title(rs.getString(4));
				cond.setcon_content(rs.getString(5));
				cond.setUser_img(rs.getString(6));
				cond.setCon_push(rs.getInt(7));
				cond.setcon_regdate(rs.getDate(8));
										
				res.add(cond);
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
	
	//검색 조회
	public List<ConDto> selectSearch(String search) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ConDto> res = new ArrayList<ConDto>();
		String sql = "SELECT * FROM L_CONS WHERE (CONS_TITLE LIKE '%" 
				   + search + "%') OR (CONS_CONTENT LIKE '%" + search + "%') ORDER BY CONS_NO DESC";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. 쿼리문 준비: " + sql);
			rs = pstm.executeQuery();
			System.out.println("4. 쿼리문 실행 및 리턴");
			while(rs.next()) {
				ConDto tmp = new ConDto();
				tmp.setcon_no(rs.getInt(1));
				tmp.setcon_writer(rs.getString(2));
				tmp.setcon_title(rs.getString(3));
				tmp.setcon_content(rs.getString(4));
				tmp.setUser_img(rs.getString(5));
				tmp.setCon_push(rs.getInt(6));
				tmp.setcon_regdate(rs.getDate(7));
				res.add(tmp);
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
	public int con_insert(ConDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = "INSERT INTO L_CONS VALUES((SELECT COUNT(CONS_NO) FROM L_CONS) + 1, ?, ?, ?, NULL, 0, SYSDATE)";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getcon_writer());
			pstm.setString(2, dto.getcon_title());
			pstm.setString(3, dto.getcon_content());
			System.out.println("03. query 준비 : " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
			
		} finally {
			close(pstm);
			close(con);
			
		}
		
		return res;
	}
	
	//수정
	public int con_update(ConDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " UPDATE L_CONS SET CONS_TITLE=?, CONS_CONTENT=? WHERE CONS_NO=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getcon_title());
			pstm.setString(2, dto.getcon_content());
			pstm.setInt(3, dto.getcon_no());
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
	public int con_delete(int con_no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " DELETE FROM L_CONS WHERE CONS_NO=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, con_no);
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

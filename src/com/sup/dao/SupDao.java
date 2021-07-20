package com.sup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.common.JDBCTemplate;
import com.notice.dto.NoticeDto;
import com.sup.dto.SupDto;

public class SupDao extends JDBCTemplate {
	//글상세보기 + 조회수 증가(detail)
	//하나의 서비스 안에 여러가지 DB관련 로직이 섞여 있다.
	public SupDto sup_viewDetail(int sup_no) {
		//둘중에 하나라고 잘못되면 처리가 안 된다.
		int res = updateReadCount(sup_no);
		
		if(res == 1) {
			//true이면 supDao에 sup_selectOne를 실행하겠다.
			return sup_selectOne(sup_no);
		} else {
			//false이면 null을 리턴하겠다.
			return null;
		}
		
		
	}
	
	//조회수 증가(detail 클릭시)
	public int updateReadCount(int sup_one) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " UPDATE L_SUPPORT SET SUP_PUSH = SUP_PUSH+1 WHERE SUP_NO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, sup_one);
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
	
	//페이징 처리
	public int getAllCount() {
		Connection con = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = " SELECT COUNT(*) FROM L_SUPPORT ";
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
	public List<SupDto> sup_selectAll(int page){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<SupDto> res = new ArrayList<>();
		
		String sql = "SELECT * FROM (SELECT ROWNUM AS ROW_NUM, A.* FROM (SELECT * FROM L_SUPPORT ORDER BY SUP_NO DESC) A) WHERE ROW_NUM<=? AND ROW_NUM>?";
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
				SupDto sup = new SupDto();
				sup.setSup_no(rs.getInt(2));
				sup.setGroup_no(rs.getInt(3));
				sup.setGroup_sq(rs.getInt(4));
				sup.setTitle_tab(rs.getInt(5));
				sup.setSup_writer(rs.getString(6));
				sup.setSup_title(rs.getString(7));
				sup.setSup_content(rs.getString(8));
				sup.setSup_push(rs.getInt(9));
				sup.setSup_regdate(rs.getDate(10));
										
				res.add(sup);
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
	public List<SupDto> selectSearch(String search) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<SupDto> res = new ArrayList<SupDto>();
		String select = "SELECT SUP_WRITER FROM L_SUPPORT WHERE (SUP_TITLE LIKE '%" + search + "%') OR (SUP_CONTENT LIKE '%" + search + "%')";
		String sql = "SELECT * FROM L_SUPPORT WHERE GROUP_NO = (SELECT GROUP_NO FROM L_SUPPORT WHERE SUP_WRITER = (" + select + ")) ORDER BY GROUP_NO DESC, GROUP_SQ";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. 쿼리문 준비: " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("4. 쿼리문 실행 및 리턴");
			while(rs.next()) {
				SupDto sup = new SupDto();
				sup.setSup_no(rs.getInt(1));
				sup.setGroup_no(rs.getInt(2));
				sup.setGroup_sq(rs.getInt(3));
				sup.setTitle_tab(rs.getInt(4));
				sup.setSup_writer(rs.getString(5));
				sup.setSup_title(rs.getString(6));
				sup.setSup_content(rs.getString(7));
				sup.setSup_push(rs.getInt(8));
				sup.setSup_regdate(rs.getDate(9));
										
				res.add(sup);
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
	
	//본인이 작성한 문의글만 출력
	public List<SupDto> selectMyself(String user_id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM L_SUPPORT WHERE GROUP_NO = (SELECT GROUP_NO FROM L_SUPPORT WHERE SUP_WRITER = ?) ORDER BY GROUP_NO DESC, GROUP_SQ";
		List<SupDto> res = new ArrayList<>();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user_id);
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				SupDto sup = new SupDto();
				sup.setSup_no(rs.getInt(1));
				sup.setGroup_no(rs.getInt(2));
				sup.setGroup_sq(rs.getInt(3));
				sup.setTitle_tab(rs.getInt(4));
				sup.setSup_writer(rs.getString(5));
				sup.setSup_title(rs.getString(6));
				sup.setSup_content(rs.getString(7));
				sup.setSup_push(rs.getInt(8));
				sup.setSup_regdate(rs.getDate(9));
										
				res.add(sup);
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
	
	//문의글 하나만 조회(detail)
	public SupDto sup_selectOne(int sup_no){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		SupDto res = new SupDto();
		
		String sql = " SELECT * FROM L_SUPPORT WHERE SUP_NO=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, sup_no);
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			if(rs.next()) {
				res.setSup_no(rs.getInt(1));
				res.setGroup_no(rs.getInt(2));
				res.setGroup_sq(rs.getInt(3));
				res.setTitle_tab(rs.getInt(4));
				res.setSup_writer(rs.getString(5));
				res.setSup_title(rs.getString(6));
				res.setSup_content(rs.getString(7));
				res.setSup_push(rs.getInt(8));
				res.setSup_regdate(rs.getDate(9));
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
	
	//문의글 추가
	public int sup_insert(SupDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = "INSERT INTO L_SUPPORT VALUES((SELECT COUNT(SUP_NO) FROM L_SUPPORT) + 1, (SELECT COUNT(GROUP_NO) FROM L_SUPPORT) + 1, 1, 0, ?, ?, ?, 0, SYSDATE)";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getSup_writer());
			pstm.setString(2, dto.getSup_title());
			pstm.setString(3, dto.getSup_content());
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
	
	//문의글 수정
	public int sup_update(SupDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " UPDATE L_SUPPORT SET SUP_TITLE=?, SUP_CONTENT=? WHERE SUP_NO=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getSup_title());
			pstm.setString(2, dto.getSup_content());
			pstm.setInt(3, dto.getSup_no());
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
	
	//문의글 삭제
	public int sup_delete(int group_no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " DELETE FROM L_SUPPORT WHERE GROUP_NO=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, group_no);
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
	
	//답변 등록
	public int insertAnswer(SupDto parent, SupDto child) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "INSERT INTO L_SUPPORT VALUES((SELECT COUNT(SUP_NO) FROM L_SUPPORT) + 1, ?, ?, ?, ?, ?, ?, 0, SYSDATE)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, parent.getGroup_no());
			pstmt.setInt(2, parent.getGroup_sq() + 1);
			pstmt.setInt(3, parent.getTitle_tab() + 1);
			pstmt.setString(4, child.getSup_writer());
			pstmt.setString(5, child.getSup_title());
			pstmt.setString(6, child.getSup_content());
			System.out.println("3. query 준비 : " + sql);
			
			res = pstmt.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		
		return res;
	}
	
	//답변 수정
	public int updateAnswer(int group_no, int group_sq) {
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "UPDATE L_SUPPORT SET GROUP_SQ = GROUP_SQ + 1 WHERE GROUP_NO = ? AND GROUP_SQ > ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, group_no);
			pstmt.setInt(2, group_sq);
			System.out.println("3. query 준비 : " + sql);
			
			res = pstmt.executeUpdate();
			System.out.println("4. query 실행 및 리턴");
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		
		return res;
	}
}

package com.info.dao;

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

public class InfoDao extends JDBCTemplate {
	
	//글상세보기 + 조회수 증가(detail)
	//하나의 서비스 안에 여러가지 DB관련 로직이 섞여 있다.
	public InfoDto info_viewDetail(int info_no) {
		//둘중에 하나라고 잘못되면 처리가 안 된다.
		int res = updateReadCount(info_no);
		
		if(res == 1) {
			//true이면 infoDao에 info_selectOne를 실행하겠다.
			return info_selectOne(info_no);
		} else {
			//false이면 null을 리턴하겠다.
			return null;
		}
	}
	
	//조회수 증가(detail 클릭시)
	public int updateReadCount(int info_no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " UPDATE L_INFO SET INFO_PUSH = INFO_PUSH+1 WHERE INFO_NO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, info_no);
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
	
	//페이징
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
	public List<InfoDto> info_selectAll(int page){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<InfoDto> res = new ArrayList<>();
		
		String sql = "SELECT * FROM (SELECT ROWNUM AS ROW_NUM, A.* FROM (SELECT * FROM L_INFO ORDER BY INFO_NO DESC) A) WHERE ROW_NUM<=? AND ROW_NUM>?";
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
				InfoDto ifd = new InfoDto();
				ifd.setInfo_no(rs.getInt(2));
				ifd.setInfo_writer(rs.getString(3));
				ifd.setInfo_title(rs.getString(4));
				ifd.setInfo_content(rs.getString(5));
				ifd.setUser_img(rs.getString(6));
				ifd.setInfo_push(rs.getInt(7));
				ifd.setInfo_regdate(rs.getDate(8));
										
				res.add(ifd);
				
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
	public InfoDto info_selectOne(int info_no){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		InfoDto res = new InfoDto();
		
		String sql = " SELECT * FROM L_INFO WHERE INFO_NO=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, info_no);
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			if(rs.next()) {
				res.setInfo_no(rs.getInt(1));
				res.setInfo_writer(rs.getString(2));
				res.setInfo_title(rs.getString(3));
				res.setInfo_content(rs.getString(4));
				res.setUser_img(rs.getString(5));
				res.setInfo_push(rs.getInt(6));
				res.setInfo_regdate(rs.getDate(7));
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
	public List<InfoDto> selectMyself(int page, String user_id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM AS ROW_NUM, A.* FROM (SELECT * FROM L_INFO ORDER BY INFO_NO DESC) A) WHERE INFO_WRITER = ? AND ROW_NUM<=? AND ROW_NUM>? ORDER BY INFO_NO DESC";
		int pageP = (page-1)*10;
		int pageN = page*10;
		List<InfoDto> res = new ArrayList<InfoDto>();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user_id);
			pstm.setInt(2, pageN);
			pstm.setInt(3, pageP);
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				InfoDto ifd = new InfoDto();
				ifd.setInfo_no(rs.getInt(2));
				ifd.setInfo_writer(rs.getString(3));
				ifd.setInfo_title(rs.getString(4));
				ifd.setInfo_content(rs.getString(5));
				ifd.setUser_img(rs.getString(6));
				ifd.setInfo_push(rs.getInt(7));
				ifd.setInfo_regdate(rs.getDate(8));
										
				res.add(ifd);
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
	public List<InfoDto> selectSearch(String search) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<InfoDto> res = new ArrayList<InfoDto>();
		String sql = "SELECT * FROM L_INFO WHERE (INFO_TITLE LIKE '%" + search + "%') OR (INFO_CONTENT LIKE '%" + search + "%') ORDER BY INFO_NO DESC";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("3. 쿼리문 준비: " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("4. 쿼리문 실행 및 리턴");
			while(rs.next()) {
				InfoDto tmp = new InfoDto();
				tmp.setInfo_no(rs.getInt(1));
				tmp.setInfo_writer(rs.getString(2));
				tmp.setInfo_title(rs.getString(3));
				tmp.setInfo_content(rs.getString(4));
				tmp.setUser_img(rs.getString(5));
				tmp.setInfo_push(rs.getInt(6));
				tmp.setInfo_regdate(rs.getDate(7));
				
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
	public int info_insert(InfoDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = "INSERT INTO L_INFO VALUES((SELECT COUNT(INFO_NO) FROM L_INFO) + 1, ?, ?, ?, NULL, 0, SYSDATE)";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getInfo_writer());
			pstm.setString(2, dto.getInfo_title());
			pstm.setString(3, dto.getInfo_content());
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
	public int info_update(InfoDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " UPDATE L_INFO SET INFO_TITLE=?, INFO_CONTENT=? WHERE INFO_NO=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getInfo_title());
			pstm.setString(2, dto.getInfo_content());
			pstm.setInt(3, dto.getInfo_no());
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
	public int info_delete(int info_no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = "DELETE FROM L_INFO WHERE INFO_NO = ?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, info_no);
			System.out.println("03. query 준비  : " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
			
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

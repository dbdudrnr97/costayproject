package board.faqBoard.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import board.model.vo.Board;



public class FaqDao {
	
	private Properties prop = new Properties();
	
	  //build-path의 board-query.properties의 내용을 읽어와 필드 prop에 저장한다.
	 
	public FaqDao() {
		
		String fileName = "/sql/board/faqBoard-query.properties";  //prop에 담을 내용이 있는 properties파일의 경로를 지정해 문자열로 지정해둠
		String path = FaqDao.class.getResource(fileName).getPath();   //getPath이전은 url객체가 나옴 -> 절대경로로 바꿔줌
		
		try {
			prop.load(new FileReader(path));  //prop.load가 경로를 읽어서 해당 내용을 읽어온다 라고 생각하자
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}


		public List<Board> selectFaqList(Connection conn, int cpage, int numPerPage) {
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<Board>faqList = new ArrayList<>();
			
			String sql = prop.getProperty("selectFaqBoardList");
		
			try {
				pstmt = conn.prepareStatement(sql);
				
				//시작 rownum과 마지막 rownum 구하는 공식
				pstmt.setInt(1, (cpage-1) * numPerPage + 1);
				pstmt.setInt(2, cpage * numPerPage);
				
				//쿼리문실
				//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
				rset = pstmt.executeQuery();
				
				//조회
				while(rset.next()) {
				
					Board board = new Board();
					
					board.setBoardNo(rset.getInt("board_no"));
					board.setBoardTitle(rset.getString("board_title"));
					board.setBoardWriter(rset.getString("board_writer"));
					board.setBoardContent(rset.getString("board_content"));
					board.setBoardEnrollDate(rset.getDate("board_enroll_date"));
					board.setBoardReadCount(rset.getInt("board_read_count"));
					board.setBoardOriginalFileName(rset.getString("board_original_filename"));
					board.setBoardRenamedFileName(rset.getString("board_renamed_filename"));
					board.setBoardCategory(rset.getString("board_category"));
					board.setBoardDelFlag(rset.getString("board_del_flag").charAt(0));
					//getChar라는건 없음 -> String으로 받고 charAt()처리 
					
					/*
					 * 
					 * CREATE TABLE BOARD (
					 * 
					 * BOARD_NO NUMBER, 
					 * BOARD_TITLE VARCHAR2(200) NOT NULL, 
					 * BOARD_WRITER VARCHAR2(20), 
					 * BOARD_CONTENT VARCHAR2(10000) NOT NULL, 
					 * BOARD_ENROLL_DATE DATE DEFAULT sysdate, 
					 * BOARD_READ_COUNT NUMBER DEFAULT 0, 
					 * BOARD_ORIGINAL_FILENAME VARCHAR2(200), 
					 * BOARD_RENAMED_FILENAME VARCHAR2(200), 
					 * BOARD_CATEGORY CHAR(1) NOT NULL, 
					 * BOARD_DEL_FLAG CHAR(1) DEFAULT 'Y',
					 * 
					 * CONSTRAINT PK_BOARD_NO PRIMARY KEY(BOARD_NO), CONSTRAINT CK_BOARD_DEL_FLAG
					 * CHECK (BOARD_DEL_FLAG IN ('Y','N')) , CONSTRAINT FK_BOARD_WRITER FOREIGN
					 * KEY(BOARD_WRITER) REFERENCES MEMBER(MEMBER_ID) );
					 * 
					 */
					faqList.add(board);
				}
				
				
			} catch (SQLException e) {
			
		     //e.printStackTrace();
				//런타임 예외로 변환 후 다시 던지기 -> 체크드와 강제 예외처리가 필요없음
				throw new RuntimeException("게시물 조회 오류", e);
			}finally {
				close(rset);
				close(pstmt);
			}
			
			
			return faqList;
		}


		public int selectTotalFaqBoards(Connection conn) {
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("selectTotalFaqBoards");
			
			int totalContents = 0;
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				if(rset.next()) {
					totalContents = rset.getInt(1); //컬럼순서로 가져옴.
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
		
			return totalContents;
		}


		public int insertFaqBoard(Connection conn, Board board) {
			//글작성
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("insertFaqBoard");

			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, board.getBoardTitle());
				pstmt.setString(2, board.getBoardWriter());
				pstmt.setString(3, board.getBoardContent());
				pstmt.setString(4, board.getBoardOriginalFileName());
				pstmt.setString(5, board.getBoardRenamedFileName());
			
			
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}


		public int selectLastBoardNo(Connection conn) {
			//게시글 성공한 경우, 등록된 게시글 번호 가져오기
			//게시글 등록 직후, 게시글 번호 가져오기
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("selectLastFaqBoardNo");
			int boardNo = 0;
			
			try {
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery();
				if(rset.next()) 
					boardNo = rset.getInt(1);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return boardNo;
		}


		public Board selectFaqBoardNo(Connection conn, int boardNo) {
			Board board = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("selectFaqBoardNo");
			// select * from board where board_no = ?
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardNo);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					board = new Board();
					
					board.setBoardNo(rset.getInt("board_no"));
					board.setBoardTitle(rset.getString("board_title"));
					board.setBoardWriter(rset.getString("board_writer"));
					board.setBoardContent(rset.getString("board_content"));
					board.setBoardEnrollDate(rset.getDate("board_enroll_date"));
					board.setBoardReadCount(rset.getInt("board_read_count"));
					board.setBoardOriginalFileName(rset.getString("board_original_filename"));
					board.setBoardRenamedFileName(rset.getString("board_renamed_filename"));
					board.setBoardCategory(rset.getString("board_category"));
					board.setBoardDelFlag(rset.getString("board_del_flag").charAt(0));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return board;
		}


		public int updateFaqBoard(Connection conn, Board board) {
			int result = 0;
			PreparedStatement pstmt = null;
			String query = prop.getProperty("updateFaqBoard"); 
			
			try {
				//미완성쿼리문을 가지고 객체생성.
				pstmt = conn.prepareStatement(query);
				//쿼리문미완성
				pstmt.setString(1, board.getBoardTitle());
				pstmt.setString(2, board.getBoardContent());
				pstmt.setString(3, board.getBoardOriginalFileName());
				pstmt.setString(4, board.getBoardRenamedFileName());
				pstmt.setInt(5, board.getBoardNo());
				
				//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
				//DML은 executeUpdate()
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}


		public int deleteFaqBoard(Connection conn, int boardNo) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("deleteFaqBoard");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardNo);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}
		
		public int updateFaqReadCount(Connection conn, int boardNo) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("updateFaqReadCount");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardNo);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}
		
		public List<Board> selectBoardsBy(Connection conn, Map<String, Object> param) {
			List<Board> faqList = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectPagedBoardsBy");
			
			switch((String)param.get("searchType")) {
			case "boardTitle" : sql = sql.replace("#", "board_title"); break;
			case "boardContent" : sql = sql.replace("#", "board_content"); break;
			}
			
			try{
				//미완성쿼리문을 가지고 객체생성. 
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + param.get("searchKeyword") + "%");
				
				//1 : 1 ~ 10
				//2 : 11 ~ 20
				int cpage = (int)param.get("cpage");
				int numPerPage = (int)param.get("numPerPage");
				pstmt.setInt(2, (cpage - 1) * numPerPage + 1);
				pstmt.setInt(3, cpage * numPerPage);
				
				
				//쿼리문실행
				//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
				rset = pstmt.executeQuery();
				
				while(rset.next()){
					Board board = new Board();
					//컬럼명은 대소문자 구분이 없다.
					board.setBoardNo(rset.getInt("board_no"));
					board.setBoardTitle(rset.getString("board_title"));
					board.setBoardWriter(rset.getString("board_writer"));
					board.setBoardContent(rset.getString("board_content"));
					board.setBoardEnrollDate(rset.getDate("board_enroll_date"));
					board.setBoardReadCount(rset.getInt("board_read_count"));
					board.setBoardOriginalFileName(rset.getString("board_original_filename"));
					board.setBoardRenamedFileName(rset.getString("board_renamed_filename"));
					board.setBoardCategory(rset.getString("board_category"));
					board.setBoardDelFlag(rset.getString("board_del_flag").charAt(0));
					
					faqList.add(board);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				close(rset);
				close(pstmt);
			}
			
			return faqList;
		}

		
		
		public int selectTotalBoardsBy(Connection conn, Map<String, Object> param) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int totalContents = 0;
			String sql = prop.getProperty("selectTotalBoardsBy");
			
			switch((String)param.get("searchType")) {
			case "boardTitle" : sql = sql.replace("#", "board_title"); break;
			case "boardContent" : sql = sql.replace("#", "board_content");  break;

			}
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + param.get("searchKeyword") + "%");
				rset = pstmt.executeQuery();
				if(rset.next()) {
					totalContents = rset.getInt(1);//컬럼순서로 가져옴.
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return totalContents;
		}

}

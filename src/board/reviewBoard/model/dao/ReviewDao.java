package board.reviewBoard.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import board.model.vo.Board;
import board.model.vo.BoardComment;
import board.model.vo.BoardExt;

import static common.JDBCTemplate.*;



public class ReviewDao {
	
	private Properties prop = new Properties();
	
	  //build-path의 board-query.properties의 내용을 읽어와 필드 prop에 저장한다.
	 
	public ReviewDao() {
		
		String fileName = "/sql/board/reviewBoard-query.properties";  //prop에 담을 내용이 있는 properties파일의 경로를 지정해 문자열로 지정해둠
		String path = ReviewDao.class.getResource(fileName).getPath();   //getPath이전은 url객체가 나옴 -> 절대경로로 바꿔줌
		
		try {
			prop.load(new FileReader(path));  //prop.load가 경로를 읽어서 해당 내용을 읽어온다 라고 생각하자
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}


		public List<BoardExt> selectReviewList(Connection conn, int cpage, int numPerPage) {
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<BoardExt>reviewList = new ArrayList<>();
			
			String sql = prop.getProperty("selectReviewBoardList");
			//selectReviewBoardList = select * from( select row_number() over(order by board_no desc) rnum, 
			//					b.*, (select count(*) from board_comment where board_ref = b.board_no) 
			//                                 board_comment_count from board b) v where rnum between ? and ?

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
				
					//boardCommentCount필드를 사용학 위해 boardExt 사용
					//Board board = new Board();
					BoardExt board = new BoardExt();
					
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
					board.setBoardCommentCount(rset.getInt("board_comment_count"));
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
					reviewList.add(board);
					//System.out.println("reviewList@dao="+reviewList);
				}
				
				
			} catch (SQLException e) {
			
		     //e.printStackTrace();
				//런타임 예외로 변환 후 다시 던지기 -> 체크드와 강제 예외처리가 필요없음
				throw new RuntimeException("게시물 조회 오류", e);
			}finally {
				close(rset);
				close(pstmt);
			}
			
			
			return reviewList;
		}


		public int selectTotalReviewBoards(Connection conn) {
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("selectTotalReviewBoards");
			
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


		public int insertReviewBoard(Connection conn, Board board) {
			//글작성
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("insertReviewBoard");
			//insert into board values(SEQ_REVIEW_BOARD_NO.nextval,?,?,?,sysdate,default,?,?,'A',default)
		
			
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
			String sql = prop.getProperty("selectLastReviewBoardNo");
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


		public Board selectReviewBoardNo(Connection conn, int boardNo) {
			Board board = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("selectReviewBoardNo");
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
		
		public int reviewUpdate(Connection conn, Board board) {
			//게시물 수정
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("updateReview"); 
			//updateReview = update board set board_title = ?, board_content = ?, board_original_filename = ?, 
																	//board_renamed_filename = ? where board_no = ?
			System.out.println(query);
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, board.getBoardTitle());
				pstmt.setString(2, board.getBoardContent());
				pstmt.setString(3, board.getBoardOriginalFileName());
				pstmt.setString(4, board.getBoardRenamedFileName());
				pstmt.setInt(5, board.getBoardNo());
				
				result = pstmt.executeUpdate();
				//System.out.println("result@dao =" +result);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
			
		}


		public int deleteReviewBoard(Connection conn, int boardNo) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("deleteReviewBoard"); 
			// deleteReviewBoard = delete from board where board_no = ?
			try {
				//미완성쿼리문을 가지고 객체생성.
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, boardNo);
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}


		public int insertReviewComment(Connection conn, BoardComment bc) {
			//댓글등록
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("insertReviewComment"); 
			//insertReviewComment = 
			//insert into board_comment values (seq_board_comment.nextval, ?, ?, ?, ?, sysdate, 'R', default, ?)
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setInt(1, bc.getBoardCommentLevel());
				pstmt.setString(2, bc.getBoardCommentContent());
				pstmt.setInt(3, bc.getBoardRef());
				pstmt.setObject(4, bc.getBoardCommentRef() != 0? bc.getBoardCommentRef() : null); //댓글인 경우 0번 댓글을 참조하게 됨
				pstmt.setString(5, bc.getBoardCommentWriter());
				
				
			//new BoardComment(0, boardCommentLevel, boardCommentContent, boardRef, boardCommentRef, null, "R", 'N', boardCommentWriter);
				/**
				BOARD_COMMENT_NO          NOT NULL NUMBER         
				BOARD_COMMENT_LEVEL       NOT NULL NUMBER         
				BOARD_COMMENT_CONTENT     NOT NULL VARCHAR2(2000) 
				BOARD_REF                 NOT NULL NUMBER         
				BOARD_COMMENT_REF                  NUMBER         
				BOARD_COMMENT_ENROLL_DATE          DATE           
				BOARD_COMMENT_CATEGORY    NOT NULL CHAR(1)        
				BOARD_COMMENT_DEL_FLAG    NOT NULL CHAR(1)        
				BOARD_COMMENT_WRITER      NOT NULL VARCHAR2(20)   
				*/		
				result = pstmt.executeUpdate();
				//System.out.println("insertReviewComment-result@dao="+result);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}


		public int updateReviewReadCount(Connection conn, int boardNo) {
			//조회수 올리기
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("updateReviewReadCount");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardNo);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			//System.out.println("result-updateReviewReadCount@dao="+result); 잘나옴
			return result;
		}


		public List<BoardComment> reviewCommentList(Connection conn, int boardNo) {
			//댓글 리스트
			List<BoardComment>commentList = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String query = prop.getProperty("reviewCommentList");

			//System.out.println("query@commentListDao="+query);
			//query@commentListDao= select * from board_comment where board_ref = ? start with board_comment_level = 1 
					//connect by board_comment_ref = prior board_comment_no 
			try {
				pstmt = conn.prepareStatement(query);
				commentList = new ArrayList<BoardComment>();
				pstmt.setInt(1, boardNo);
				
				rset = pstmt.executeQuery();
				//System.out.println("rset@commentListDao="+rset);
				//조회
				while(rset.next()) {
					BoardComment bc = new BoardComment();
					
					bc.setBoardCommentNo(rset.getInt("board_comment_no"));
					bc.setBoardCommentLevel(rset.getInt("board_comment_level"));
					bc.setBoardCommentContent(rset.getString("board_comment_content"));
					bc.setBoardRef(rset.getInt("board_ref"));
					bc.setBoardCommentRef(rset.getInt("board_comment_ref"));
					bc.setBoardCommentEnrollDate(rset.getDate("board_comment_enroll_date"));
					bc.setBoardCommentCategory(rset.getString("board_comment_category"));
					bc.setBoardCommentDelFlag(rset.getString("board_comment_del_flag").charAt(0));
					bc.setBoardCommentWriter(rset.getString("board_comment_writer"));
					
					//게시글별 댓글의 수를 VO객체에 담기 (새로 생성 :commentCnt필드추가)
					
					commentList.add(bc);
					System.out.println("commentList@dao="+commentList);
				
					
					/*
					 * private int boardCommentNo; 
					 * private int boardCommentLevel; 
					 * private String boardCommentContent; 
					 * private int boardRef; 
					 * private int boardCommentRef;
					 * private Date boardCommentEnrollDate; 
					 * private String boardCommentCategory;
					 * private char boardCommentDelFlag; 
					 * private String boardCommentWriter;
					 */

				}
		
			} catch (SQLException e) {
			
		     //e.printStackTrace();
				//런타임 예외로 변환 후 다시 던지기 -> 체크드와 강제 예외처리가 필요없음
				throw new RuntimeException("게시물 조회 오류", e);
			}finally {
				close(rset);
				close(pstmt);
			}
			
			return commentList ;
		}


		public List<BoardExt> selectBoardsBy(Connection conn, Map<String, Object> param) {
			List<BoardExt> reviewList = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectPagedBoardsBy");
			
			switch((String)param.get("searchType")) {
			case "boardTitle" : sql = sql.replace("#", "board_title"); break;
			case "boardContent" : sql = sql.replace("#", "board_content"); break;
			/* case "boardEnrollDate" : sql = sql.replace("#", "board_enroll_date"); break; */
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
					BoardExt board = new BoardExt();
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
					
					reviewList.add(board);
					System.out.println("boardFinder@dao/reviewList = " + reviewList); 
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				close(rset);
				close(pstmt);
			}
			
			return reviewList;
		}


		public int selectTotalBoardsBy(Connection conn, Map<String, Object> param) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int totalContents = 0;
			String sql = prop.getProperty("selectTotalBoardsBy");
			//select count(*) from member where # like ?
			
			switch((String)param.get("searchType")) {
			case "boardTitle" : sql = sql.replace("#", "board_title"); break;
			case "boardContent" : sql = sql.replace("#", "board_content");  break;
			/*
			 * case "boardEnrollDate" : sql = sql.replace("#", "board_enroll_date"); break;
			 */
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


		public int deleteReviewComment(Connection conn, int boardCommentNo) {
			PreparedStatement pstmt = null;
			int result = 0;
			String query = prop.getProperty("deleteReviewComment");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, boardCommentNo);
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}
}

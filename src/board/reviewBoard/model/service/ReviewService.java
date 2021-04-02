package board.reviewBoard.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import board.model.vo.Board;
import board.model.vo.BoardComment;
import board.model.vo.BoardExt;
import board.reviewBoard.model.dao.ReviewDao;

import static common.JDBCTemplate.*;

public class ReviewService {
	
	ReviewDao reviewDao = new ReviewDao();
	
	public List<BoardExt> selectReviewList(int cpage, int numPerPage) {
		Connection conn = getConnection();
		List<BoardExt> reviewList = reviewDao.selectReviewList(conn, cpage, numPerPage);
		close(conn);
		return reviewList;
	}

	public int selectTotalReviewBoards() {
		Connection conn = getConnection();
		int totalContents = reviewDao.selectTotalReviewBoards(conn);
		close(conn);
		return totalContents;
	}

	public int insertReviewBoard(Board board) {
		//글작성
		Connection conn = getConnection();
		int result = reviewDao.insertReviewBoard(conn,board);
		if(result > 0) {
			//게시글 성공한 경우, 등록된 게시글 번호 가져오기
			int boardNo = reviewDao.selectLastBoardNo(conn);
			board.setBoardNo(boardNo);
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public Board selectReviewBoardNo(int boardNo) {
		Connection conn = getConnection();
		Board board = reviewDao.selectReviewBoardNo(conn, boardNo);
		close(conn);
		return board;
	}
	
	public int reviewUpdate(Board board) {
		Connection conn = getConnection();
		int result = reviewDao.reviewUpdate(conn,board);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int deleteReviewBoard(int boardNo) {
		Connection conn = getConnection();
		int result = reviewDao.deleteReviewBoard(conn,boardNo);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	
		public int insertReviewComment(BoardComment bc) {
		//댓글등록
		Connection conn = getConnection();
		int result = reviewDao.insertReviewComment(conn,bc);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int updateReviewReadCount(int boardNo) {
		//조회수 올리기
		Connection conn = getConnection();
		int result = reviewDao.updateReviewReadCount(conn,boardNo);
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}

	public List<BoardComment> reviewCommentList(int boardNo) {
		//댓글리스트
		Connection conn = getConnection();
		List<BoardComment> commentList = reviewDao.reviewCommentList(conn,boardNo);
		close(conn);
		return commentList;
	}

	public List<BoardExt> selectBoardsBy(Map<String, Object> param) {
		Connection conn = getConnection();
		List<BoardExt> reviewList= reviewDao.selectBoardsBy(conn, param);
		close(conn);
		return reviewList;
	}

	public int selectTotalBoardsBy(Map<String, Object> param) {
		Connection conn = getConnection();
		int totalContents = reviewDao.selectTotalBoardsBy(conn, param);
		close(conn);
		return totalContents;
	}

	public int deleteReviewComment(int boardCommentNo) {
		Connection conn = getConnection();
		int result = reviewDao.deleteReviewComment(conn,boardCommentNo);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
}

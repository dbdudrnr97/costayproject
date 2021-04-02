package board.newHostBoard.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import board.model.vo.Board;
import board.model.vo.BoardComment;
import board.model.vo.BoardExt;
import board.newHostBoard.model.dao.NewHostDao;

public class NewHostService {

	NewHostDao newHostDao = new NewHostDao();
	
	public List<BoardExt> selectBoardsBy(Map<String, Object> param) {
		Connection conn = getConnection();
		List<BoardExt> reviewList= newHostDao.selectBoardsBy(conn, param);
		close(conn);
		return reviewList;
		
	}

	public int selectTotalBoardsBy(Map<String, Object> param) {
		Connection conn = getConnection();
		int totalContents = newHostDao.selectTotalBoardsBy(conn, param);
		close(conn);
		return totalContents;
	}

	public int insertNewHostBoard(Board board) {
		//글작성
		Connection conn = getConnection();
		int result = newHostDao.insertNewHostBoard(conn,board);
		if(result > 0) {
			//게시글 성공한 경우, 등록된 게시글 번호 가져오기
			int boardNo = newHostDao.selectLastBoardNo(conn);
			board.setBoardNo(boardNo);
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int updateNewHostReadCount(int boardNo) {
		//조회수 올리기
		Connection conn = getConnection();
		int result = newHostDao.updateNewHostReadCount(conn,boardNo);
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}

	public Board selectNewHostBoardNo(int boardNo) {
		Connection conn = getConnection();
		Board board = newHostDao.selectNewHostBoardNo(conn, boardNo);
		close(conn);
		return board;
	}

	public List<BoardComment> newHostCommentList(int boardNo) {
		//댓글리스트
		Connection conn = getConnection();
		List<BoardComment> commentList = newHostDao.newHostCommentList(conn,boardNo);
		close(conn);
		return commentList;
	}

	public int deleteNewHostComment(int boardCommentNo) {
		Connection conn = getConnection();
		int result = newHostDao.deleteNewHostComment(conn,boardCommentNo);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int insertNewHostComment(BoardComment bc) {
		//댓글등록
		Connection conn = getConnection();
		int result = newHostDao.insertNewHostComment(conn,bc);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<BoardExt> selectNewHostList(int cpage, int numPerPage) {
		//게시물 조회
		Connection conn = getConnection();
		List<BoardExt> hostList = newHostDao.selectNewHostList(conn, cpage, numPerPage);
		close(conn);
		return hostList;
	}

	public int selectTotalNHBoards() {
		Connection conn = getConnection();
		int totalContents = newHostDao.selectTotalNHBoards(conn);
		close(conn);
		return totalContents;
	}

	public int newHostUpdate(Board board) {
		Connection conn = getConnection();
		int result = newHostDao.newHostUpdate(conn,board);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int deleteNewHostBoard(int boardNo) {
		Connection conn = getConnection();
		int result = newHostDao.deleteNewHostBoard(conn,boardNo);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

}

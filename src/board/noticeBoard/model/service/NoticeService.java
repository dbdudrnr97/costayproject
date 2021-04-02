package board.noticeBoard.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import board.model.vo.Board;
import board.noticeBoard.model.dao.NoticeDao;

public class NoticeService {
	
	NoticeDao noticeDao = new NoticeDao();
	
	public List<Board> selectNoticeList(int cpage, int numPerPage) {
		Connection conn = getConnection();
		List<Board> noticeList = noticeDao.selectNoticeList(conn, cpage, numPerPage);
		close(conn);
		return noticeList;
	}

	public int selectTotalNoticeBoards() {
		Connection conn = getConnection();
		int totalContents = noticeDao.selectTotalNoticeBoards(conn);
		close(conn);
		return totalContents;
	}

	public int insertNoticeBoard(Board board) {
		//글작성
		Connection conn = getConnection();
		int result = noticeDao.insertNoticeBoard(conn,board);
		if(result > 0) {
			//게시글 성공한 경우, 등록된 게시글 번호 가져오기
			int boardNo = noticeDao.selectLastBoardNo(conn);
			board.setBoardNo(boardNo);
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public Board selectNoticeBoardNo(int boardNo) {
		Connection conn = getConnection();
		Board board = noticeDao.selectNoticeBoardNo(conn, boardNo);
		close(conn);
		return board;
	}


	public int updateNoticeBoard(Board board) {
		Connection conn = getConnection();
		int result = noticeDao.updateNoticeBoard(conn, board);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteNoticeBoard(int boardNo) {
		Connection conn = getConnection();
		int result = noticeDao.deleteNoticeBoard(conn, boardNo);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	
	public int updateNoticeReadCount(int boardNo) {
		Connection conn = getConnection();
		int result = noticeDao.updateNoticeReadCount(conn, boardNo);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<Board> selectBoardsBy(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Board> noticeList= noticeDao.selectBoardsBy(conn, param);
		close(conn);
		return noticeList;
	}

	public int selectTotalBoardsBy(Map<String, Object> param) {
		Connection conn = getConnection();
		int totalContents = noticeDao.selectTotalBoardsBy(conn, param);
		close(conn);
		return totalContents;
	}
	

}

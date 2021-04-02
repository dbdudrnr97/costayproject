package board.faqBoard.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import board.model.vo.Board;
import board.faqBoard.model.dao.FaqDao;

import static common.JDBCTemplate.*;

public class FaqService {
	
	FaqDao faqDao = new FaqDao();
	
	public List<Board> selectFaqList(int cpage, int numPerPage) {
		Connection conn = getConnection();
		List<Board> faqList = faqDao.selectFaqList(conn, cpage, numPerPage);
		close(conn);
		return faqList;
	}

	public int selectTotalFaqBoards() {
		Connection conn = getConnection();
		int totalContents = faqDao.selectTotalFaqBoards(conn);
		close(conn);
		return totalContents;
	}

	public int insertFaqBoard(Board board) {
		//글작성
		Connection conn = getConnection();
		int result = faqDao.insertFaqBoard(conn,board);
		if(result > 0) {
			//게시글 성공한 경우, 등록된 게시글 번호 가져오기
			int boardNo = faqDao.selectLastBoardNo(conn);
			board.setBoardNo(boardNo);
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public Board selectFaqBoardNo(int boardNo) {
		Connection conn = getConnection();
		Board board = faqDao.selectFaqBoardNo(conn, boardNo);
		close(conn);
		return board;
	}


	public int updateFaqBoard(Board board) {
		Connection conn = getConnection();
		int result = faqDao.updateFaqBoard(conn, board);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteFaqBoard(int boardNo) {
		Connection conn = getConnection();
		int result = faqDao.deleteFaqBoard(conn, boardNo);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateFaqReadCount(int boardNo) {
		Connection conn = getConnection();
		int result = faqDao.updateFaqReadCount(conn, boardNo);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<Board> selectBoardsBy(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Board> faqList= faqDao.selectBoardsBy(conn, param);
		close(conn);
		return faqList;
	}

	public int selectTotalBoardsBy(Map<String, Object> param) {
		Connection conn = getConnection();
		int totalContents = faqDao.selectTotalBoardsBy(conn, param);
		close(conn);
		return totalContents;
	}

	

}

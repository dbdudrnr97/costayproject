package board.model.vo;

import java.sql.Date;

public class BoardExt extends Board {

	private int boardCommentCount;

	public BoardExt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardExt(int boardNo, String boardTitle, String boardWriter, String boardContent, Date boardEnrollDate,
			int boardReadCount, String boardOriginalFileName, String boardRenamedFileName, String boardCategory,
			char boardDelFlag, int boardCommentCount) {
		super(boardNo, boardTitle, boardWriter, boardContent, boardEnrollDate, boardReadCount, boardOriginalFileName,
				boardRenamedFileName, boardCategory, boardDelFlag);
		this.boardCommentCount = boardCommentCount;
	}

	public int getBoardCommentCount() {
		return boardCommentCount;
	}

	public void setBoardCommentCount(int boardCommentCount) {
		this.boardCommentCount = boardCommentCount;
	}

	@Override
	public String toString() {
		return "BoardExt [boardCommentCount=" + boardCommentCount + ", toString()=" + super.toString() + "]";
	}
	
	
	
}

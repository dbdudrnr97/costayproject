package board.model.vo;

import java.sql.Date;

public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private Date boardEnrollDate;
	private int boardReadCount;
	private String boardOriginalFileName;
	private String boardRenamedFileName;
	private String boardCategory;
	private char boardDelFlag;
	
	
	public Board() {
	}


	public Board(int boardNo, String boardTitle, String boardWriter, String boardContent, Date boardEnrollDate,
			int boardReadCount, String boardOriginalFileName, String boardRenamedFileName, String boardCategory,
			char boardDelFlag) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
		this.boardEnrollDate = boardEnrollDate;
		this.boardReadCount = boardReadCount;
		this.boardOriginalFileName = boardOriginalFileName;
		this.boardRenamedFileName = boardRenamedFileName;
		this.boardCategory = boardCategory;
		this.boardDelFlag = boardDelFlag;
	}


	public int getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}


	public String getBoardWriter() {
		return boardWriter;
	}


	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}


	public String getBoardContent() {
		return boardContent;
	}


	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}


	public Date getBoardEnrollDate() {
		return boardEnrollDate;
	}


	public void setBoardEnrollDate(Date boardEnrollDate) {
		this.boardEnrollDate = boardEnrollDate;
	}


	public int getBoardReadCount() {
		return boardReadCount;
	}

	public void setBoardReadCount(int boardReadCount) {
		this.boardReadCount = boardReadCount;
	}

	public String getBoardOriginalFileName() {
		return boardOriginalFileName;
	}

	public void setBoardOriginalFileName(String boardOriginalFileName) {
		this.boardOriginalFileName = boardOriginalFileName;
	}

	public String getBoardRenamedFileName() {
		return boardRenamedFileName;
	}

	public void setBoardRenamedFileName(String boardRenamedFileName) {
		this.boardRenamedFileName = boardRenamedFileName;
	}

	public String getBoardCategory() {
		return boardCategory;
	}

	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}

	public char getBoardDelFlag() {
		return boardDelFlag;
	}

	public void setBoardDelFlag(char boardDelFlag) {
		this.boardDelFlag = boardDelFlag;
	}


	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
				+ ", boardContent=" + boardContent + ", boardEnrollDate=" + boardEnrollDate + ", boardReadCount="
				+ boardReadCount + ", boardOriginalFileName=" + boardOriginalFileName + ", boardRenamedFileName="
				+ boardRenamedFileName + ", boardCategory=" + boardCategory + ", boardDelFlag=" + boardDelFlag + "]";
	}

}

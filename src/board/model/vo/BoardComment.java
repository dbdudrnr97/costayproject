package board.model.vo;

import java.sql.Date;

public class BoardComment {

	private int boardCommentNo;
	private int boardCommentLevel;
	private String boardCommentContent;
	private int boardRef;
	private int boardCommentRef;
	private Date boardCommentEnrollDate;
	private String boardCommentCategory;
	private char boardCommentDelFlag;
	private String boardCommentWriter;

	
	public BoardComment() {
	}
	
	public BoardComment(int boardCommentNo, int boardCommentLevel, String boardCommentContent, int boardRef,
			int boardCommentRef, Date boardCommentEnrollDate, String boardCommentCategory,
			char boardCommentDelFlag, String boardCommentWriter) {
		super();
		this.boardCommentNo = boardCommentNo;
		this.boardCommentLevel = boardCommentLevel;
		this.boardCommentContent = boardCommentContent;
		this.boardRef = boardRef;
		this.boardCommentRef = boardCommentRef;
		this.boardCommentEnrollDate = boardCommentEnrollDate;
		this.boardCommentCategory = boardCommentCategory;
		this.boardCommentDelFlag = boardCommentDelFlag;
		this.boardCommentWriter = boardCommentWriter;
	}
	
	public int getBoardCommentNo() {
		return boardCommentNo;
	}
	public void setBoardCommentNo(int boardCommentNo) {
		this.boardCommentNo = boardCommentNo;
	}
	public int getBoardCommentLevel() {
		return boardCommentLevel;
	}
	public void setBoardCommentLevel(int boardCommentLevel) {
		this.boardCommentLevel = boardCommentLevel;
	}
	public String getBoardCommentContent() {
		return boardCommentContent;
	}
	public void setBoardCommentContent(String boardCommentContent) {
		this.boardCommentContent = boardCommentContent;
	}
	public int getBoardRef() {
		return boardRef;
	}
	public void setBoardRef(int boardRef) {
		this.boardRef = boardRef;
	}
	public int getBoardCommentRef() {
		return boardCommentRef;
	}
	public void setBoardCommentRef(int boardCommentRef) {
		this.boardCommentRef = boardCommentRef;
	}
	public Date getBoardCommentEnrollDate() {
		return boardCommentEnrollDate;
	}
	public void setBoardCommentEnrollDate(Date boardCommentEnrollDate) {
		this.boardCommentEnrollDate = boardCommentEnrollDate;
	}
	public String getBoardCommentCategory() {
		return boardCommentCategory;
	}
	public void setBoardCommentCategory(String boardCommentCategory) {
		this.boardCommentCategory = boardCommentCategory;
	}
	public char getBoardCommentDelFlag() {
		return boardCommentDelFlag;
	}
	public void setBoardCommentDelFlag(char boardCommentDelFlag) {
		this.boardCommentDelFlag = boardCommentDelFlag;
	}
	public String getBoardCommentWriter() {
		return boardCommentWriter;
	}
	public void setBoardCommentWriter(String boardCommentWriter) {
		this.boardCommentWriter = boardCommentWriter;
	}
	
	@Override
	public String toString() {
		return "BoardComment [boardCommentNo=" + boardCommentNo + ", boardCommentLevel=" + boardCommentLevel
				+ ", boardCommentContent=" + boardCommentContent + ", boardRef=" + boardRef + ", boardCommentRef="
				+ boardCommentRef + ", boardCommentEnrollDate=" + boardCommentEnrollDate + ", boardCommentCategory="
				+ boardCommentCategory + ", boardCommentDelFlag=" + boardCommentDelFlag + ", boardCommentWriter="
				+ boardCommentWriter + "]";
	}
	
	
	
}

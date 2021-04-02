package product.model.vo;

import java.sql.Date;

public class Comment {
	
	private String commentId;			//NOT NULL
	private String commentLevel;		//DEFAULT 1
	private String commentContent;		//NOT NULL
	private int productScore;			//NOT NULL
	private int productRef;				//NULL
	private int commentRef;				//NULL
	private Date commentDate;			//NOT NULL, DEFAULT SYSDATE
	private char commentCategory;		//NOT NULL
	private char commentDelFlag;		//NULL
	private String playId;				//NOT NULL
	private String placeId;				//NOT NULL
	
	
	public Comment(String commentId, String commentLevel, String commentContent, int productScore, int productRef,
			int commentRef, Date commentDate, char commentCategory, char commentDelFlag, String playId,
			String placeId) {
		super();
		this.commentId = commentId;
		this.commentLevel = commentLevel;
		this.commentContent = commentContent;
		this.productScore = productScore;
		this.productRef = productRef;
		this.commentRef = commentRef;
		this.commentDate = commentDate;
		this.commentCategory = commentCategory;
		this.commentDelFlag = commentDelFlag;
		this.playId = playId;
		this.placeId = placeId;
	}


	public String getCommentId() {
		return commentId;
	}


	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}


	public String getCommentLevel() {
		return commentLevel;
	}


	public void setCommentLevel(String commentLevel) {
		this.commentLevel = commentLevel;
	}


	public String getCommentContent() {
		return commentContent;
	}


	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}


	public int getProductScore() {
		return productScore;
	}


	public void setProductScore(int productScore) {
		this.productScore = productScore;
	}


	public int getProductRef() {
		return productRef;
	}


	public void setProductRef(int productRef) {
		this.productRef = productRef;
	}


	public int getCommentRef() {
		return commentRef;
	}


	public void setCommentRef(int commentRef) {
		this.commentRef = commentRef;
	}


	public Date getCommentDate() {
		return commentDate;
	}


	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}


	public char getCommentCategory() {
		return commentCategory;
	}


	public void setCommentCategory(char commentCategory) {
		this.commentCategory = commentCategory;
	}


	public char getCommentDelFlag() {
		return commentDelFlag;
	}


	public void setCommentDelFlag(char commentDelFlag) {
		this.commentDelFlag = commentDelFlag;
	}


	public String getPlayId() {
		return playId;
	}


	public void setPlayId(String playId) {
		this.playId = playId;
	}


	public String getPlaceId() {
		return placeId;
	}


	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}


	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentLevel=" + commentLevel + ", commentContent="
				+ commentContent + ", productScore=" + productScore + ", productRef=" + productRef + ", commentRef="
				+ commentRef + ", commentDate=" + commentDate + ", commentCategory=" + commentCategory
				+ ", commentDelFlag=" + commentDelFlag + ", playId=" + playId + ", placeId=" + placeId + "]";
	}
	
	
	
	
}

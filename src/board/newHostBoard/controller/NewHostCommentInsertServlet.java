package board.newHostBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.vo.BoardComment;
import board.newHostBoard.model.service.NewHostService;

/**
 * Servlet implementation class NewHostCommentInsertServlet
 */
@WebServlet("/board/newHost/CommentInsert")
public class NewHostCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NewHostService newHostService = new NewHostService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 사용자 입력값
		int boardCommentRef = Integer.parseInt(request.getParameter("boardCommentRef"));
		int boardRef = Integer.parseInt(request.getParameter("boardRef"));
		int boardCommentLevel = Integer.parseInt(request.getParameter("boardCommentLevel"));
		String boardCommentWriter = request.getParameter("boardCommentWriter");
		String boardCommentContent = request.getParameter("boardCommentContent");
	
		
		//insertReviewComment = 
		//insert into board_comment values (seq_board_comment.nextval, ?, ?, ?, ?, sysdate, 'R', default, ?)
		BoardComment bc = 
				new BoardComment(0, boardCommentLevel, boardCommentContent, boardRef, boardCommentRef, null, "H", 'N', boardCommentWriter);
		System.out.println("bc@CommentInsertServlet =" + bc);
		
		/*
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
		
	
		//2 업무로직 : 댓글 등록
		int result= newHostService.insertNewHostComment(bc);
		String msg = result > 0 ? "댓글등록성공" : "댓글등록실패";
		
		//3 세션에 속성저장 및 메세지
		request.getSession().setAttribute("msg", msg);
		//4 리다이렉트 처리
		response.sendRedirect(request.getContextPath() + "/board/newHost/boardView?boardNo=" + boardRef);

	}

}

package board.newHostBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.vo.Board;
import board.model.vo.BoardComment;
import board.newHostBoard.model.service.NewHostService;


@WebServlet("/board/newHost/boardView")
public class NewHostBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	NewHostService newHostService = new NewHostService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값 처리 
		 
		int	boardNo = Integer.parseInt(request.getParameter("boardNo"));
//		System.out.println("boardNo@viewServlet="+boardNo);
		
		int result = newHostService.updateNewHostReadCount(boardNo);
//		System.out.println("result@viewServlet="+result);
		Board board = newHostService.selectNewHostBoardNo(boardNo);
//		System.out.println("board@viewServlet="+board);
		
		//content 추가처리 
		//XSS(Cross site scripting) 공격대비 =>사용자가 입력한 모든것에 다 처리필요 => util에 뺴놓고 공통처리하자
		//스크립트의 위치는 자유로워서 게시글내에게 스크립트가 있다해도 실제로 실행되는 코드가 되어버리므로 그걸 무효화하는것
		String boardContent = board.getBoardContent()
								   .replaceAll("<", "&lt;")  //문자그대로 사용하기 (html의 escaping)
								   .replaceAll(">", "&gt;");
		//개행문자
		boardContent = boardContent.replaceAll("\\n", "<br>");
		board.setBoardContent(boardContent);
		System.out.println("boardContent@boardViewServlet =" + boardContent);   //잘나옴
		System.out.println("board@boardViewServlet =" + board);  //잘나옴
		
		
		//댓글목록 가져오기
		//댓글과 대댓글을 그룹으로 묶어서 가져와야함(쿼리에서 해결하기)
		List<BoardComment> commentList = newHostService.newHostCommentList(boardNo);
		System.out.println("commentList@viewServlet="+commentList);   
		
		//3. view단 처리 : jsp로 forwarding처리
		HttpSession session = request.getSession();
		session.setAttribute("title", "커뮤니티");
		request.setAttribute("board", board);
		request.setAttribute("commentList", commentList);
		request.getRequestDispatcher("/WEB-INF/views/board/newHostBoard/newHostBoardView.jsp")
			   .forward(request, response);	
	}

	
	
}

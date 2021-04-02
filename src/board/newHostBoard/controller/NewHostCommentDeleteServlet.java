package board.newHostBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.newHostBoard.model.service.NewHostService;

/**
 * Servlet implementation class NewHostCommentDeleteServlet
 */
@WebServlet("/board/newHost/CommentDelete")
public class NewHostCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	NewHostService newHostService = new NewHostService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자 입력값
		int boardCommentNo = Integer.parseInt(request.getParameter("boardCommentNo"));
		int boardNo =Integer.parseInt(request.getParameter("boardNo"));
		//업무로직
		int result = newHostService.deleteNewHostComment(boardCommentNo);
		String msg = result > 0? "댓글삭제 성공" :"댓글삭제실패";
		//사용자 피드백 및 redirect
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/board/newHost/boardView?boardNo=" +boardNo);
	}

}

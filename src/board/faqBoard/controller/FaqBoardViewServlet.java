package board.faqBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.vo.Board;
import board.faqBoard.model.service.FaqService;

@WebServlet("/board/faq/boardView")
public class FaqBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FaqService faqService = new FaqService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		//조회수
		int result = faqService.updateFaqReadCount(boardNo);
		Board board = faqService.selectFaqBoardNo(boardNo);
		
		//개행문자
		String boardContent = board.getBoardContent().replaceAll("<", "&lt;")
													.replaceAll(">", "&gt;");
		boardContent = boardContent.replaceAll("\\n", "<br>");
		board.setBoardContent(boardContent);
		
		String imgPath = getServletContext().getRealPath("/upload/board")+"/" + board.getBoardRenamedFileName();
		
		
		
		HttpSession session = request.getSession();
		session.setAttribute("title", "고객센터");
		request.setAttribute("Board", board);
		request.getRequestDispatcher("/WEB-INF/views/board/faqBoard/faqBoardView.jsp").forward(request, response);
	}

}

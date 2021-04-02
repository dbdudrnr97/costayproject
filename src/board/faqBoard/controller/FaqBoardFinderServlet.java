package board.faqBoard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.vo.Board;
import board.faqBoard.model.service.FaqService;
import common.util.MvcUtils;

/**
 * Servlet implementation class AdminMemberFinderServlet
 */
@WebServlet("/board/faq/boardFinder")
public class FaqBoardFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FaqService faqService = new FaqService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값 처리
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		int numPerPage = 10;
		int cpage = 1;
		try {
			cpage = Integer.parseInt(request.getParameter("cpage"));
		} catch (NumberFormatException e) {
			//기본값 1
		}
		
		//사용자 입력값을 Map으로 처리
		Map<String, Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		param.put("cpage", cpage);
		param.put("numPerPage", numPerPage);
		
		
		//2. 업무로직 : 검색
		List<Board> faqList = faqService.selectBoardsBy(param);
		
		int totalContents = faqService.selectTotalBoardsBy(param);
		
		String url = request.getRequestURI() 
				   + "?searchType=" + searchType 
				   + "&searchKeyword=" + searchKeyword; 
		String pageBar = MvcUtils.getPageBar(totalContents, cpage, numPerPage, url);
		System.out.println("boardFinder@servlet/noticeList = " + faqList);

		
		
		//3. view단 처리 : fowarding 
		request.setAttribute("faqList", faqList);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/WEB-INF/views/board/faqBoard/faqBoardList.jsp")
			   .forward(request, response);
		
	}

}

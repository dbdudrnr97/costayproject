package board.newHostBoard.controller;

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
import board.model.vo.BoardExt;
import board.newHostBoard.model.service.NewHostService;
import common.util.MvcUtils;

/**
 * Servlet implementation class NewHostBoardFinderServlet
 */
@WebServlet("/board/newHost/boardFinder")
public class NewHostBoardFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NewHostService newHostService = new NewHostService();
	
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
				List<BoardExt> hostList = newHostService.selectBoardsBy(param);
				
				int totalContents = newHostService.selectTotalBoardsBy(param);
				
				String url = request.getRequestURI() 
						   + "?searchType=" + searchType 
						   + "&searchKeyword=" + searchKeyword; 
				String pageBar = MvcUtils.getPageBar(totalContents, cpage, numPerPage, url);
				//System.out.println("boardFinder@servlet/hostList = " + hostList); //잘나옴
				//System.out.println("hostlist@finder =" +hostList);
				//System.out.println("pageBar@finder =" +pageBar);
				
				
				//3. view단 처리 : fowarding 
				request.setAttribute("hostList", hostList);
				request.setAttribute("pageBar", pageBar);
				request.getRequestDispatcher("/WEB-INF/views/board/newHostBoard/newHostBoardList.jsp")
					   .forward(request, response);
				
			}
 
}

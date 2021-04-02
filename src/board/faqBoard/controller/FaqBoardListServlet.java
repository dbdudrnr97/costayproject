package board.faqBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.vo.Board;
import board.faqBoard.model.service.FaqService;
import common.util.MvcUtils;

@WebServlet("/board/faq/boardList")
public class FaqBoardListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	FaqService faqService = new FaqService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		try {
		
			//1 사용자 입력값 처리  cpage | numPerPage = 5
			final int numPerPage = 10;
			int cpage = 1;
			try {
				cpage = Integer.parseInt(request.getParameter("cpage"));
			}catch (NumberFormatException e) {
				//예외가 발생한 경우 , cpage는 1로 유지한다
			}
			

			//2.업무로직 : 각 페이지에 해당하는 게시글 가져오기
			//페이지 바 처리 - 페이지바 사이즈 : 페이지 다섯개씩 처리
			//          - 전체 페이지 수 : (전체 게시물 수 / numPerPage) 의 올림 처리
			List<Board>faqList= faqService.selectFaqList(cpage, numPerPage);  //게시물 조회
			
			// totalContents 총 게시물 수
			int totalContents = faqService.selectTotalFaqBoards();
			//url 페이지 링크를 클릭했을 때 이동할 주소
			String url = request.getRequestURI();
			// 페이지 바 작성
			String pageBar = MvcUtils.getPageBar(totalContents, cpage, numPerPage, url);
			
			
			//3. view 단 처리 (forwarding): jsp에 전달한 값은 request속성을 이용한다
			request.setAttribute("faqList", faqList);
			request.setAttribute("pageBar", pageBar);
			
			HttpSession session = request.getSession();
			session.setAttribute("title", "고객센터");
			request.getRequestDispatcher("/WEB-INF/views/board/faqBoard/faqBoardList.jsp").forward(request, response);
			
			}catch (Exception e) {
				//예외처리
				e.printStackTrace(); //필요한 행위가 있다면 여기서 진행 (복구 혹은 관리자에게 예외내역 발송처리 등) 
				
				//WAS에게  예외 던지기
				throw e;
			
			}
			
		}

}

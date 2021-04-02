package board.newHostBoard.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.newHostBoard.model.service.NewHostService;

/**
 * Servlet implementation class NewHostBoardDeleteServlet
 */
@WebServlet("/board/newHost/boardDelete")
public class NewHostBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NewHostService newHostService = new NewHostService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String rName = request.getParameter("rName");
		
//		System.out.println("rName@serlvet= [" + rName + "]"); //공백인지 확인용
		
		//업무로직 
		int result = newHostService.deleteNewHostBoard(boardNo);
//		System.out.println("result@servlet =" +result);
		
		//view 단
		String msg = result > 0 ? "게시물 삭제 성공!" : "게시물 삭제 실패!";
		
		//첨부파일이 있는경우, 삭제처리
		if(result > 0 && !"".equals(rName)) {
			String saveDirectory = getServletContext().getRealPath("/upload/board"); // webContent하위의 파일
			File delFile = new File(saveDirectory, rName);
			boolean bool = delFile.delete(); // 삭제처리
			msg += bool ? "(첨부파일삭제성공)" : "(첨부파일삭제실패)";
		}
		
		//redirect 처리
		request.getSession().setAttribute("msg",msg);
		response.sendRedirect(request.getContextPath() + "/board/newHost/boardList");
		
	}

}

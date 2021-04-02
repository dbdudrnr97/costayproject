package board.newHostBoard.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import board.model.vo.Board;
import board.newHostBoard.model.service.NewHostService;
import common.MvcFileRenamePolicy;

@WebServlet("/board/newHost/boardUpdate")
public class NewHostBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NewHostService newHostService = new NewHostService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//1.parameterHandling
		int boardNo;
		
		boardNo = Integer.parseInt(request.getParameter("boardNo"));
//		System.out.println("boardNo@updateServlet="+boardNo);
		
		//2.businessLogic
		Board board = newHostService.selectNewHostBoardNo(boardNo);
//		System.out.println("board@updateServlet="+board);
		//System.out.println("@@@@@@@@@@@");
		
		//3.view
		HttpSession session = request.getSession();
		session.setAttribute("title", "커뮤니티");
		request.setAttribute("board", board);
		request.getRequestDispatcher("/WEB-INF/views/board/newHostBoard/newHostBoardUpdate.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 new MultipartRequest(
		 			HttpServletRequest request, 
		 			String saveDirectory, 		//업로드파일의 저장경로(절대경로)
		 			int maxPostSize, 			//최대크기제한 10mb
		 			String encoding, 			//인코딩
		 			FileRenamePolicy policy 	//파일이름 재지정 정책 객체
		 		)
		 */
		
		//application : WAS실행시부터 종료시까지 운영되는 객체
		String saveDirectory = getServletContext().getRealPath("/upload/board");// / -> Web Root Directory
		//byte단위 : 1mb = 1kb * 1kb
		int maxPostSize = 10 * 1024 * 1024;
		String encoding = "utf-8";
		//중복파일에 대해서 number부여
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		//MultipartRequest객체 생성
		MultipartRequest multipartReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		//MultipartRequest를 사용하면, 기존 request로 부터 사용자 입력값을 가져올 수 없다.
		
		//1.사용자 입력값으로 Board객체 생성 
		int boardNo = Integer.parseInt(multipartReq.getParameter("boardNo"));
		String boardTitle = multipartReq.getParameter("boardTitle");
		String boardWriter= multipartReq.getParameter("boardWriter");
		String boardContent = multipartReq.getParameter("boardContent");
		String boardOriginalFileName = multipartReq.getOriginalFileName("upFile");
		String boardRenamedFileName = multipartReq.getFilesystemName("upFile");
		
		String delFile = multipartReq.getParameter("delFile");
		
		//기존 첨부파일정보
		String oldBoardOriginalFileName = multipartReq.getParameter("oldBoardOriginalFileName"); 
		String oldBoardRenamedFileName = multipartReq.getParameter("oldBoardRenamedFileName"); 
	
		//기존 파일이 있는 경우만 : 기존 파일 삭제 처리!
		if(oldBoardOriginalFileName != null) {
			//1.업로드한 파일 가져오기 | 2. 기존 파일제거
		File upFile = multipartReq.getFile("upFile"); //업로드한 파일 가져오는 객체
		if(upFile != null || delFile != null) {
			File realDelFile = new File(saveDirectory, delFile); //새 파일 객체?
			boolean bool = realDelFile.delete();
			System.out.println(delFile + ":" + (bool ? "기존파일삭제 성공!" : "기존파일 삭제 실패"));
		//delFile :삭제한 파일명
		//realDelFile : 파일 객체
		}
		else {
			//3.기존파일정보를 유지
			boardOriginalFileName = oldBoardOriginalFileName;
			boardRenamedFileName = oldBoardRenamedFileName;
			
		}
	}	
			
		Board board = 
				new Board(boardNo, boardTitle, boardWriter, boardContent, null, 0, boardOriginalFileName, boardRenamedFileName,"R", 'N');
		
		System.out.println("board@updateServlet="+board);
		
		//업무로직 : Board객체 db수정 요청
		
		int result = newHostService.newHostUpdate(board);
		System.out.println("result@servlet="+result);
		
		String msg = result > 0 ? "게시물 수정 성공!" : "게시물 수정 실패";
		
		String loc = result > 0 ?
				request.getContextPath() + "/board/newHost/boardView?boardNo=" + boardNo :
					
					request.getContextPath() + "/board/newHost/BoardList";  
		// /costay/board/reviewBoard/boardView
				
		//사용자 피드백(msg) 및 redirect처리
		//DML이후 반드시 요청url을 변경할 것
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(loc);
		
		
		
	}

}

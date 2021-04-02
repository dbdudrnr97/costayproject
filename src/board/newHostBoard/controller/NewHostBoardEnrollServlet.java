package board.newHostBoard.controller;

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
import board.noticeBoard.model.service.NoticeService;
import common.MvcFileRenamePolicy;

@WebServlet("/board/newHost/boardEnroll")
public class NewHostBoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NewHostService newHostService = new NewHostService ();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("title", "커뮤니티");
		request.getRequestDispatcher("/WEB-INF/views/board/newHostBoard/newHostBoardEnroll.jsp")	
		       .forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//리스트의 토탈 컨텐츠는 전체 파인더는 해당 검색에 대한 회원수 또는 게시글 수 셀렉스멤버스바이와 셀렉트토탈멤버스바이는 동일한 where절을 갖는다
			
					
			/*
			 * MultipartRequest 이용해서 객체 생성(파일 읽어서 서버에 저장이 완료)
			new MultipartRequest(HttpServletRequest (
								String saveDirectory, : 저장된 서버컴퓨터의 저장경로 (정대경로) 
								int maxPostSize, : 최대크기 제한  10mb
								String encoding, : encoding
								FileRenamePolicy policy : 파일 이름 재지정 정책 객체);
						)
			*/
					
		
			String saveDirectory = getServletContext().getRealPath("/upload/board");  //여기서 /는 webRoot를 의미 = webContent 디렉토리
		
			//최대 업로드 크기 : 바이트 단위로 전달 (1mg = 1kb *1kb)
			int maxPostSize = 10 * 1024 * 1024;
					
			String encoding = "utf-8";
			
			//중복 파일에 덮어써지지 않도록 넘버링을 부여함
			//FileRenamePolicy policy = new DefaultFileRenamePolicy();
			FileRenamePolicy policy = new MvcFileRenamePolicy();
			
			//MultipartRequest객체 생성
			MultipartRequest multipartReq = 
					new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			//MultipartRequest객체 사용시 기존 request객체로 부터 사용자 입력값을 가져올 수 없다.
			//1.사용자 입력값으로 Board객체 생성 
			String boardTitle = multipartReq.getParameter("boardTitle");
			String boardWriter= multipartReq.getParameter("boardWriter");
			String boardContent = multipartReq.getParameter("boardContent");
			String boardOriginalFileName = multipartReq.getOriginalFileName("upFile"); //서버에 저장된 파일명 -> (JSP 첨부파일의 name속성값)인자로 전달
			String boardRenamedFileName = multipartReq.getFilesystemName("upFile"); //실제 저장된 파일명
			

			
			Board board = new Board(0, boardTitle, boardWriter, boardContent, null, 0, boardOriginalFileName, boardRenamedFileName, "N", 'N');

			/*
			 * private int boardNo; 
			 * private String boardTitle; 
			 * private String boardWriter;
			 * private String boardContent; 
			 * private Date boardEnrollDate; 
			 * private int boardReadCount; 
			 * private String boardOriginalFileName; 
			 * private String boardRenamedFileName; 
			 * private String boardCategory; 
			 * private char boardDelFlag;
			 * 
			 */
			
			
			
			//업무로직
			//조회수 증가
			//int result = boardService.updateBoardReadCount(boardNo); // + 1
			
			int result = newHostService.insertNewHostBoard(board);
//			System.out.println("result@servlet=" + result);
//			System.out.println("saveDirectory@servlet=" + saveDirectory);
			
			String msg = result > 0 ? "게시글 등록 성공!" : "게시글 등록 실패";
			
			// 성공시 상세보기 실패시 목록페이지로 이동
			//board.getBoardNo() 를 통해 새로 등록된 게시글 번호 가져오기
			String location = request.getContextPath() + "/board/newHost/boardList";
					
			request.getSession().setAttribute("msg", msg);
			
			//dml은 무조건 url변경 필요!
			response.sendRedirect(location);

	}

}

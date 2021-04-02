package board.newHostBoard.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/board/newHost/fileDownload")
public class NewHostFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//다운로드 서블렛 = 서버입장에서는  파일을 읽어서(fileInput)해서 응답(response)에 출력스트림에 써주기 작업 후 보내는것!
		
		//1. 사용자 입력값 (파라미터가져옴)
		String oName = request.getParameter("oName");
		String rName = request.getParameter("rName");
		
		//2. 파일입력스트림, 응답출력스트림 준비
		//파일은 webContent/upload/board.. 하위에 있는것을 절대 경로로 가져오기
		
		//파일입력스트림
		String saveDirectory = getServletContext().getRealPath("/upload/board"); // 게시물 등록과 같은방법
		File downFile = new File(saveDirectory, rName);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downFile));
		
		//응답출력스트림 : 이미 있으므로 가져와서 사용
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		
		//3.응답 헤더 작성 : 파일 타입, 파일명
		response.setContentType("application/octet-stream"); //이진데이터라고 알려줌
		//tomcat의 인코딩(ios-8859-1) 변환 후 전달
		oName = new String(oName.getBytes("utf-8"), "iso-8859-1"); //바이트로 바꿔주며 원래는 utf-8인 인코딩방법을 톰캣으로 변환
		//System.out.println("oName=" +oName);
		response.setHeader("Content-Disposition", "attachment;filename=" + oName); // 다운로드 지시: 다운로드 받으면 이름을  oName으로 해라
		
		//4. 파일 읽어서 응답 쓰기 작업
		// 바이트 단위로 읽어서 뿌려주므로 int 타입 ????????????????
		int data = -1;
		while((data = bis.read()) != -1) {
			bos.write(data);
		}
		
		//5. 자원반납
		bis.close();
		bos.close();
		
		//do get 자체가 익셉션을 던지고 있어서 별도의 예외처리는 필요없다
	}

}

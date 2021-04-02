package product.controller.play;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.MvcFileRenamePolicy;
import product.model.service.PlayService;
import product.model.vo.Place;
import product.model.vo.Play;

@WebServlet("/admin/play/productEnroll")
public class PlayEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PlayService playService = new PlayService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/product/play/playEnroll.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//MultipartRequest객체 생성
		String saveDirectory = getServletContext().getRealPath("/upload/productProfile");// / -> Web Root Directory
		int maxPostSize = 30 * 1024 * 1024;
		String encoding = "utf-8";
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		MultipartRequest multipartReq = 
				new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		//사용자입력값처리
		String company = multipartReq.getParameter("company");
		String playName = multipartReq.getParameter("playName");
		String playLoc = multipartReq.getParameter("playLoc");
		String playAddr = multipartReq.getParameter("playAddr");
		int availCount = Integer.parseInt(multipartReq.getParameter("availCount"));
		int playPrice = Integer.parseInt(multipartReq.getParameter("playPrice"));
		String playNote = multipartReq.getParameter("playNote");
		String playLanguage = multipartReq.getParameter("playLanguage");
		int playTime = Integer.parseInt(multipartReq.getParameter("playTime"));
		String playFood = multipartReq.getParameter("playFood");
		String playEquipment = multipartReq.getParameter("playEquipment");
		String playTransfort = multipartReq.getParameter("playTransfort");
		String thumbnailOriginalFileName = multipartReq.getOriginalFileName("upProfile");
		String thumbnailRenamedFileName = multipartReq.getFilesystemName("upProfile");
		
		Play play = new Play(null, company, 
				thumbnailOriginalFileName, thumbnailRenamedFileName, 
				playName, 0, playLoc, playAddr, playPrice, 
				playTime, availCount, playLanguage, playNote, playFood, 
				playEquipment, playTransfort, 'N');
		//2. 업무로직
		int result = playService.playEnroll(play);
		
		System.out.println("playEnrollServlet@play = "+play);
		System.out.println("playEnrollServlet@play = "+play.getPlayId());
		
		//3.사용자 피드백(msg) 및 redirect처리 (/mvc/board/boardList)
		request.setAttribute("play", play);
		request.getRequestDispatcher("/WEB-INF/views/product/play/playImgEnroll.jsp").forward(request, response);
	}

}

package product.controller.play;

import java.io.File;
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

/**
 * Servlet implementation class PlayUpdateServlet
 */
@WebServlet("/product/play/productUpdate")
public class PlayUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PlayService playService = new PlayService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String playId = request.getParameter("playId");
		
		Play play = playService.selectPlayOne(playId);
		
		request.setAttribute("play", play);
		request.getRequestDispatcher("/WEB-INF/views/product/play/playUpdate.jsp").forward(request, response);
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
				String playId = multipartReq.getParameter("playId");
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
				
				String delFile = multipartReq.getParameter("delFile");
				String oldThumbnailOriginalFileName = multipartReq.getOriginalFileName("profileOriginalFileName");
				String oldTumbnailRenamedFileName = multipartReq.getFilesystemName("profileRenamedFileName");
				
				//기존파일이 있는 경우
				if(oldThumbnailOriginalFileName != null) {
					//1.업로드한 파일 가져오기 | 2.기존파일제거
					File upFile = multipartReq.getFile("upProfile");
					if(upFile != null || delFile != null) {
						//새파일 업로드 또는 기존파일 제거하는 경우
						File realDelFile = new File(saveDirectory, delFile);
						boolean bool = realDelFile.delete();
					}
					else {
						//3.기존파일정보를 유지하는 경우
						thumbnailOriginalFileName = oldThumbnailOriginalFileName;
						thumbnailRenamedFileName= oldTumbnailRenamedFileName;
					}
				}
				Play play = new Play(playId, null, thumbnailOriginalFileName, thumbnailRenamedFileName, 
						null, 0, null, playAddr, playPrice, playTime, availCount, 
						playLanguage, playNote, playFood, playEquipment, playTransfort, 'N');
				//2. 업무로직
				int result = playService.playUpdate(play);
												
				request.setAttribute("playId", playId);
				request.getRequestDispatcher("/WEB-INF/views/product/play/playImgUpdatePrompt.jsp").forward(request, response);
	}

}

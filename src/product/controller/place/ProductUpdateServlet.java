package product.controller.place;

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
import product.model.service.PlaceService;
import product.model.vo.Place;

@WebServlet("/product/place/productUpdate")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlaceService placeService = new PlaceService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String placeId = request.getParameter("placeId");
		
		Place place = placeService.selectPlaceId(placeId);
		
		request.setAttribute("place", place);
		request.getRequestDispatcher("/WEB-INF/views/product/place/placeUpdate.jsp").forward(request, response);
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
		String placeId = multipartReq.getParameter("placeId");
		String placeAddr = multipartReq.getParameter("placeAddr");
		int placePrice = Integer.parseInt(multipartReq.getParameter("placePrice"));
		int availCount = Integer.parseInt(multipartReq.getParameter("availCount"));
		String placeNote = multipartReq.getParameter("placeNote");
		int roomCount = Integer.parseInt(multipartReq.getParameter("roomCount"));
		String facility = multipartReq.getParameter("facility");
		String thumbnailOriginalFileName = multipartReq.getOriginalFileName("upProfile");
		String thumbnailRenamedFileName = multipartReq.getFilesystemName("upProfile");
		String delFile = multipartReq.getParameter("delFile");
		String memberId = multipartReq.getParameter("memberId");
		//기존첨부파일정보
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
		Place place =
				new Place(placeId, thumbnailOriginalFileName, thumbnailRenamedFileName, null,
						0, null, placeAddr, placePrice, availCount, placeNote, roomCount,
						facility, 'N', null);
		//2. 업무로직
		int result = placeService.placeUpdate(place);
										
		request.setAttribute("placeId", placeId);
		request.getRequestDispatcher("/WEB-INF/views/product/place/placeImgUpdatePrompt.jsp").forward(request, response);
	}

}

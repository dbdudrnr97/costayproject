package product.controller.place;

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
import common.MvcFileRenamePolicy;
import common.util.MvcUtils;
import member.model.vo.Member;
import product.model.service.PlaceService;
import product.model.vo.Place;


@WebServlet("/product/place/productEnroll")
public class PlaceEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PlaceService placeService = new PlaceService();   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("title", "숙소 등록");
		request.getRequestDispatcher("/WEB-INF/views/product/place/placeEnroll.jsp").forward(request, response);
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
		String memberId = multipartReq.getParameter("memberId");
		String placeName = multipartReq.getParameter("placeName");
		String placeLoc = multipartReq.getParameter("placeLoc");
		String placeAddr = multipartReq.getParameter("placeAddr");
		int placePrice = Integer.parseInt(multipartReq.getParameter("placePrice"));
		int availCount = Integer.parseInt(multipartReq.getParameter("availCount"));
		String placeNote = multipartReq.getParameter("placeNote");
		int roomCount = Integer.parseInt(multipartReq.getParameter("roomCount"));
		String facility = multipartReq.getParameter("facility");
		String thumbnailOriginalFileName = multipartReq.getOriginalFileName("upProfile");
		String thumbnailRenamedFileName = multipartReq.getFilesystemName("upProfile");
		
		
		
		Place place =
				new Place(null, thumbnailOriginalFileName, thumbnailRenamedFileName, placeName,
						0, placeLoc, placeAddr, placePrice, availCount, placeNote, roomCount,
						facility, 'N', memberId);
		//2. 업무로직
		int result = placeService.placeEnroll(place);
										
		//3.사용자 피드백(msg) 및 redirect처리 (/mvc/board/boardList)
		request.setAttribute("place", place);
		request.getRequestDispatcher("/WEB-INF/views/product/place/placeImgEnroll.jsp").forward(request, response);
	}

}

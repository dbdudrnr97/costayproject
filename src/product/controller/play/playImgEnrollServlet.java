package product.controller.play;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.MvcFileRenamePolicy;
import product.model.service.PlaceService;
import product.model.service.PlayService;
import product.model.vo.ImageStore;


@WebServlet("/product/play/playImgEnroll")
public class playImgEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PlayService playService = new PlayService();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//MultipartRequest객체 생성
		String saveDirectory = getServletContext().getRealPath("/upload/productFile");// / -> Web Root Directory
		int maxPostSize = 30 * 1024 * 1024;
		String encoding = "utf-8";
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		MultipartRequest multipartReq = 
				new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		String playId = multipartReq.getParameter("playId");

		int result = 0;
		ImageStore imageStore = null;
		Enumeration fileNames = multipartReq.getFileNames();
		while(fileNames.hasMoreElements()) {
			String parameter = (String) fileNames.nextElement();
			String fileName = multipartReq.getOriginalFileName(parameter);
			String fileRealName = multipartReq.getFilesystemName(parameter);
			
			if(fileName == null) return;
			
			imageStore = new ImageStore(0, fileName, fileRealName, playId, null);
			System.out.println("playImageEnrollServlet@imgstore = " + imageStore);
			
			result += playService.playImgEnroll(imageStore);
		}
		String msg = result == 3 ? "체험 등록 성공!" : "체험 등록 실패!"; 
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		response.sendRedirect(request.getContextPath());
	}

}

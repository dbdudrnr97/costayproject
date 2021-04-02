package product.controller.place;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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
import product.model.vo.ImageStore;
import product.model.vo.Place;

@WebServlet("/product/place/productImgUpdate")
public class ProductImgUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlaceService placeService = new PlaceService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String placeId = request.getParameter("placeId");
		
		List<ImageStore> list = placeService.selectImageStore(placeId);
		Place place = placeService.selectPlaceId(placeId);
		
		request.setAttribute("place", place);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/product/place/placeImgUpdate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//MultipartRequest객체 생성
		String saveDirectory = getServletContext().getRealPath("/upload/productFile");// / -> Web Root Directory
		int maxPostSize = 30 * 1024 * 1024;
		String encoding = "utf-8";
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		MultipartRequest multipartReq = 
				new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		String placeId = multipartReq.getParameter("placeId");

		//기존첨부파일정보
		String oldOriginalFileName1 = multipartReq.getOriginalFileName("OriginalFileName1");
		String oldRenamedFileName1 = multipartReq.getFilesystemName("RenamedFileName1");
		String oldOriginalFileName2 = multipartReq.getOriginalFileName("OriginalFileName2");
		String oldRenamedFileName2 = multipartReq.getFilesystemName("RenamedFileName2");
		String oldOriginalFileName3 = multipartReq.getOriginalFileName("OriginalFileName3");
		String oldRenamedFileName3 = multipartReq.getFilesystemName("RenamedFileName3");
		String delFile1 = multipartReq.getParameter("delFile1");
		String delFile2 = multipartReq.getParameter("delFile2");
		String delFile3 = multipartReq.getParameter("delFile3");
		
		List<String> oldOriginalList = new ArrayList<String>();
		oldOriginalList.add(oldOriginalFileName1);
		oldOriginalList.add(oldOriginalFileName2);
		oldOriginalList.add(oldOriginalFileName3);
		List<String> oldRenamedList = new ArrayList<String>();
		oldRenamedList.add(oldRenamedFileName1);
		oldRenamedList.add(oldRenamedFileName2);
		oldRenamedList.add(oldRenamedFileName3);
		List<String> delFileList = new ArrayList<String>();
		delFileList.add(delFile1);
		delFileList.add(delFile2);
		delFileList.add(delFile3);
		
		int result = 0;
		ImageStore imageStore = null;
		Enumeration fileNames = multipartReq.getFileNames();

		int i = 2;
		while(fileNames.hasMoreElements()) {
			String parameter = (String) fileNames.nextElement();
			String fileName = multipartReq.getOriginalFileName(parameter);
			String fileRealName = multipartReq.getFilesystemName(parameter);
			
			if(oldOriginalList.get(i) != null) {
				//1.업로드한 파일 가져오기 | 2.기존파일제거
				File upFile = multipartReq.getFile(parameter);
				if(upFile != null || delFileList.get(i) != null) {
					//새파일 업로드 또는 기존파일 제거하는 경우
					File realDelFile = new File(saveDirectory, delFileList.get(i));
					boolean bool = realDelFile.delete();
				}
				else {
					//3.기존파일정보를 유지하는 경우
					fileName = oldOriginalList.get(i);
					fileRealName= oldRenamedList.get(i);
				}
			}

			
			if(fileName == null) return;
			
			imageStore = new ImageStore(0, fileName, fileRealName, null, placeId);
			result += placeService.placeImgUpdate(imageStore);
			i--;
		}
		System.out.println(result);
		String msg = result == 9 ? "숙소 등록 성공!" : "숙소 등록 실패!"; 
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		response.sendRedirect(request.getContextPath());
	}

}

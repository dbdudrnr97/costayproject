package product.controller.place;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Registration;
import product.model.service.PlaceService;
import product.model.vo.ImageStore;
import product.model.vo.Place;

/**
 * Servlet implementation class PlaceViewDetailsServlet
 */
@WebServlet("/product/place/placeViewDetails")
public class PlaceViewDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	private PlaceService placeService = new PlaceService();
	
	//jsp 연결용
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regId = request.getParameter("regId");
		String placeId = request.getParameter("placeId");
		//ImageStore imageStore = null;
		//System.out.println("regId@placeServlet=" +regId);
		
//		list = placeService.selectImageStore(list);
		List<ImageStore> list = placeService.selectImageStore(placeId);
		Registration reg = memberService.selectRegistrationOne(regId);
		Place place = placeService.selectPlaceOne(placeId);
		String facility = placeService.selectPlaceFacility(placeId); //facility 를 db에서 받아서 split처리 -> facility 배열 생성
//		System.out.println("facility@servlet="+facility);
//		facility@servlet=취식,와이파이,주차장,바베큐장,TV,에어컨
//		facilityArr@servlet=[Ljava.lang.String;@f8ce22c
		String note = place.getPlaceNote().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		place.setPlaceNote(note);
		
//		String[] facilityArr = facility.split(",");  //여기서부터 문제ㅠㅠㅠㅠㅠㅠ
		//위의 facility를 잘라서 배열에다가 다 집어넣기
//		System.out.println("facilityArr@servlet="+facilityArr);
		
		request.setAttribute("reg", reg);
		request.setAttribute("place", place);
		request.setAttribute("list", list);
//		request.setAttribute("facilityArr", facilityArr);
		request.setAttribute("facility", facility);
//		System.out.println("facility@servlet="+facility);

		//System.out.println("list@servlet =" + list);
		
		request.getRequestDispatcher("/WEB-INF/views/product/place/placeViewDetails.jsp").forward(request, response);
   }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	
//	}

}

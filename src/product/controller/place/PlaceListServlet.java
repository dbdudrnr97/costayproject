package product.controller.place;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import product.model.service.PlaceService;
import product.model.vo.Place;

/**
 * Servlet implementation class PlaceListServlet
 */
@WebServlet("/place/placeList")
public class PlaceListServlet extends HttpServlet {
	private static final String notSelected = "notSelected";
	private static final long serialVersionUID = 1L;
    PlaceService placeService = new PlaceService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> param = new HashMap<>();
		int cpage = 1;
		int numPerPage = 9;
		int totalContents = placeService.selectPlaceCount();
		param.put("cpage", cpage);
		param.put("numPerPage", numPerPage);
		List<Place> list = placeService.selectPlaceList(param);

		request.setAttribute("type", PlaceService.TOTAL_SEARCH);
		HttpSession session = request.getSession();
		session.setAttribute("title", "전체 숙소");
		request.setAttribute("totalContents", new Integer(totalContents));
		request.setAttribute("placeList", list);
		request.getRequestDispatcher("/WEB-INF/views/product/place/placeList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			int cpage = 1;
			int numPerPage = 9;
			Map<String, Object> param = new HashMap<>();
			param.put("cpage", cpage);
			param.put("numPerPage", numPerPage);
			
			List<Place> list = new ArrayList<Place>();
			String loc = request.getParameter("loc");
			String price = request.getParameter("price");
			int type = 0;
			int totalContents = 0;
	//		System.out.println("test" + loc);
	//		System.out.println("test" + price);
			
			//1. 지역만 선택하는 경우
			if(!(loc.equals(notSelected)) && (price.equals(notSelected))) {
				list = placeService.selectLocalPlaceList(param, loc);
				totalContents = placeService.selectLocalPlaceCount(loc);
				type = PlaceService.LOCAL_SEARCH;
			}
		
			//2. 가격만 선택하는 경우
			if((loc.equals(notSelected)) && !(price.equals(notSelected))) {
				list = placeService.selectPricePlaceList(param, price);
				totalContents = placeService.selectPricePlaceCount(price);
				type = PlaceService.PRICE_SEARCH;
			}
			
			//3. 둘 다 선택하는 경우
			if(!(loc.equals(notSelected)) && !(price.equals(notSelected))) {
				list = placeService.selectLocPricePlaceList(param, loc, price);
				totalContents = placeService.selectLocPricePlaceCount(loc,price);
				type = PlaceService.LOC_PRICE_SEARCH;
			}

			//4. 둘 다 선택 안하는 경우(전체보기)
			if((loc.equals(notSelected)) && (price.equals(notSelected))) {
				list = placeService.selectPlaceList(param);
				totalContents = placeService.selectPlaceCount();
				type = PlaceService.TOTAL_SEARCH;
			}
			
			request.setAttribute("type", new Integer(type));
			request.setAttribute("location", loc);
			request.setAttribute("price", price);
			request.setAttribute("placeList", list);
			request.setAttribute("totalContents", new Integer(totalContents));
			request.getRequestDispatcher("/WEB-INF/views/product/place/placeList.jsp").forward(request, response);
		}catch(Exception e) {
			//예외처리
			e.printStackTrace();
		}		
	}
}

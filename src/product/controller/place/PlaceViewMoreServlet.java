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

import com.google.gson.Gson;

import product.model.service.PlaceService;
import product.model.vo.Place;

/**
 * Servlet implementation class PlaceViewMoreServlet
 */
@WebServlet("/place/placeViewMore")
public class PlaceViewMoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlaceService placeService = new PlaceService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int type = Integer.parseInt(request.getParameter("type"));
			int cpage = Integer.parseInt(request.getParameter("cpage"));
			String loc = request.getParameter("location");
			String price = request.getParameter("price");
			int numPerPage = 9;
			List<Place> list = new ArrayList<Place>();
			Map<String, Object> param = new HashMap<>();
			param.put("cpage", cpage);
			param.put("numPerPage", numPerPage);

			if(type == PlaceService.TOTAL_SEARCH) {
				//둘다 선택 안하는 경우
				list = placeService.selectPlaceList(param);
			}else if(type == PlaceService.LOCAL_SEARCH) {
				//지역만
				list = placeService.selectLocalPlaceList(param, loc);
			}else if(type == PlaceService.PRICE_SEARCH) {
				//가격만
				list = placeService.selectPricePlaceList(param, price);			
			}else if(type == PlaceService.LOC_PRICE_SEARCH) {
				//둘다 선택
				list = placeService.selectLocPricePlaceList(param, loc, price);
			}
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(list, response.getWriter());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

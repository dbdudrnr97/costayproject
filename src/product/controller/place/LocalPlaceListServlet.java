package product.controller.place;

import java.io.IOException;
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


@WebServlet("/product/place/localPlaceList")
public class LocalPlaceListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PlaceService placeSerivce = new PlaceService();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String placeLoc = request.getParameter("placeLoc");
		
		Map<String, Object> param = new HashMap<>();
		int cpage = 1;
		int numPerPage = 3;
		try {
			cpage = Integer.parseInt(request.getParameter("cpage"));			
		}catch(NumberFormatException e) {
			
		}
		param.put("cpage", cpage);
		param.put("numPerPage", numPerPage);
		int totalContents = placeSerivce.selectLocalPlaceCount(placeLoc);
		List <Place> list = placeSerivce.selectLocalPlace(placeLoc, param);
		
//		String url = request.getRequestURI();
//		String pageBar = MvcUtils.getPageBar(totalContents, cpage, numPerPage, url);
//		List <Place> list = placeSerivce.selectLocalPlace(placeLoc, cpage, numPerPage); 
		
		request.setAttribute("placeLoc", placeLoc);
		request.setAttribute("totalContents", new Integer(totalContents));
//		request.setAttribute("cpage", new Integer(cpage));
//		request.setAttribute("pageBar",pageBar);	
		request.getRequestDispatcher("/WEB-INF/views/product/place/localPlaceList.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String placeLoc = request.getParameter("placeLoc");
		Map<String, Object> param = new HashMap<>();
		int cpage = 1;
		int numPerPage = 3;
		try {
			cpage = Integer.parseInt(request.getParameter("cpage"));			
		}catch(NumberFormatException e) {
			
		}
		param.put("cpage", cpage);
		param.put("numPerPage", numPerPage);
		List <Place> list = placeSerivce.selectLocalPlace(placeLoc, param);
		
	    response.setContentType("application/json; charset=utf-8");
	    new Gson().toJson(list, response.getWriter());
	}

}

package product.controller.place;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import product.model.service.PlaceService;
import product.model.vo.Place;

/**
 * Servlet implementation class PlaceRandomPickServlet
 */
@WebServlet("/place/placeRandom")
public class PlaceRandomPickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlaceService placeService = new PlaceService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Place> list = new ArrayList<Place>();
		list = placeService.selectPlaceRandomPick();
		
	    response.setContentType("application/json; charset=utf-8");
	    new Gson().toJson(list, response.getWriter());
	}

}

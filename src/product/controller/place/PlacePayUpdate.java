package product.controller.place;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import product.model.service.PlaceService;

@WebServlet("/product/place/pay")
public class PlacePayUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlaceService placeService = new PlaceService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regId = request.getParameter("regId");
		
		int result = placeService.payUpdate(regId);
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(result, response.getWriter());
	}

}

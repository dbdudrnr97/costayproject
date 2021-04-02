package product.controller.place;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.model.vo.Registration;
import product.model.service.PlaceService;

/**
 * Servlet implementation class MyRegListServlet
 */
@WebServlet("/product/place/myReg")
public class MyRegListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private PlaceService placeService = new PlaceService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String placeId = request.getParameter("placeId");
		List<Registration> list = placeService.myRegList(placeId);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(list, response.getWriter());
	}

}

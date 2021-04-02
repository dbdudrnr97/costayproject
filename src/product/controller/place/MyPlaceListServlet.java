package product.controller.place;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Registration;
import product.model.service.PlaceService;
import product.model.vo.Place;

@WebServlet("/product/place/myPlace")
public class MyPlaceListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlaceService placeService = new PlaceService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		
		List<Place> list = placeService.selectMyPlace(memberId);	
		HttpSession session = request.getSession();
		session.setAttribute("title", "내 숙소정보");
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/product/place/myPlace.jsp").forward(request, response);
	}

}

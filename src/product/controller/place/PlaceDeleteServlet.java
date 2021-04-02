package product.controller.place;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.PlaceService;

/**
 * Servlet implementation class PlaceDeleteServlet
 */
@WebServlet("/product/place/productDelete")
public class PlaceDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PlaceService placeService = new PlaceService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String placeId = request.getParameter("placeId");
		
		int result = placeService.placeDelete(placeId);
		
		String msg = result > 0 ? "숙소가 삭제되었습니다." : "숙소가 삭제되지 않았습니다.";
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath());
	}

}

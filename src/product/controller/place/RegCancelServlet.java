package product.controller.place;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import product.model.service.PlaceService;

/**
 * Servlet implementation class RegCancelServlet
 */
@WebServlet("/product/place/regCancel")
public class RegCancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PlaceService placeService = new PlaceService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regId = request.getParameter("regId");
		
		int result = placeService.regCancel(regId);
		
		String msg = result >0 ? "거절완료." : "거절실패."; 
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		response.sendRedirect(request.getContextPath());
	}
}

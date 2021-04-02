package product.controller.place;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Registration;
import product.model.service.PlaceService;

@WebServlet("/product/place/placeReg")
public class PlaceRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlaceService placeService = new PlaceService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date regChkinDate = Date.valueOf(request.getParameter("regChkinDate"));
		int guestCount = Integer.parseInt(request.getParameter("guestCount"));
		String placeId = request.getParameter("placeId");
		
		request.setAttribute("regChkinDate", regChkinDate);
		request.setAttribute("guestCount", guestCount);
		request.setAttribute("placeId", placeId);
		request.getRequestDispatcher("/WEB-INF/views/product/place/placeReg.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		int guestCount = Integer.parseInt(request.getParameter("guestCount"));
		Date regChkinDate = Date.valueOf(request.getParameter("regChkinDate"));
		Date regChkOutDate = Date.valueOf(request.getParameter("regChkoutDate"));
		String placeId = request.getParameter("placeId");
		
		Registration reg = new Registration(null, guestCount, null, regChkinDate, 
				regChkOutDate, 'N', 'C', memberId, null, placeId);
		
		int result = placeService.placeRegEnroll(reg);
		
		String msg = result >0 ? "예약신청이 완료되었습니다." : "예약신청이 처리되지 않았습니다."; 
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		response.sendRedirect(request.getContextPath());
	}

}

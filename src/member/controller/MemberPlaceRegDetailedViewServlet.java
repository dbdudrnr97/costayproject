package member.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Registration;
import product.model.service.PlaceService;
import product.model.vo.Place;

/**
 * Servlet implementation class MemberPlaceRegDetailedView
 */
@WebServlet("/member/placeRegView/detailedView")
public class MemberPlaceRegDetailedViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	private PlaceService placeService = new PlaceService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regId = request.getParameter("reg");
		String placeId = request.getParameter("place");
		Registration reg = null;
		Place place = null;
		
		reg = memberService.selectRegistrationOne(regId);
		place = placeService.selectPlaceOne(placeId);
		
		request.setAttribute("reg", reg);
		request.setAttribute("place", place);
		HttpSession session = request.getSession();
		session.setAttribute("title", "예약 상세");
		request.getRequestDispatcher("/WEB-INF/views/member/placeRegDetailedView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		//post로 받아오지 못함.
//		
//		String reg = request.getParameter("reg");
//		String play = request.getParameter("play");
//	}
}

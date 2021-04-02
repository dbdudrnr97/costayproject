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
import product.model.service.PlayService;
import product.model.vo.Play;

/**
 * Servlet implementation class MemberPlaceRegDetailedView
 */
@WebServlet("/member/playRegView/detailedView")
public class MemberPlayRegDetailedViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	private PlayService playService = new PlayService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regId = request.getParameter("reg");
		String playId = request.getParameter("play");
		Registration reg = null;
		Play play = null;
//		System.out.println("test" + reg);
//		System.out.println("test" + play);
		
		reg = memberService.selectRegistrationOne(regId);
		play = playService.selectPlayOne(playId);
		
		request.setAttribute("reg", reg);
		request.setAttribute("play", play);
		HttpSession session = request.getSession();
		session.setAttribute("title", "예약상세");
		request.getRequestDispatcher("/WEB-INF/views/member/playRegDetailedView.jsp").forward(request, response);
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

package member.controller;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;
import product.model.vo.Place;

/**
 * Servlet implementation class MemberPlayView
 */
@WebServlet("/member/placeRegView")
public class MemberPlaceRegViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member m = (Member)request.getSession().getAttribute("memberLoggedIn");
		Map<String, Place> expectedMap = new HashMap<String, Place>();
		Map<String, Place> previousMap = new HashMap<String, Place>();
		Map<String, Place> waitMap = new HashMap<String, Place>();
		
		//멤버 id로 예정된 예약(현재 날짜와 체크아웃 날짜 비교) 찾고, 예약과 일치하는 체험 검색
		expectedMap = memberService.selectExpectedPlace(m.getMemberId(), expectedMap);
		//멤버 id로 만료된 예약(현재 날짜와 체크아웃 날짜 비교) 찾고, 예약과 일치하는 체험 검색
		previousMap = memberService.selectPreviousPlace(m.getMemberId(), previousMap);
		//DelFlag로 승인 대기중인 예약 찾기
		waitMap = memberService.selectWaitPlace(m.getMemberId(), waitMap);
		HttpSession session = request.getSession();
		session.setAttribute("title", "예약내역");
		request.setAttribute("expected", expectedMap);
		request.setAttribute("previous", previousMap);		
		request.setAttribute("wait", waitMap);		
		request.getRequestDispatcher("/WEB-INF/views/member/placeRegView.jsp").forward(request, response);
	}
}

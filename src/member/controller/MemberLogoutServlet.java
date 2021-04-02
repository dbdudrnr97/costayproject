package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberLogoutServlet
 */
@WebServlet("/member/logout")
public class MemberLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		//false 옵션 -> 해당하는 세션이 없을 때 새로 만들지 말라고 null 리턴
		if(session != null)
			session.invalidate();
		String location = request.getHeader("Referer");

		//인덱스페이지 재요청하는 리다이렉트 처리
//		response.sendRedirect(request.getContextPath());
		response.sendRedirect(location);
	}
}

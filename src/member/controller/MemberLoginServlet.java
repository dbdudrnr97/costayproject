package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.util.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String location = request.getHeader("Referer");
		HttpSession session = request.getSession();
		session.setAttribute("title", "로그인");
		request.setAttribute("location", location);
		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩처리
		request.setCharacterEncoding("utf-8");
		
		//2. 사용자입력값 처리
		String memberId = request.getParameter("memberId");
		String password = MvcUtils.getEncryptedPassword(request.getParameter("password"));
		String saveId = request.getParameter("saveId");
		String location = request.getParameter("location");		
		
		Member member = memberService.selectOne(memberId);
		
		//로그인 성공
		if(member != null && password.equals(member.getPassword())) {
			HttpSession session = request.getSession(true);
			session.setAttribute("memberLoggedIn",  member);

			Cookie c = new Cookie("saveId", memberId);
			c.setPath(request.getContextPath()); //쿠키전송 디렉터리. 
			
			//saveId쿠키 설정
			if(saveId != null) {
				//saveId 체크한 경우
				//유효기간 설정(초단위). client(브라우져)에서 쿠키를 보관할 시간 설정
				c.setMaxAge(7 * 24 * 60 * 60);
			}else {
				//saveId 체크 안한 경우
				c.setMaxAge(0); //즉시삭제
			}
			response.addCookie(c);
			System.out.println("로그인 성공!");
			response.sendRedirect(location);
		}
		//로그인 실패 : 아이디 존재X, 비번이 틀린 경우
		else {
			HttpSession session = request.getSession(true);
			session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			System.out.println("로그인 실패!");
			response.sendRedirect(location);
		}
	}
}

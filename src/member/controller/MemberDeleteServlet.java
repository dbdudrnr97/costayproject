package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("/WEB-INF/views/member/delete.jsp")
//			   .forward(request, response);
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession(true);
		Member member = (Member)session.getAttribute("memberLoggedIn");

		//서비스 로직 호출
		int deleteChk = memberService.deleteMember(member.getMemberId());

		//받은 결과에 따라 뷰페이지 내보내기
		String msg = null;
		String loc = null;
		
		if(deleteChk != 0) {
			msg = "회원탈퇴를 성공하였습니다.";
			loc = request.getContextPath() + "/member/logout";//회원탈퇴인 경우, 로그아웃 처리함.
		}else {
			msg = "회원탈퇴를 실패하였습니다.";
			loc = request.getContextPath();
		}
		//DML, login 등 요청후 반드시 url을 변경해서 새로고침 사고를 방지한다.
		if(session!=null) {
			session.invalidate();
		}
		
		response.sendRedirect(loc);
	}

}

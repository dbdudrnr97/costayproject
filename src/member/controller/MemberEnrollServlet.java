package member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import common.MvcFileRenamePolicy;
import common.util.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/member/enroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("title", "회원가입");
		request.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp")
			   .forward(request, response);
	}

	/**
	 * 회원가입
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//MultipartRequest객체 생성
		String saveDirectory = getServletContext().getRealPath("/upload/memberProfile");// / -> Web Root Directory
		int maxPostSize = 30 * 1024 * 1024;
		String encoding = "utf-8";
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		MultipartRequest multipartReq = 
				new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		//사용자입력값처리
		Date birth = null;
		String date = multipartReq.getParameter("birth");
		if(!(date.equals(""))) {
			birth = Date.valueOf(date);
		}
		String memberEmail = multipartReq.getParameter("memberEmail");
		if(memberEmail.equals("")) {
			memberEmail = null;
		}
		String memberAddress = multipartReq.getParameter("memberAddress");
		if(memberAddress.equals("")) {
			memberAddress = null;
		}
		
		Member member = new Member();
		
		member.setMemberId(multipartReq.getParameter("memberId"));
		member.setPassword(MvcUtils.getEncryptedPassword(multipartReq.getParameter("password")));
		member.setMemberName(multipartReq.getParameter("memberName"));
		member.setMemberProfileOriginalFileName(multipartReq.getOriginalFileName("upProfile"));
		member.setMemberProfileRenamedFileName(multipartReq.getFilesystemName("upProfile"));
		member.setMemberPassport(multipartReq.getParameter("memberPassport"));
		member.setPhone(multipartReq.getParameter("phone"));
		member.setGender(multipartReq.getParameter("gender"));
		member.setBirth(birth);
		member.setMemberAddress(memberAddress);
		member.setMemberEmail(memberEmail);
		member.setMemberEnrollDate(null);
		member.setMemberDelFlag('N');	// 
		
		HttpSession session = request.getSession();
		
		int enrollChk = memberService.insertMember(member);
		if(enrollChk != 0) {
			session.setAttribute("msg", "회원가입이 완료되었습니다.");
		} else {
			session.setAttribute("msg", "회원가입에 실패하셨습니다. 다시 시도해주세요.");
		}
		
		request.setAttribute("loc", request.getContextPath());
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/index.jsp");		
		reqDispatcher.forward(request, response);
		
	}

}

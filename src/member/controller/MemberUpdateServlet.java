package member.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import board.model.vo.Board;
import common.MvcFileRenamePolicy;
import common.util.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		Member member = memberService.selectOne(memberId);
		HttpSession session = request.getSession();
		session.setAttribute("title", "정보수정");
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/views/member/memberUpdate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 파일이 포함된 사용자 요청 처리 MultipartRequest객체 생성 */
		/*
		 new MultipartRequest(
		 			HttpServletRequest request, 
		 			String saveDirectory, 		//업로드파일의 저장경로(절대경로)
		 			int maxPostSize, 			//최대크기제한 10mb
		 			String encoding, 			//인코딩
		 			FileRenamePolicy policy 	//파일이름 재지정 정책 객체
		 		)
		 */
		//application : WAS실행시부터 종료시까지 운영되는 객체
		String saveDirectory = getServletContext().getRealPath("/upload/memberProfile");// / -> Web Root Directory
		
		//byte단위 : 1mb = 1kb * 1kb
		int maxPostSize = 30 * 1024 * 1024;
		
		String encoding = "utf-8";
		
		//중복파일에 대해서 number부여
		FileRenamePolicy policy = new MvcFileRenamePolicy();
		
		//MultipartRequest객체 생성
		MultipartRequest multipartReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		String memberId = multipartReq.getParameter("memberId");
		String memberName = multipartReq.getParameter("memberName");
		String password = MvcUtils.getEncryptedPassword(multipartReq.getParameter("password"));
		System.out.println(password);
		String passport = multipartReq.getParameter("passport");
		String phone = multipartReq.getParameter("phone");
		String address = multipartReq.getParameter("address");
		String email = multipartReq.getParameter("email");
		String delFile = multipartReq.getParameter("delFile");
		String ProfileOriginalFileName = multipartReq.getOriginalFileName("upProfile");
		String ProfileRenamedFileName = multipartReq.getFilesystemName("upProfile");
		//기존첨부파일 정보
		String oldProfileOriginalFileName = multipartReq.getParameter("profileOriginalFileName");
		String oldProfileRenamedFileName = multipartReq.getParameter("profileRenamedFileName");
		
		
		//기존파일이 있는 경우
		if(oldProfileOriginalFileName != null) {
			//1.업로드한 파일 가져오기 | 2.기존파일제거
			File upFile = multipartReq.getFile("upProfile");
			if(upFile != null || delFile != null) {
				//새파일 업로드 또는 기존파일 제거하는 경우
				File realDelFile = new File(saveDirectory, delFile);
				boolean bool = realDelFile.delete();
				System.out.println(delFile + " : " + (bool ? "기존 파일 삭제 성공!" : "기존 파일 삭제 실패!"));
			}
			else {
				//3.기존파일정보를 유지하는 경우
				ProfileOriginalFileName = oldProfileOriginalFileName;
				ProfileRenamedFileName= oldProfileRenamedFileName;
			}
		}
		
		
		Member member =
				new Member(memberId, password, memberName, ProfileOriginalFileName, ProfileRenamedFileName,
							passport, phone, null, 0, null, address, email, null, 'n' , 'n');
		
		//2. 업무로직 : Board객체 db수정 요청
		int result = memberService.updateMember(member);
		String msg = result > 0 ? "정보 수정 성공!" : "정보 수정 실패!"; 
		String location = request.getContextPath() + "/member/memberView?memberId=" + member.getMemberId();
							
				
		//3.사용자 피드백(msg) 및 redirect처리
		//DML이후 반드시 요청url을 변경할 것
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(location);
	}

}

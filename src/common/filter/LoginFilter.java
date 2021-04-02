package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter({ "/member/delete", "/member/myPage", "/member/logout", "/member/placeRegView/detailedView",
			"/member/placeRegistration", "/member/placeRegView", "/member/playRegView/detailedView",
			"/member/playRegView", "/member/memberUpdate", "/member/memberView",
			"/product/place/myPlace", "/product/place/productDelete", "/product/place/productEnroll",
			"/product/place/placeImgEnroll", "/product/place/productImgUpdate", "/product/place/productUpdate",
			"/product/play/playDelete", "/product/play/playImgEnroll", "/product/play/playImgUpdate",
			"/product/play/productUpdate", "/board/faq/boardDelete", "/board/faq/boardEnroll",
			"/board/faq/boardUpdate", "/board/newHost/boardDelete", "/board/newHost/boardEnroll",
			"/board/newHost/boardUpdate", "/board/newHost/CommentDelete", "/board/newHost/CommentInsert",	
			"/board/notice/boardDelete", "/board/notice/boardEnroll", "/board/notice/boardUpdate",
			"/board/review/boardDelete", "/board/review/boardEnroll","/board/review/boardUpdate",
			"/board/review/CommentInsert", "/product/play/playReg", "/product/place/placeReg"})
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpSession session = httpReq.getSession();
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
		
		//로그인하지 않은 경우
		if(memberLoggedIn == null) {
			HttpServletResponse httpResp = (HttpServletResponse)response;
			session.setAttribute("msg",  "로그인 후 이용하실 수 있습니다.");
			httpResp.sendRedirect(httpReq.getContextPath());
			return;
		}
		
		chain.doFilter(request, response);
	
	}
}

//package common.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import member.model.vo.Member;
//
///**
// * Servlet Filter implementation class MemberAuthFilter
// */
//@WebFilter({ "/member/myPage", "/member/memberUpdate"})
//public class MemberAuthFilter implements Filter {
//
//    /**
//     * Default constructor. 
//     */
//    public MemberAuthFilter() {
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see Filter#init(FilterConfig)
//	 */
//	public void init(FilterConfig fConfig) throws ServletException {
//		// TODO Auto-generated method stub
//	}
//	/**
//	 * @see Filter#destroy()
//	 */
//	public void destroy() {
//		// TODO Auto-generated method stub
//	}
//
//	/**
//	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
//	 */
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest httpReq = (HttpServletRequest)request;
//		HttpSession session = httpReq.getSession();
//		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
//		
//		//다른 사용자 정보로 접근하는 경우
////		System.out.println("1 : " + memberLoggedIn.getMemberId());
//		String UriId = httpReq.getParameter("memberId");
////		System.out.println("2 : " + UriId);
//		
//		if(UriId == null || memberLoggedIn == null || !(memberLoggedIn.getMemberId().equals(UriId))) {
//			HttpServletResponse httpResp = (HttpServletResponse)response;
//			session.setAttribute("msg",  "잘못된 경로로 접근하셨습니다.");
//			httpResp.sendRedirect(httpReq.getContextPath());
//			return;
//		}
//
//
//		// pass the request along the filter chain
//		chain.doFilter(request, response);
//	}
//
//
//}

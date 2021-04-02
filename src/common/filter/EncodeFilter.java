package common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class EncodeFilter implements Filter {

    public EncodeFilter() {
    }

	public void init(FilterConfig fConfig) throws ServletException {
	}
	public void destroy() {
	}

	public void doFilter(ServletRequest request, 
						 ServletResponse response, 
						 FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
//		System.out.println("set Encode");
		chain.doFilter(request, response);
		
	}

	

}

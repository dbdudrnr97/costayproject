package product.controller.play;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.service.PlayService;

/**
 * Servlet implementation class PlayDeleteServlet
 */
@WebServlet("/product/play/playDelete")
public class PlayDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlayService playService = new PlayService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String playId = request.getParameter("playId");
		
		int result = playService.playDelete(playId);
		
		String msg = result > 0 ? "숙소가 삭제되었습니다." : "숙소가 삭제되지 않았습니다.";
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath());
	}

}

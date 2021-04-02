package product.controller.play;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.model.vo.Registration;
import product.model.service.PlayService;
import product.model.vo.Play;


@WebServlet("/product/play/playReg")
public class PlayRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlayService playService = new PlayService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date regChkinDate = Date.valueOf(request.getParameter("regChkinDate"));
		int guestCount = Integer.parseInt(request.getParameter("guestCount"));
		String playId = request.getParameter("playId");
		
		Play play = playService.selectPlayOne(playId);
		
		request.setAttribute("regChkinDate", regChkinDate);
		request.setAttribute("guestCount", guestCount);
		request.setAttribute("play", play);
		request.getRequestDispatcher("/WEB-INF/views/product/play/playReg.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date regChkinDate = Date.valueOf(request.getParameter("regChkinDate"));
		int guestCount = Integer.parseInt(request.getParameter("guestCount"));
		String playId = request.getParameter("playId");
		String memberId = request.getParameter("memberId");
		
		Registration reg = new Registration(null, guestCount, null, regChkinDate, null, 'Y',
							'N', memberId, playId, null);
		
		int result = playService.playRegistration(reg);
		
		String msg = result > 0 ? "예약이 완료되었습니다." : "예약실패";
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(msg, response.getWriter());
	}

}

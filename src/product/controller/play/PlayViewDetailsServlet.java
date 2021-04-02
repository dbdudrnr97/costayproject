package product.controller.play;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import product.model.service.PlayService;
import product.model.vo.ImageStore;
import product.model.vo.Play;

@WebServlet("/product/play/playViewDetails")
public class PlayViewDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PlayService playService = new PlayService();
	
	//jsp연결용
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String playId = request.getParameter("playId");
		System.out.println(playId);
		
		List<ImageStore> list = playService.selectPlayImageStore(playId);
		Play play = playService.selectPlayOne(playId);
		
		String note = play.getPlayNote().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		play.setPlayNote(note);
//		
//		System.out.println("list@servlet="+list);
//		System.out.println("play@servlet="+play);
//		
		request.setAttribute("play", play);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/product/play/playViewDetails.jsp").forward(request, response);
	}

}

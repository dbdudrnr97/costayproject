package product.controller.play;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import product.model.service.PlayService;
import product.model.vo.Play;

/**
 * Servlet implementation class PlaceRandomPickServlet
 */
@WebServlet("/play/playRandom")
public class PlayRandomPickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlayService playService = new PlayService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Play> list = new ArrayList<Play>();
		list = playService.selectPlayRandomPick();
		
	    response.setContentType("application/json; charset=utf-8");
	    new Gson().toJson(list, response.getWriter());
	}

}

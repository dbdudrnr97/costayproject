package product.controller.play;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import product.model.service.PlayService;
import product.model.vo.Play;

/**
 * Servlet implementation class PlayViewMoreServlet
 */
@WebServlet("/play/playViewMore")
public class PlayViewMoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlayService playService = new PlayService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int type = Integer.parseInt(request.getParameter("type"));
			int cpage = Integer.parseInt(request.getParameter("cpage"));
			String loc = request.getParameter("location");
			String price = request.getParameter("price");
			int numPerPage = 9;
			List<Play> list = new ArrayList<Play>();
			Map<String, Object> param = new HashMap<>();
			param.put("cpage", cpage);
			param.put("numPerPage", numPerPage);
			
			if(type == PlayService.TOTAL_SEARCH) {
				//둘다 선택 안하는 경우
				list = playService.selectPlayList(param);
				System.out.println(list);
			}else if(type == PlayService.LOCAL_SEARCH) {
				//지역만
				list = playService.selectLocalPlayList(param, loc);
			}else if(type == PlayService.PRICE_SEARCH) {
				//가격만
				list = playService.selectPricePlayList(param, price);			
			}else if(type == PlayService.LOC_PRICE_SEARCH) {
				//둘다 선택
				list = playService.selectLocPricePlayList(param, loc, price);
			}
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(list, response.getWriter());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

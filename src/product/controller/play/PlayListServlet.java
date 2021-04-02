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
 * Servlet implementation class PlayListServlet
 */
@WebServlet("/play/playList")
public class PlayListServlet extends HttpServlet {
	private static final String notSelected = "notSelected";
	private static final long serialVersionUID = 1L;
    PlayService playService = new PlayService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> param = new HashMap<>();
		int cpage = 1;
		int numPerPage = 9;
		int totalContents = playService.selectPlayCount();
		param.put("cpage", cpage);
		param.put("numPerPage", numPerPage);
		List<Play> list = playService.selectPlayList(param);

		request.setAttribute("type", PlayService.TOTAL_SEARCH);
		request.setAttribute("totalContents", new Integer(totalContents));
		request.setAttribute("playList", list);
		request.getRequestDispatcher("/WEB-INF/views/product/play/playList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			int cpage = 1;
			int numPerPage = 9;
			Map<String, Object> param = new HashMap<>();
			param.put("cpage", cpage);
			param.put("numPerPage", numPerPage);
			
			List<Play> list = new ArrayList<Play>();
			String loc = request.getParameter("loc");
			String price = request.getParameter("price");
			int type = 0;
			int totalContents = 0;
	//		System.out.println("test" + loc);
	//		System.out.println("test" + price);
			
			//1. 지역만 선택하는 경우
			if(!(loc.equals(notSelected)) && (price.equals(notSelected))) {
				list = playService.selectLocalPlayList(param, loc);
				totalContents = playService.selectLocalPlayCount(loc);
				type = PlayService.LOCAL_SEARCH;
			}
		
			//2. 가격만 선택하는 경우
			if((loc.equals(notSelected)) && !(price.equals(notSelected))) {
				list = playService.selectPricePlayList(param, price);
				totalContents = playService.selectPricePlayCount(price);
				type = PlayService.PRICE_SEARCH;
			}
			
			//3. 둘 다 선택하는 경우
			if(!(loc.equals(notSelected)) && !(price.equals(notSelected))) {
				list = playService.selectLocPricePlayList(param, loc, price);
				totalContents = playService.selectLocPricePlayCount(loc,price);
				type = PlayService.LOC_PRICE_SEARCH;
			}

			//4. 둘 다 선택 안하는 경우(전체보기)
			if((loc.equals(notSelected)) && (price.equals(notSelected))) {
				list = playService.selectPlayList(param);
				totalContents = playService.selectPlayCount();
				type = PlayService.TOTAL_SEARCH;
			}
			
			request.setAttribute("type", new Integer(type));
			request.setAttribute("location", loc);
			request.setAttribute("price", price);
			request.setAttribute("playList", list);
			request.setAttribute("totalContents", new Integer(totalContents));
			request.getRequestDispatcher("/WEB-INF/views/product/play/playList.jsp").forward(request, response);
		}catch(Exception e) {
			//예외처리
			e.printStackTrace();
		}		
	}
}

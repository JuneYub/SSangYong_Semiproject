package parkjuneyub.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import common.controller.AbstractController;
import parkjuneyub.order.model.OrderDeatailVO;
import parkjuneyub.order.model.OrderVO;
import parkjuneyub.board.model.BoardDAO;
import parkjuneyub.board.model.InterBoardDAO;

public class WriteReviewAction extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod();
		if("get".equalsIgnoreCase(method)) {
			String user_id = request.getParameter("userid");
			String product_num = request.getParameter("product_num");
			
			InterBoardDAO bdao = new BoardDAO();
			List<OrderVO> odrDetailList = bdao.getOrderDeatailList(user_id, product_num);
			request.setAttribute("odrDetailList", odrDetailList);
			request.setAttribute("user_id", user_id);
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/pjy_board/writeReview.jsp");
		}
		
		if("post".equalsIgnoreCase(method)) {
			String user_id = request.getParameter("user_id");
			Long product_id = Long.parseLong(request.getParameter("product_num"));
			String select_order_num = request.getParameter("select_order_num");
			int review_rating = Integer.parseInt(request.getParameter("review_rating"));
			String review_contents = request.getParameter("review_contents");
			
			Map<String, Object> map = new HashMap<>();
			map.put("user_id", user_id);
			map.put("product_id", product_id);
			map.put("select_order_num", select_order_num);
			map.put("review_rating", review_rating);
			map.put("review_contents", review_contents);
			
			InterBoardDAO bdao = new BoardDAO();
			int n = bdao.insertReview(map);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("n", n);
			String json = jsonObj.toString();
			request.setAttribute("json", json);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/jsonview.jsp");
			
		}
		

	}

}

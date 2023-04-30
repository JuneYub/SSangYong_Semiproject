package parkjuneyub.product.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import parkjuneyub.board.model.BoardDAO;
import parkjuneyub.board.model.InterBoardDAO;
import parkjuneyub.board.model.ReviewVO;
import parkjuneyub.product.model.InterProductDAO;
import parkjuneyub.product.model.ProductDAO;
import parkjuneyub.product.model.ProductVO;

public class ProductDetailAction extends AbstractController {
	

	/// http://localhost:9090/SemiProject/productDeatail.ban?product_num=9 이런식으로 입력하셔야 상품 상세페이지 들어가실 수 있습니다.
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		String product_num = request.getParameter("product_num");
		if(product_num == null) {
			product_num = "";
		}
		if(product_num.trim().isEmpty()) {
			
			String message = "비정상적인 경로로 들어왔습니다.";
			String loc = "javascript:history.back()";
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
		}
		
		if(!product_num.trim().isEmpty()) {
			try {
				long num = Long.parseLong(product_num);
			}
			catch(NumberFormatException e) {
				String message = "비정상적인 경로로 들어왔습니다.";
				String loc = "javascript:history.back()";
				request.setAttribute("message", message);
				request.setAttribute("loc", loc);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/msg.jsp");
				return;
			}
			
			
			
			ProductVO pvo = new ProductVO();
			InterProductDAO pdao = new ProductDAO();
			pvo = pdao.showItemInfo(product_num);
			
			InterBoardDAO bdao = new BoardDAO();
			List<ReviewVO> reviewList = bdao.getReivewList(product_num);
			
			request.setAttribute("reviewList", reviewList);
			request.setAttribute("pvo", pvo);
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/pjy_product/productDetail.jsp");
		}
		
		
	}
	
}

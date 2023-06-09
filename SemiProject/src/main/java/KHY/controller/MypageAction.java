package KHY.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import KHY.product.model.InterProductDAO;
import KHY.product.model.OrderDeatailVO;
import KHY.product.model.ProductDAO;
import common.controller.AbstractController;
import sge.member.model.MemberVO;





public class MypageAction extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
	    
		if(loginuser != null)  {
			// 로그인을 했으면
			
				InterProductDAO pdao = new ProductDAO(); 
		         
				List<OrderDeatailVO> orderList = pdao.selectOrderList(loginuser.getUser_id());  
		        
				int total = pdao.selectStatus(loginuser.getUser_id());
				
				request.setAttribute("orderList", orderList);
				request.setAttribute("total", total);
				
		        
			
			
				// 로그인한 사용자가 자신의 마이페이지를 보는 경우
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/KHY/mypage_main.jsp");
				
		
			
			
		}
		else {
			// 로그인을 안했으면
			String message = "마이페이지를 보기 위해서는 먼저 로그인을 하세요!!";
			String loc = "javascript:history.back()";
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
			
			
		}
		
	}
	
	
	
}

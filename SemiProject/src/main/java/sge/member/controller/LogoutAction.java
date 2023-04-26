package sge.member.controller;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractController;
import sge.member.model.MemberVO;


public class LogoutAction extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그아웃 처리하기
		
		//세션 불러오기 
		HttpSession session = request.getSession();
		
		/////////////////////////////////////////////////
		// 로그아웃을 하면 시작페이지로 가는 것이 아니라 방금 보았던 그 페이지로 그대로 가기 위한 것임.
		//String goBackURL = (String)session.getAttribute("goBackURL");
		
		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
		// 첫번째 방법: 세션을 그대로 존재하게 끔 해두고 세션에 저장되어진 어떤 값(지금은 로그인 되어진 회원객체);
		//session.removeAttribute("loginuser");
		// 두번쨰 방법: WAS 메모리 상에서 
		session.invalidate(); // 세션 정보 날리기 
		/////////////////////////////////////////////////////////////////
		super.setRedirect(true);

		/*if(goBackURL != null && !"admin".equals(loginuser.getUser_id()) ) {
			super.setViewPage(request.getContextPath()+ goBackURL);
		}
		else {*/
		super.setViewPage(request.getContextPath()+"/home.ban");
		//}

	}
}

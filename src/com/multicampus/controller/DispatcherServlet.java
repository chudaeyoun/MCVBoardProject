package com.multicampus.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.multicampus.biz.user.UserDAO;
import com.multicampus.biz.user.UserVO;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DispatcherServlet() {
    	System.out.println("===> DispatcherServlet 생성");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. 사용자 요청 path 정보를 추출한다. 
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println(path);
		
		// 2. 추출된 path에 해당하는 로직으로 분기처리한다. 
		if(path.equals("/login.do")) {
			System.out.println("로그인 기능 처리");
			
			// 1. 사용자 입력정보(id, password) 추출
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			// 2. DB 연동 처리
			UserVO vo = new UserVO();
			vo.setId(id);
			vo.setPassword(password);
			
			UserDAO userDAO = new UserDAO();
			UserVO user = userDAO.getUser(vo);
			 
			// 3. 화면 네비게이션
			if(user != null) {
				HttpSession session = request.getSession();
				// 로그인 성공했을 때 세션에 상태정보를 저장한다. 
				session.setMaxInactiveInterval(60 * 60 * 1);
				session.setAttribute("user", user);
				response.sendRedirect("getBoardList.jsp");
			} else {
				response.sendRedirect("login.html");
			}
			
			
		} else if(path.equals("/logout.do")) {
			System.out.println("로그아웃 기능 처리");
		} else if(path.equals("/insertBoard.do")) {
			System.out.println("글 등록 기능 처리");
		} else if(path.equals("/updateBoard.do")) {
			System.out.println("글 수정 기능 처리");
		} else if(path.equals("/deleteBoard.do")) {
			System.out.println("글 삭제 기능 처리");
		} else if(path.equals("/getBoard.do")) {
			System.out.println("글 상세 조회 기능 처리");
		} else if(path.equals("/getBoardList.do")) {
			System.out.println("글 목록 검색 기능 처리");
		} else {
			System.out.println("잘못된 URL 경로네요. 대소문자 확인하세요.");
		}
	}

}













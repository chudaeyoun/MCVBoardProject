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
    	System.out.println("===> DispatcherServlet ����");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. ����� ��û path ������ �����Ѵ�. 
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println(path);
		
		// 2. ����� path�� �ش��ϴ� �������� �б�ó���Ѵ�. 
		if(path.equals("/login.do")) {
			System.out.println("�α��� ��� ó��");
			
			// 1. ����� �Է�����(id, password) ����
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			// 2. DB ���� ó��
			UserVO vo = new UserVO();
			vo.setId(id);
			vo.setPassword(password);
			
			UserDAO userDAO = new UserDAO();
			UserVO user = userDAO.getUser(vo);
			 
			// 3. ȭ�� �׺���̼�
			if(user != null) {
				HttpSession session = request.getSession();
				// �α��� �������� �� ���ǿ� ���������� �����Ѵ�. 
				session.setMaxInactiveInterval(60 * 60 * 1);
				session.setAttribute("user", user);
				response.sendRedirect("getBoardList.jsp");
			} else {
				response.sendRedirect("login.html");
			}
			
			
		} else if(path.equals("/logout.do")) {
			System.out.println("�α׾ƿ� ��� ó��");
		} else if(path.equals("/insertBoard.do")) {
			System.out.println("�� ��� ��� ó��");
		} else if(path.equals("/updateBoard.do")) {
			System.out.println("�� ���� ��� ó��");
		} else if(path.equals("/deleteBoard.do")) {
			System.out.println("�� ���� ��� ó��");
		} else if(path.equals("/getBoard.do")) {
			System.out.println("�� �� ��ȸ ��� ó��");
		} else if(path.equals("/getBoardList.do")) {
			System.out.println("�� ��� �˻� ��� ó��");
		} else {
			System.out.println("�߸��� URL ��γ׿�. ��ҹ��� Ȯ���ϼ���.");
		}
	}

}













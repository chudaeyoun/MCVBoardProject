package com.multicampus.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.multicampus.biz.board.BoardDAO;
import com.multicampus.biz.board.BoardVO;
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
				response.sendRedirect("getBoardList.do");
			} else {
				response.sendRedirect("login.html");
			}			
		} else if(path.equals("/logout.do")) {
			System.out.println("�α׾ƿ� ��� ó��");
			
			// �α׾ƿ� ��û�� �������� ���ε� ������ ���� �����Ѵ�.
			HttpSession session = request.getSession();
			session.invalidate();

			// ���� ȭ������ �̵��Ѵ�.
			response.sendRedirect("login.html");
			
		} else if(path.equals("/insertBoard.do")) {
			System.out.println("�� ��� ��� ó��");
			
			// 1. ����� �Է�����(title, writer, content) ����
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			// 2. DB ���� ó��
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setContent(content);
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.insertBoard(vo);
			
			// 3. ȭ�� �׺���̼�
			response.sendRedirect("getBoardList.do");
			
		} else if(path.equals("/updateBoard.do")) {
			System.out.println("�� ���� ��� ó��");
			
			// 1. ����� �Է�����(title, seq, content) ����
			String title = request.getParameter("title");
			String seq = request.getParameter("seq");
			String content = request.getParameter("content");
			
			// 2. DB ���� ó��
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setSeq(Integer.parseInt(seq));
			vo.setContent(content);
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.updateBoard(vo);
			
			// 3. ȭ�� �׺���̼�
			response.sendRedirect("getBoardList.do");
			
		} else if(path.equals("/deleteBoard.do")) {
			System.out.println("�� ���� ��� ó��");
			
			// 1. ����� �Է�����(seq) ����
			String seq = request.getParameter("seq");
			
			// 2. DB ���� ó��
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.deleteBoard(vo);
			
			// 3. ȭ�� �׺���̼�
			response.sendRedirect("getBoardList.do");
			
			
		} else if(path.equals("/getBoard.do")) {
			System.out.println("�� �� ��ȸ ��� ó��");
			
			// 1. ����� �Է����� ����
			String seq = request.getParameter("seq");
			
			// 2. DB ���� ó��
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			
			BoardDAO boardDAO = new BoardDAO();
			BoardVO board = boardDAO.getBoard(vo);
			
			// 3. �˻� ����� ���ǿ� ����ϰ� �� ��� ȭ��(getBoardList.jsp)���� �̵��Ѵ�.
			HttpSession session = request.getSession();
			session.setAttribute("board", board);
			response.sendRedirect("getBoardList.jsp");		
			
		} else if(path.equals("/getBoardList.do")) {
			System.out.println("�� ��� �˻� ��� ó��");
			
			// 0. ���� üũ			
			HttpSession session = request.getSession();
			UserVO user = (UserVO) session.getAttribute("user");
			if(user == null) {
				response.sendRedirect("login.html");
			} else {

				// 1. ����� �Է����� ����(�˻� ����� ���߿�...)
				// 2. DB ���� ó��
				BoardDAO boardDAO = new BoardDAO();
				List<BoardVO> boardList = boardDAO.getBoardList();
			
				// 3. �˻� ����� ���ǿ� ����ϰ� �� ��� ȭ��(getBoardList.jsp)���� �̵��Ѵ�.
				session.setAttribute("boardList", boardList);
				response.sendRedirect("getBoardList.jsp");		
			}
		} else {
			System.out.println("�߸��� URL ��γ׿�. ��ҹ��� Ȯ���ϼ���.");
		}
	}

}













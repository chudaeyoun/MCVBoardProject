<%@page contentType="text/html; charset=EUC-KR"%>

<%
	// �α׾ƿ� ��û�� �������� ���ε� ������ ���� �����Ѵ�.
	session.invalidate();

	// ���� ȭ������ �̵��Ѵ�.
	response.sendRedirect("login.html");
%>
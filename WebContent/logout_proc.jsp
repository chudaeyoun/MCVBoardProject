<%@page contentType="text/html; charset=EUC-KR"%>

<%
	// 로그아웃 요청한 브라우저와 매핑된 세션을 강제 종료한다.
	session.invalidate();

	// 메인 화면으로 이동한다.
	response.sendRedirect("login.html");
%>
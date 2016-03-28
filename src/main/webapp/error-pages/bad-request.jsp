<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My kite</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/webjars/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
	<jsp:include page="../header.jsp">
		<jsp:param value="yes" name="hideUserInfo"/>
	</jsp:include>

	<div class="container httpoError">
		<h1>Bad request</h1>
		<p>You have not given your consent for the required attributes.</p>
		<p>
			<a href="${pageContext.request.contextPath}/">Go back to the main
				page</a>
		</p>
	</div>
</body>
</html>

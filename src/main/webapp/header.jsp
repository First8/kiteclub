<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top-menu">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/">
				<img alt="Brand" class="brand-image" src="${pageContext.request.contextPath}/images/logo-nav.png" />
			</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="top-menu">
			<ul class="nav navbar-nav">
				<li class=""><a href="${pageContext.request.contextPath}/">Home</a></li>
				<li role="separator" class="divider"></li>
				<% if (request.getRemoteUser() != null && request.isUserInRole("Admin")) {  %>
					<li class=""><a href="${pageContext.request.contextPath}/admin.jsp">Admin</a></li>
				<% } %>
			</ul>
			<% if (request.getRemoteUser() != null) {  %>
				<ul class="nav navbar-nav navbar-right">
					<% if (!"yes".equalsIgnoreCase(request.getParameter("hideUserInfo"))) {  %>
						<!-- TODO: add user info -->
						<li><a href="{{sessionInfo.accountUrl}}" target="_blank">Account</a></li>
						<li role="separator" class="divider"></li>
					<% } %>
					<li><a href="${pageContext.request.contextPath}/rest/user/logout">Logout</a></li>
				</ul>
			<% } %>
		</div><!-- /.navbar-collapse -->
	</div><!--/.container-fluid -->
</nav>

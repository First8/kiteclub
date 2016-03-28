<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html ng-app="kiteclubMainApp">
<head>
<title>Kiteclub</title>
<link rel="stylesheet" type="text/css"
	href="./webjars/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="./webjars/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="./webjars/angularjs/1.4.6/angular.min.js"></script>
<script>
      var kiteclubMainApp = angular.module('kiteclubMainApp', []);
      kiteclubMainApp.controller('KiteclubMainCtrl', function ($scope, $http){
        // TODO: there is a better AngularJS way to do this...
        $http.get('./rest/user').success(function(data) {
            console.log(data);
            $scope.sessionInfo = data;
        });
      });
    </script>
</head>
<body ng-controller="KiteclubMainCtrl">
	<jsp:include page="header.jsp"/>
	<div class="container">
		<div class="box menu text-left">
			<h2>Your Kite-club!</h2>
			<p>Welcome to the Kite-club.</p>
			<ul>
				<li>Access <a href="${pageContext.request.contextPath}/my-kites.jsp">your kites</a></li>
				<li><a href="${pageContext.request.contextPath}/admin.jsp">Admin access</a></li>
			</ul>
		</div>
	</div>
</body>
</html>

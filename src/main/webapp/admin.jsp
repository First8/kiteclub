<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html ng-app="kiteAdminApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin page</title>
<link rel="stylesheet" type="text/css"
	href="./webjars/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="./webjars/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="./webjars/angularjs/1.4.6/angular.min.js"></script>
<script>
      var kiteAdminApp = angular.module('kiteAdminApp', []);
      kiteAdminApp.controller('KiteCtrl', function ($scope, $http){
        $http.get('./rest/kites').success(function(data) {
          $scope.kites = data;
        });
        // TODO: there is a better AngularJS way to do this...
        $http.get('./rest/user').success(function(data) {
            console.log(data);
            $scope.sessionInfo = data;
        });
      });
    </script>
</head>
<body ng-controller="KiteCtrl">
	<jsp:include page="header.jsp" />

	<div class="container">
		<div class="box admin">
			<p>All kites in the club</p>
			<p>
				Search:<input ng-model="query" type="text" />
			</p>
			<table class="table-striped quadcopters">
				<tr>
					<th>#</th>
					<th>Name</th>
					<th>Picture</th>
					<th>Commands</th>
				</tr>
				<tr ng-repeat="kite in kites | filter:query">
					<td>{{kite.id}}</td>
					<td>{{kite.name}}</td>
					<td><img class="quadcopter" ng-src="images/quadcopters/{{kite.image}}"></td>
					<td>
						<div class="form-group">
							<button type="submit" class="btn">Register payment</button>
							<button type="submit" class="btn">Rojeer</button>
						</div>
					</td>
				</tr>
			</table>
			<p>
				<a href="rest/kites">All kites raw</a>
			</p>
		</div>
	</div>
</body>
</html>
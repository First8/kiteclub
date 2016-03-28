<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html ng-app="myKiteApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My kite</title>
<link rel="stylesheet" type="text/css"
	href="./webjars/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="./webjars/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="./webjars/angularjs/1.4.6/angular.min.js"></script>
<script>
      var myKiteApp = angular.module('myKiteApp', []);
      myKiteApp.controller('MyKiteCtrl', function ($scope, $http){
        $http.get('./rest/kites/mine').success(function(data) {
          $scope.myKites = data;
        });
        // TODO: there is a better AngularJS way to do this...
        $http.get('./rest/user').success(function(data) {
            console.log(data);
            $scope.sessionInfo = data;
        });
      });
    </script>
</head>
<body ng-controller="MyKiteCtrl">
	<jsp:include page="header.jsp" />

	<div class="container">
		<div class="box myKite">
			<p>Your kites</p>
			<p>
				Search:<input ng-model="query" type="text" />
			</p>
			<table class="table-striped table-bordered quadcopters">
				<tr>
					<th>#</th>
					<th>Name</th>
					<th>Picture</th>
					<th>Commands</th>
				</tr>
				<tr ng-repeat="kite in myKites | filter:query">
					<td class="id">{{kite.id}}</td>
					<td class="name">{{kite.name}}</td>
					<td class="image"><img class="quadcopter" ng-src="images/quadcopters/{{kite.image}}"></td>
					<td class="actions">
						<div class="form-group">
							<button type="submit" class="btn">Remove</button>
							<button type="submit" class="btn">Upload image</button>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					</td>
					<td>
						<div class="form-group">
							<button type="submit" class="btn">Add</button>
						</div>
					</td>
				</tr>
			</table>
			<p>
				<a href="rest/kites/mine">Your kites raw</a>
			</p>
		</div>
	</div>
</body>
</html>
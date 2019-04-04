var app = angular.module("app", ['ngResource', 'ngRoute']);

app.controller("AppController", function($scope, $http) {
	$scope.info = "Execute http get request";

	// Get from {service-name/target-port}:
	$http.get('http://springboot-app-service:8080').then(successCallback, errorCallback);

	// TODO : get a json list and display it the container. See ng-repeat.
	function successCallback(response){
    	console.log(response);
    	var name = response.data.player.name;
    	var country = response.data.player.countryName;
    	var rank = response.data.player.rank.nr;
    	
    	$scope.player = name;
    	$scope.country = country;
    	$scope.rank = rank;
	}

	function errorCallback(error){
    	console.log(error);
	}
});
var app = angular.module("app", ['ngResource', 'ngRoute']);

app.controller("AppController", function($scope, $http) {
	$scope.info = "insert info here";

	$http.get('http://api.bf4stats.com/api/playerInfo?plat=pc&name=1ApRiL&output=json')
	.then(successCallback, errorCallback);

	function successCallback(response){
    	console.log(response);
    	$scope.player = response.data.player.name;
	}

	function errorCallback(error){
    	console.log(error);
	}
});

app.controller('AppCtrl', function($scope) {
	$scope.message = "The app routing is working!";
});

app.controller('UserCtrl', function($scope) {
 	$scope.message = "The app routing is still working!";
});
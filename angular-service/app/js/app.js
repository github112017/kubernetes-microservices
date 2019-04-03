var app = angular.module("app", ['ngResource', 'ngRoute']);

app.controller("AppController", function($scope, $http) {
	$scope.info = "Execute http request";

	$http.get('http://api.bf4stats.com/api/playerInfo?plat=pc&name=1ApRiL&output=json')
	.then(successCallback, errorCallback);

	// TODO : get a json list and display it the container.
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
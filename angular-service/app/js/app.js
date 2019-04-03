var app = angular.module("app", ['ngResource', 'ngRoute']);

app.controller("AppController", function($scope, $http) {
	$scope.info = "insert info here";

	$http.get('http://api.bf4stats.com/api/playerInfo?plat=pc&name=1ApRiL&output=json')
	.then(successCallback, errorCallback);

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
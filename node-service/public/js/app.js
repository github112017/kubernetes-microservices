var app = angular.module("app", ['ngResource', 'ngRoute']);

/* Enable CORS request 
app.config(['$httpProvider', function($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
}])
*/


app.controller("AppController", function($scope, $http) {
    $scope.info = "Executes http request"

    var base_url = 'minikube:30003/api/load'
    var load_url = '/api/load'

    $http.get(base_url + load_url)
        .then(successCallback, errorCallback)


	function successCallback(response){
    	console.log(response);
    	//var name = response.data.player.name;
    	//var country = response.data.player.countryName;
    	//var rank = response.data.player.rank.nr;
    	
    	//$scope.player = name;
    	//$scope.country = country;
    	//$scope.rank = rank;
	}

	function errorCallback(error){
    	console.log(error);
	}
});
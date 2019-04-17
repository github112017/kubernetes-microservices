app.controller("AppController", function($scope, $http, apiService) {
  var player = 'John'

  apiService.load_player(player)
  .then(function(response) {
    console.log("LOAD_SUCEESS : " + response)
  })

  apiService.get_player(player)
  .then(successCallback, errorCallback)
    
  function successCallback(response) {
    console.log("SUCCESS : " + response)

    $scope.player = response.name
    $scope.game = response.game
    $scope.country = response.country
    $scope.rank = response.rank
  }
  
  function errorCallback(error){
    console.log("ERROR : " + error)
  }
});
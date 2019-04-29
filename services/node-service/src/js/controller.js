app.controller("AppController", function($scope, $http, apiService) {
  var player = 'John'

  apiService.load_player(player)
    .then(function(response) {
    console.log("loaded player : " + response)
  })

  /* Wait for PUT to finish 
  var now = new Date().getTime()
  var millisecondsToWait = 4000
  while(new Date().getTime() < now + millisecondsToWait) {
    // wait
  }*/

  apiService.get_player(player)
    .then(success, error)
    
  function success(response) {
    var player = angular.fromJson(response.data.body)    

    $scope.name = player.name
    $scope.game = player.game
    $scope.country = player.country
    $scope.rank = player.rank
  }
  
  function error(error){
    console.log("ERROR : " + error)
  }
});
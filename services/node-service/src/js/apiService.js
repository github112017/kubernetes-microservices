app.factory("apiService", function($http, $q) {
  var base_url = 'http://minikube:30003'

  return {
    load_player: function(name){
      var api_load = '/api/player/'
      console.log("sending $http.get to : " + base_url+api_load+name)

      return $http({
        method: 'PUT',
        url: base_url + api_load + name,
        headers: {
          'Content-Type': 'text/plain'
        }
      })
    },

    get_player: function(name){
      var api_get = '/api/player/'
      console.log("sending $http.get to : " + base_url+api_get+name)

      return $http({
        method: 'GET',
        url: base_url + api_get + name,
        headers: {
          'Content-Type': 'application/json'
        }
      })
    }
  }
})
app.factory("apiService", function($http, $q) {
  /* ExpressJS server url, using nodeport 30003 */
  var baseUrl = 'http://minikube:30003'
  var playerApi = '/api/player/'


  return {
    load_player: function(name) {
      return $http({
        method: 'PUT',
        url: baseUrl+playerApi+name,
        headers: {
          'Content-Type': 'text/plain'
        }
      })
    },

    get_player: function(name) {
      return $http({
        method: 'GET',
        url: baseUrl+playerApi+name,
        headers: {
          'Content-Type': 'application/json'
        }
      })
    }
  }
})
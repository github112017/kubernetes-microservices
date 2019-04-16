var app = angular.module("app", ['ngResource', 'ngRoute']);

app.config(function($routeProvider, $locationProvider, $httpProvider) {
  
  /* Enable CORS */ 
  $httpProvider.defaults.useXDomain = true
  delete $httpProvider.defaults.headers.common['X-Requested-With']

  // TODO : add routes
  
})
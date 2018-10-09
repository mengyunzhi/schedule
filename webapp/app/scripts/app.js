'use strict';

/**
 * @ngdoc overview
 * @name scheduleApp
 * @description
 * # scheduleApp
 *
 * Main module of the application.
 */
angular
  .module('scheduleApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.router'
  ])
  .config(function($provide) {
    $provide
    .constant('routers', [
        {
          name: 'main',
          url: '/',
          templateUrl: 'views/main.html',
          controller: 'MainCtrl'
        },
        {
          name: 'about',
          url: '/about',
          templateUrl: 'views/about.html',
        }
      ]);
  })
  .config(function($stateProvider, $urlRouterProvider, routers) {
    angular.forEach(routers, function(router){
      $stateProvider
      .state(router);
    });
    $urlRouterProvider.otherwise('/');
  });

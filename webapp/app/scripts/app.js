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
          controller: 'MainCtrl',
          data: { title: '首页', show: true }
        },
        {
          name: 'student',
          url: '/student',
          templateUrl: 'views/Student/index.html',
          controller: 'StudentIndexCtrl',
          data: { title: '学生管理', show: true }
        },
        {
          name: 'course',
          url: '/course',
          templateUrl: 'views/Course/index.html',
          controller: 'CourseIndexCtrl',
          data: { title: '课程管理', show: true }
        },
        // course/add
        // @author chenjie
        {
          name: 'course.add', 
          url: '/add', 
          templateUrl: 'views/course/add.html',
          controller: 'CourseAddCtrl',
          data: {
              title: '增加',
              show: false
          }
        },
        // course/edit
        // @author chenjie
        {
          name: 'course.edit', 
          url: '/edit/:id', 
          templateUrl: 'views/course/edit.html',
          controller: 'CourseEditCtrl',
          data: {
              title: '编辑',
              show: false
          }
        },
        {
          name: 'semester',
          url: '/semester',
          templateUrl: 'views/Semester/index.html',
          controller: 'SemesterIndexCtrl',
          data: { title: '学期管理', show: true }
        },
        {
          name: 'contribution',
          url: '/contribution',
          templateUrl: 'views/Contribution/index.html',
          controller: 'ContributionIndexCtrl',
          data: { title: '贡献值管理', show: true }
        }
      ]);
  })
  .config(function($stateProvider, $urlRouterProvider, $provide, $httpProvider, routers) {
    angular.forEach(routers, function(router){
      $stateProvider
      .state(router);
    });
    $urlRouterProvider.otherwise('/');

    $provide.factory('myHttpInterceptor', function() {
            return {
                //拦截请求信息
                'request': function(config) {
                    //如果以html结尾，那么就不进行URL改写，否则就进行改写
                    var suffix = config.url.split('.').pop();
                    if (suffix !== 'html') {
                        config.url = 'http://127.0.0.1:8080' + config.url;
                    }

                    return config;
                },
            };
        });

        $httpProvider.interceptors.push('myHttpInterceptor');
  });

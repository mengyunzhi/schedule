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
          name: 'schedule',
          url: '/',
          templateUrl: 'views/schedule/index.html',
          controller: 'ScheduleIndexCtrl',
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
          name: 'student.add',
          url: '/add',
          templateUrl: 'views/Student/add.html',
          controller: 'StudentAddCtrl',
          data: { title: '增加', show: false }
        },
        {
          name: 'student.edit',
          url: '/edit/:id',
          templateUrl: 'views/Student/edit.html',
          controller: 'StudentEditCtrl',
          data: { title: '编辑', show: false }
        },
        {
          name: 'student.select',
          url: '/select/:id',
          templateUrl: 'views/Student/selectCourse.html',
          controller: 'StudentSelectcourseCtrl',
          data: { title: '选课', show: false }
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
          templateUrl: 'views/Course/add.html',
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
          templateUrl: 'views/Course/edit.html',
          controller: 'CourseEditCtrl',
          data: {
              title: '编辑',
              show: false
          }
        },
        // course/selectSchedule
        // @author chenjie
        {
          name: 'course.selectSchedule', 
          url: '/selectSchedule/:id', 
          templateUrl: 'views/Course/selectSchedule.html',
          controller: 'CourseSelectscheduleCtrl',
          data: {
              title: '选择时间',
              show: false
          }
        },
        // course/selectWeekOrder
        // @author chenjie
        {
          name: 'course.selectWeekOrder', 
          url: '/selectWeekOrder?courseId&semesterId&week&node', 
          templateUrl: 'views/Course/selectWeekOrder.html',
          controller: 'CourseSelectweekorderCtrl',
          data: {
              title: '选择周次',
              show: false
          },
        },
        {
          name: 'semester',
          url: '/semester',
          templateUrl: 'views/Semester/index.html',
          controller: 'SemesterIndexCtrl',
          data: { title: '学期管理', show: true }
        },
        {
          name: 'semester.add',
          url: '/add',
          templateUrl: 'views/Semester/add.html',
          controller: 'SemesterAddCtrl',
          data: {title: 'semesterAdd', show: false}
        },
        {
          name: 'semester.edit',
          url: '/edit/:id',
          templateUrl: 'views/Semester/edit.html',
          controller: 'SemesterEditCtrl',
          data: {title: 'semesterEdit', show: false}
        },
        {
          name: 'contribution',
          url: '/contribution',
          templateUrl: 'views/Contribution/index.html',
          controller: 'ContributionIndexCtrl',
          data: { title: '贡献值管理', show: true }
        },
        {
            name: 'contribution.information',
            url: '/information/:id',
            templateUrl: 'views/Contribution/information.html',
            controller: 'ContributionInformationCtrl',
            data: {
                title: '详细信息',
                show: false
            }
        },
        {
            name: 'contribution.edit',
            url: '/edit/:id',
            templateUrl: 'views/Contribution/edit.html',
            controller: 'ContributionEditCtrl',
            data: {
                title: '编辑信息',
                show: false
            }
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

'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:CourseEditCtrl
 * @description
 * # CourseEditCtrl
 * Controller of the scheduleApp
 * @author chenjie
 */
angular.module('scheduleApp')
  .controller('CourseEditCtrl', function ($scope, $http, $stateParams, $state) {
    var self = this;
    self.init = function() {
      // 获取当前这个课程
      var id = $stateParams.id;
      var url = '/Course/' + id;
      $http.get(url)
        .then(function success(response) {
          // 把获取的课程，绑定到V层
          $scope.data = response.data;
        }, function error(response) {
          console.log('请求失败 ' + url, response);
        });
    };

    // 提交数据
    self.submit = function() {
      var id = $stateParams.id;
      var url = '/Course/' + id;
      $http.put(url, $scope.data)
        .then(function success(response) {
          /// 把获取的课程，绑定到V层
          $state.transitionTo('course', {}, { reload: true });
        }, function error(response) {
          console.log('请求失败 ' + url, response);
        });
    };


    self.init();
    $scope.submit = self.submit;
  });

'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the scheduleApp
 * 登录界面控制器
 */
angular.module('scheduleApp')
  .controller('LoginCtrl', function ($scope, user, $location) {
  
      var self = this;
      
      // 初始化
      self.init = function () {
          $scope.user = {
              username: '',
              password: '',
          };
          
          //如果出错，显示错误
          $scope.showErrorBox = false;
      };
      
      self.submit = function () {
          user.login($scope.user)
              .then(function success() {
                  $scope.showErrorBox = false;
                  user.getCurrentLoginUser(function () {
                      $location.url('/main');
                  }, function error(response) {
                      $scope.showErrorBox = true;
                      console.error('error', response);
                  });
              });
      };
    
      $scope.submit = self.submit;
      self.init();
  });

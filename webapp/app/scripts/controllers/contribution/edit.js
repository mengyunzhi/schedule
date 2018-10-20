'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:ContributionEditCtrl
 * @description
 * # ContributionEditCtrl
 * Controller of the scheduleApp
 * 编辑某人贡献值的控制器
 */
angular.module('scheduleApp')
  .controller('ContributionEditCtrl', function ($scope, $http, contribution, $stateParams, studentService) {
      var self = this;
      var id = $stateParams.id;  // 从路由中得到的id
      
      self.init = function () {
          $scope.data = {
              operate: true,        // 执行的操作 true增加,false减少
              value: 0.00,          // 改变的贡献值的大小
              remarks: '',          // 贡献值附带的备注
              time: 0,              // 修改贡献值的时间
              title: '主动修改',     // 操作名字
              pullRequest: '无'   // 附带的pullRequest
          };
          
          //通过id得到该学生的信息
          studentService.getStudentByCourse(id, function (data) {
              $scope.studentName = data.name;
          });
      };
      
      self.submit = function () {
          contribution.modifyContribution($scope.data, id);
      };
      
      self.init();
      $scope.submit = self.submit;
  });

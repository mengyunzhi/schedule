'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:ContributionInformationCtrl
 * @description
 * # ContributionInformationCtrl
 * Controller of the scheduleApp
 * 得到某个学生的贡献值的详细信息
 */
angular.module('scheduleApp')
  .controller('ContributionInformationCtrl', function ($scope, $http, contribution, $stateParams) {
      var self = this;
      var id = $stateParams.id;   //从路由获取到的id
      
      self.init = function () {
          contribution.getDetailedInformation(function (data){
              $scope.contributionInformations = data.data;
          }, id);
      };
      
      self.init();
  });

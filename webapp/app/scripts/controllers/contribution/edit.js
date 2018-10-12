'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:ContributionEditCtrl
 * @description
 * # ContributionEditCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
  .controller('ContributionEditCtrl', function ($scope, $http, contribution) {
      var self = this;
      
      self.init = function () {
    
          $scope.data = {
              operate: true,
              value: 0.00,
              remarks: '',
              time: 0,
              title: '主动修改',
              pullRequest: 'null'
          };
          
         
      };
      
      self.submit = function (data, id) {
          contribution.modifyContribution(data, id);
      };
      
      
      self.init();
      $scope.submit = self.submit;
  });

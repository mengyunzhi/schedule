'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:ContributionInformationCtrl
 * @description
 * # ContributionInformationCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
  .controller('ContributionInformationCtrl', function ($scope, $http, contribution) {
      var self = this;
      self.init = function () {

          //通过getById得到学生
          
          contribution.getDetailedInformation(function (data) {
              //莫名其妙的错误，以后研究
              $scope.contributionInformations = data.data;

              console.log($scope.contributionInformations);
          });
      };
      
      self.init();
  });

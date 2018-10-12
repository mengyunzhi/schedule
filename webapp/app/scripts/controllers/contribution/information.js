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
          contribution.getDetailedInformation(function (data) {
              //莫名其妙的错误，以后研究
              // $scope.contributionInformations = data.contributionList;
              
          });
      };
      
      self.init();
  });

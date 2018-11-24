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
    .controller('ContributionInformationCtrl', function ($scope, $http, contribution, $stateParams, studentService) {
        var self = this;
        var id = $stateParams.id;   //从路由获取到的id

        self.init = function () {
            $scope.pageParams = {page: 0, size: 2};
            self.reload();
            contribution.getDetailedInformation(function (data) {
                $scope.contributionInformations = data.data;
            }, id);

            //通过id得到该学生的信息
            studentService.getStudentByCourse(id, function (data) {
                $scope.studentName = data.name;
            });
        };

        self.reload = function() {
            contribution.getPage($scope.pageParams, function(data) {
                $scope.page = data;
            });
        };
        /**
         * 根据当前页数获取分页
         * @param page
         */
        self.reloadByPage = function(page) {
            $scope.pageParams.page = page;
            self.reload();
        };

        self.init();
        $scope.reloadByPage = self.reloadByPage;

    });

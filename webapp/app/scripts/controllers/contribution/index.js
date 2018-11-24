'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:ContributionIndexCtrl
 * @description
 * # ContributionIndexCtrl
 * Controller of the scheduleApp
 * contribution 主页的控制器
 */
angular.module('scheduleApp')
    .controller('ContributionIndexCtrl', function ($http, $scope, contribution, studentService) {
        var self = this;
        
        //本月累增
        var monthIncrease = function (students) {
            contribution.getWeekIncrease(students);
        };
        
        //本周内增
        var weekIncrease = function (students) {
            contribution.getMonthIncrease(students);
        };
        
        //初始化
        self.init = function () {
            //得到所有的学生数据
            studentService.getAllStudent(function (data) {

                $scope.allStudents = data;
                //得到本月累增
                monthIncrease($scope.allStudents);
                
                //得到本周累增
                weekIncrease($scope.allStudents);
                
            });
        };
        
        
        self.init();
        
    });

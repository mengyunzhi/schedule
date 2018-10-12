'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:ContributionIndexCtrl
 * @description
 * # ContributionIndexCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
    .controller('ContributionIndexCtrl', function ($http, $scope, contribution) {
        var self = this;
    
        //本月累增
        var monthIncrease = function () {
            contribution.getWeekIncrease();
        };
    
        //本周内增
        var weekIncrease = function () {
            contribution.getMonthIncrease();
        };
        
        //初始化
        self.init = function () {
            
            //得到所有的学生数据
            
            monthIncrease();
            weekIncrease();
        };
        
      
        
        self.init();
        
    });

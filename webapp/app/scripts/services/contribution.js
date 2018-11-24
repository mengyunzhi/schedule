'use strict';

/**
 * @ngdoc service
 * @name scheduleApp.contribution
 * @description
 * # contribution
 * Service in the scheduleApp.
 * contribution的 控制器
 */
angular.module('scheduleApp')
    .service('contribution', function ($http, $state) {
        var self = this;
        
        //直接修改贡献值
        self.modifyContribution = function (data, id) {
            
            var url = '/Contribution/modify/' + id;
            data.time = new Date().valueOf();
            if (data.operate === 'false') {
                data.value = -data.value;
            }
            //发起请求
            $http.put(url, data)
                .then(function success() {
                    $state.transitionTo('contribution', {}, {reload: true});
                    console.log('success');
                }, function error() {
                    console.log('error');
                });
        };
        
        //取得该对象贡献值获取的详细信息
        self.getDetailedInformation = function (callback, id) {
            // 如果传入的有id，就使用传入的id，否则用路由的Id;
            
            var url = '/Contribution/information/' + id;
            $http.get(url)
                .then(function success(response) {
                    //用回调函数绑定数据到$scope
                    if (callback) {
                        callback(response);
                    }
                    console.log('success');
                }, function error() {
                    console.log('error');
                });
        };
        
        //取得本月类增的数据
        self.getMonthIncrease = function (students) {
            //得到当前的年月
            var date = new Date();
            var currentYear = date.getFullYear();    // 年
            var currentMonth = date.getMonth();      // 月
            angular.forEach(students, function (student) {
                student.monthIncrease = 0;
                angular.forEach(student.contributionList, function (contribution) {
                    
                    // 当前的年月
                    var contributionTime = new Date();
                    
                    var contributionYear = contributionTime.getFullYear();   // 年
                    var contributionMonth = contributionTime.getMonth();    // 月
                   
                    // 判断是否在本年本月
                    if (currentYear === contributionYear && currentMonth === contributionMonth) {
                        student.monthIncrease += contribution.value;
                    }
                });
            });
        };
        
        //取得本周类增的数据
        self.getWeekIncrease = function (students) {
            //得到本周一，本周日
            var date = new Date();
            
            var begin = date.setDate(date.getDate() - date.getDay() + 1);  // 周一
            var end = date.setDate(date.getDate() + 6);                    // 周日
            angular.forEach(students, function (student) {
                student.weekIncrease = 0;

                angular.forEach(student.contributionList, function (contribution) {

                    if (contribution.time > begin && contribution.time < end) {
                        student.weekIncrease += contribution.value;
                    }
                });
            });
        };
    });

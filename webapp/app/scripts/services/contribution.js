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
                    self.alertWindow('修改贡献值失败');
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
                    self.alertWindow('取得对象贡献值失败');
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
            var time = new Date();
            var begin; // 周一
            var end;   // 周日
            var day = time.getDay();
            var date = time.getDate();

            // 计算周一和周日的日期
            if (day === 0) {
                begin = date - 6;
                end = date;
            } else if (day === 1) {
                begin = date;
                end = begin + 6;
            } else {
                begin = date - day + 1;
                end = begin + 6;
            }

            angular.forEach(students, function (student) {
                student.weekIncrease = 0;

                angular.forEach(student.contributionList, function (contribution) {
                    var contributionDate = new Date(contribution.time).getDate();
                    if (contributionDate >= begin && contributionDate <= end) {
                        student.weekIncrease += contribution.value;
                    }
                });
            });

        };

        // 弹窗， 写一个方法，方便重写
        self.alertWindow = function (msg) {
            alert(msg);
        };

        self.getPage = function (pageParams, callback) {

            var pageUrl = '/Contribution/page';
            $http.get(pageUrl, { params: pageParams})
                .then(function (response) {
                    callback(response.data);
                }, function () {
                    self.alertWindow('获取数据失败');
                    console.log('failed to get contributions page');
                });
        };
    });

'use strict';

/**
 * @ngdoc service
 * @name scheduleApp.contribution
 * @description
 * # contribution
 * Service in the scheduleApp.
 */
angular.module('scheduleApp')
    .service('contribution', function ($http, $state, $stateParams) {
        var self = this;
        var id = $stateParams.id;
        
        //直接修改贡献值
        self.modifyContribution = function (data) {
            var url = '/Contribution/modify/' + id;
            data.time = new Date().valueOf();
            console.log(data.time);
            if (data.operate === 'false') {
                data.value = -data.value;
            }
            //发起请求
            $http.put(url, data)
                .then(function success() {
                    $state.transitionTo('contribution', {}, {reload: true});
                    console.log('success');
                }, function error() {
                    console.log(data);
                    console.log('error');
                });
        };
        
        //取得该对象贡献值获取的详细信息
        self.getDetailedInformation = function (callback) {
            var url = '/Contribution/information/' + id;
            $http.get(url)
                .then(function success(response) {
                    //用回调函数绑定数据到$scope
                    if (callback) {
                        callback(response);
                    }
                    $state.transitionTo('contribution', {}, {reload: true});
                    console.log('success');
                }, function error() {
                    console.log('error');
                });
        };
        
        self.getAllContribution = function (callback) {
            var url = '/student/';
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
        self.getMonthIncrease = function (callback) {
          var url = '/Contribution/monthIncrease/' + id;
          $http.get(url)
              .then(function success(response) {
                  if (callback) {
                      callback(response);
                  }
                  console.log('success');
              }, function error() {
                  console.log(' monthIncrease error');
              });
          
        };
        
        //取得本周类增的数据
        self.getWeekIncrease = function (callback) {
            var url = '/Contribution/weekIncrease/' + id;
            $http.get(url)
                .then(function success(response) {
                    if (callback) {
                        callback(response);
                    }
                    console.log('success');
                }, function error() {
                    console.log(' weekIncrease error');
                });
        };
    });

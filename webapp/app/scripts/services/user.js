'use strict';

/**
 * @ngdoc service
 * @name scheduleApp.user
 * @description
 * # user
 * Service in the scheduleApp.
 * user 服务
 */
angular.module('scheduleApp')
    .service('user', function ($http, $timeout) {
        var self = this;

        // 初始化
        self.init = function () {
            $timeout(function () {
                self.getCurrentLoginUser();
            }, 500);
        };

        // 观察者
        self.observerCallbacks = [];

        // 注册订阅者
        self.registerObserverCallback = function (callback) {
            self.observerCallbacks.push(callback);
        };

        // 出版
        self.notifyObserver = function (CurrentLoginUser) {
            angular.forEach(self.observerCallbacks, function (callback) {
                callback(CurrentLoginUser);
            });
        };

        // 获取当前登录用户
        self.getCurrentLoginUser = function (callback) {
            var url = '/User/me';

            $http.get(url)
                .then(function success(response) {
                    self.notifyObserver(response.data);
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    var user = {};
                    self.notifyObserver(user);
                    self.alertWindow('获取当前登录用户失败');
                    console.log('获取当前登录教师错误', response);
                });
        };

        // 用户登录
        self.login = function (user) {
            var url = '/User/login';
            return $http.post(url, user);
        };

        // 注销
        self.logout = function (callback) {
            var url = '/User/logout';
            $http.post(url)
                .then(function success(response) {
                    self.notifyObserver({});
                    if (callback) {
                        callback(response);
                    }
                }, function error(reason) {
                    self.alertWindow('注销失败');
                    console.error('logout error', reason);
                });
        };

        self.init();

        // 弹窗， 写一个方法，方便重写
        self.alertWindow = function (msg) {
            alert(msg);
        };

    });

'use strict';

/**
 * @ngdoc directive
 * @name scheduleApp.directive:listindex
 * @description
 * # listindex
 */
angular.module('scheduleApp')
    .directive('listIndex', function($state, routers, systemSetting, user, $location) {
        return {
            templateUrl: 'views/directives/listIndex.html',
            restrict: 'E',
            scope: {},

            link: function postLink($scope) {
                var self = this;
                self.init = function() {
                    //初始化
                    $scope.data = {
                        CurrentLoginUser: {
                            id: '',
                            name: ''
                        }
                    };

                    //注册观察者
                    user.registerObserverCallback(function(user) {
                        $scope.data.CurrentLoginUser = user;
                    });

                };

                //导航栏点击选中
                self.isActive = function(state) {
                    //获取当前路由哦
                    var currentState = $state.$current;
                    //存在parent属性
                    if (currentState.parent) {
                        //一直找到跟路由
                        while (currentState.parent.parent !== null) {
                            currentState = currentState.parent;

                        }
                        //判断根路由
                        if (currentState.name === state.name) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                };

                // 注销
                self.logout = function() {
                    user.logout(function() {
                        $location.url('/login');
                    });
                };

                self.init();
                $scope.isActive = self.isActive;
                $scope.routers = routers;
                $scope.logout = self.logout;

            }
        };
    });

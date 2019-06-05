'use strict';

/**
 * @ngdoc service
 * @name scheduleApp.systemSetting
 * @description
 * # systemSetting
 * Service in the scheduleApp.
 * systemSetting 服务
 */
angular.module('scheduleApp')
    .service('systemSetting', function ($http, $timeout) {
        var self = this;
        var baseUrl = '/SystemSetting/';
        
        //获取所有系统设置
        self.getAll = function(callBack) {
            $http.get(baseUrl)
            .then(function(response) {
                var data = response.data;
                if(callBack) {
                    callBack(data);
                }
            }, function() {
                console.log('false get systemSetting');
            });
        };

        //更新系统设置
        self.updateSettings = function(settings, callBack, errorCallBack) {
            $http.put(baseUrl + 'updateSettings',settings)
            .then(function(response) {
                if(callBack) {
                    callBack(response);
                }
            }, function() {
                if(errorCallBack) {
                    errorCallBack();
                }
                console.log('fail to update setting');
            });
        };
        
    });

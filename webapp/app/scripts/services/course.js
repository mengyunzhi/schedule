'use strict';

/**
 * @ngdoc service
 * @name scheduleApp.course
 * @description
 * # course
 * Service in the scheduleApp.
 * @author     chenjie
 */
angular.module('scheduleApp')
    .service('course', function($http) {
        var self = this;

        /**
         * 分页
         * @param params 查询参数 {page:当前页， size: 每页大小}
         * @param callback
         * @author     chenjie
         */
        self.page = function(params, callback) {
            var url = '/Course/page';
            // 使用参数发起get请求
            $http.get(url, { params: params })
                .then(function success(response) {
                    if (callback) { callback(response.data); }
                }, function error(response) {
                    console.log('error', response);
                })
        };

        /**
         * 删除
         * @param 要删除的对象
         * @param callback
         */
        self.delete = function(object, callback) {
            var url = '/Course/' + object.id;
            $http.delete(url)
                .then(function success(response) {
                    if (callback) { callback(); }
                }, function error(response) {
                    console.log('error', response);
                });

        };

        return {
            delete: self.delete,
            page: self.page
        };
    });

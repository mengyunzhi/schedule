'use strict';

/**
 * @ngdoc service
 * @name scheduleApp.studentservice
 * @description
 * # studentservice
 * 学生
 */
angular.module('scheduleApp')
    .service('studentService', function($http) {
        var self = this;

        //获取所有学生
        self.getAllStudent = function(callback) {
            var url = '/student/';
            $http.get(url)
                .then(function success(response) {
                    console.log(response);
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    console.log(response);
                    console.log('error');
                });

        };
        return {
            getAllStudent: self.getAllStudent
        };
    });

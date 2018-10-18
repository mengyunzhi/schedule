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
                    // console.log(response);
                    if (callback) {
                        callback(response.data);
                    }
                }, function error() {
                    // console.log(response);
                    console.log('error');
                });

        };

        //全选（选课）
        self.checkAll = function(status, data) {
            var idArray = [];
            if (status === true) {
                var j = 0;
                for (var i = data.size - 1; i >= 0; i--, j++) {
                    idArray[j] = data.content[i];
                }
            } else {
                idArray.splice(0, idArray.length);
            }
            return idArray;
        };

        self.getStudentByCourse = function(id, callback) {
            var url = '/student/' + id;
            $http.get(url)
                .then(function success(response) {
                    if (callback) { callback(response.data); }
                }, function error(response) {
                    console.log('请求失败 ' + url, response);
                });

        };

        //保存选择的课程
        self.selectCourse = function(id, idArray, callback) {
            var url = '/student/select/' + id;
            $http.put(url, idArray)
                .then(function success() {
                    if (callback) {
                        callback();
                    }
                }, function error() {
                    console.log('error');
                });
        };

        return {
            getAllStudent: self.getAllStudent,
            selectCourse: self.selectCourse,
            checkAll: self.checkAll,
            getStudentByCourse: self.getStudentByCourse
        };
    });

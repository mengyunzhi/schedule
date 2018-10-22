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

        // 获取所有学生
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

        // 获取当前学生的课程
        self.getStudentByCourse = function(id, callback) {
            var url = '/student/' + id;
            $http.get(url)
                .then(function success(response) {
                    if (callback) { callback(response.data); }
                }, function error(response) {
                    console.log('请求失败 ' + url, response);
                });

        };

        // 保存选择的课程
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

        // 删除
        self.delete = function(object, callback) {
            var url = '/student/' + object.id;
            $http.delete(url)
                .then(function succsess(response) {
                    if (callback) { callback(response.data); }
                }, function error() {
                    console.log('删除失败');
                });
        };

        // 按学生名称查询
        self.findByName = function(name, callback) {
            var url = '/student/name/' + name;
            $http.get(url)
                .then(function(response) {
                    if (callback) { callback(response.data); }
                }, function() {
                    console.log('error');
                });
        };

        return {
            getAllStudent: self.getAllStudent,
            selectCourse: self.selectCourse,
            checkAll: self.checkAll,
            delete: self.delete,
            findByName: self.findByName,
            getStudentByCourse: self.getStudentByCourse
        };
    });

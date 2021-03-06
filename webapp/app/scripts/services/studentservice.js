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
                    self.alertWindow('获取学生失败');
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
                    self.alertWindow('获取学生课程失败');
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
                    self.alertWindow('保存课程失败');
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
                    self.alertWindow('删除失败');
                    console.log('删除失败');
                });
        };

        // 按学生名称查询
        self.findByName = function(name, params, callback) {
            var url = '/student/query/' + name;
            $http.get(url, {params: params })
                .then(function(response) {
                    if (callback) { callback(response.data); }
                }, function() {
                    self.alertWindow('查询失败');
                    console.log('error');
                });
        };

        //分页
        self.page = function(params, callback) {
            var url = '/student/page';
            $http.get(url, { params: params })
                .then(function success(response) {
                    if (callback) {
                        callback(response.data);
                    }
                }, function error(response) {
                    self.alertWindow('获取数据失败');
                    console.log('error', response);
                });
        };

        // 弹窗， 写一个方法，方便重写
        self.alertWindow = function (msg) {
            alert(msg);
        };

        return {
            getAllStudent: self.getAllStudent,
            selectCourse: self.selectCourse,
            checkAll: self.checkAll,
            delete: self.delete,
            findByName: self.findByName,
            getStudentByCourse: self.getStudentByCourse,
            page: self.page
        };
    });

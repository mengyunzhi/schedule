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
    .service('courseService', function($http) {
        var self = this;

        self.currentSemester = {};



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
                });
        };

        /**
         * 请求当前这个课程       
         * param      {long} <id> { 当前页面的课程id }
         * return     {course} { 返回请求到的课程 }
         * @author    chenjie 
         */
        self.getCourseById = function(id, callback) {
            var url = '/Course/' + id;
            $http.get(url)
                .then(function success(response) {
                    if (callback) { callback(response.data); }
                }, function error(response) {
                    console.log('请求失败 ' + url, response);
                });

        };

        /**
         * 设置当前这个学期       
         * @author    chenjie 
         */
        self.getCurrentSemester = function(callback) {
            $http.get('/semester/getSemester')
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    console.log('请求失败' + response);
                });
        };


        /**
         * 更新课程       
         * param      {long} <id> { 需要编辑的课程id }
         * param      {object} <data> { 更新后的课程 }
         * paeam      callback
         * @author    chenjie
         */
        self.update = function(id, data, callback) {
            var url = '/Course/' + id;
            $http.put(url, data)
                .then(function success() {
                    if (callback) { callback(); }
                }, function error(response) {
                    console.log('请求失败 ' + url, response);
                });
        };


        /**
         * 新增       
         * param      {object} <data> { 新增的数据 }
         * paeam      callback
         * @author    chenjie
         */
        self.add = function(data, callback) {
            var url = '/Course/';
            $http.post(url, data)
                .then(function success() {
                    if (callback) { callback(); }
                }, function error(response) {
                    console.log('新增课程发生错误' + url, response);
                });
        };

        /**
         * 全选       { 需要页面数据排序为逆序 }
         * param      {bool} <status> { 全选按钮状态 }
         * param      {object} <data> { 前台通过get请求从http请求来的数据 }
         * return     {array} <idArray> { 返回全部数据的id }
         * @author    chenjie
         */
        self.dataIds = function(status, data) {
            var idArray = [];
            if (status === true) {
                var j = 0;
                for (var i = data.size - 1; i >= 0; i--, j++) {
                    idArray[j] = data.content[i].id;
                }
            } else {
                idArray.splice(0, idArray.length);
            }
            return idArray;
        };

        /**
         * 批量删除
         * param      {array} <deleteList> { 要删除的对象数组 }
         * @param     callback
         * @author    chenjie
         */
        self.deleteMultiple = function(deleteList, callback) {
            var url = '/Course/deleteAll';
            console.log(deleteList);

            $http.delete(url, { data: deleteList, headers: { 'Content-type': 'application/json;charset=utf-8' } })
                .then(function success() {
                    if (callback) { callback(); }
                    console.log("deleteSuccesss");
                }, function error(response) {
                    console.log("deleteError", response);
                });
        };

        return {
            delete: self.delete,
            page: self.page,
            deleteMultiple: self.deleteMultiple,
            dataIds: self.dataIds,
            getCourseById: self.getCourseById,
            update: self.update,
            add: self.add,
            getCurrentSemester: self.getCurrentSemester,
            currentSemester: self.currentSemester
        };
    });

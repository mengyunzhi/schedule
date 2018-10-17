'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:StudentSelectcourseCtrl
 * @description
 * # StudentSelectcourseCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
    .controller('StudentSelectcourseCtrl', function($http,$state, $scope, $stateParams, courseService, studentService) {
        var self = this;

        // 初始化
        self.init = function() {
            $scope.params = { page: 0, size: 3 };
            self.load();
            self.selectActive();
        };

        //当前学生已选课程默认选中
        self.selectActive = function() {
            var id = $stateParams.id;
            studentService.getStudentByCourse(id, function(data) {
                //循环所有课程
                angular.forEach($scope.data.content, function(course) {
                    //循环当前学生所选的课程
                    angular.forEach(data.courseList, function(checkedCourse) {
                        //判断当前学生课程与所有课程中相同的课程
                        if (course.id === checkedCourse.id) {
                            //默认选中已选课程
                            course._checked = true;
                        }
                    });
                });
            });
        };

        // 加载数据
        self.load = self.reload = function() {
            courseService.page($scope.params, function(data) {
                $scope.data = data;
                //将所有课程状态初始化为false
                angular.forEach($scope.data.content, function(course) {
                    course._checked = false;
                });
            });
        };

        // 分页时重新加载数据
        self.reloadByPage = function(page) {
            $scope.params.page = page;
            self.reload();
        };

        // 进行每页大小
        self.reloadBySize = function(size) {
            $scope.params.size = size;
            self.load();
        };



        //选课
        self.selectCourse = function() {
            var id = $stateParams.id;
            studentService.selectCourse(id, $scope.array, function() {
                $state.transitionTo('student', {}, {reload: true});
                $scope.array.splice(0, $scope.array.length);
            });
        };

        //全选             
        self.selectAll = function(allStatus) {
            $scope.array = studentService.checkAll(allStatus, $scope.data);
        };
      
        self.init();
        $scope.allStatus = false;
        $scope.selectAll = self.selectAll;
        $scope.array = [];
        $scope.reloadByPage = self.reloadByPage;
        $scope.reloadBySize = self.reloadBySize;
        $scope.selectCourse = self.selectCourse;
    });

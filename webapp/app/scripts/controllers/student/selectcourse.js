'use strict';

/**
 * @ngdoc function
 * @name scheduleApp.controller:StudentSelectcourseCtrl
 * @description
 * # StudentSelectcourseCtrl
 * Controller of the scheduleApp
 */
angular.module('scheduleApp')
    .controller('StudentSelectcourseCtrl', function($http, $state, $scope, $stateParams, courseService, studentService) {
        var self = this;
        var selectCourses = [];
        self.studentCourses = [];

        // 初始化
        self.init = function() {
            $scope.params = { page: 0, size: 3 };
            $scope.selectAllOrNot = false;
            studentService.getStudentByCourse($stateParams.id, function(data) {
                self.studentCourses = data.courseList;
            });
            self.load();
            $scope.$watch('courses.content', self.watchCourses, true);
        };

        // 加载数据
        self.load = self.reload = function() {
            courseService.getActiveSemesterByCourse($scope.params, function(data) {
                $scope.courses = data;
                // 将所有课程状态初始化为false
                angular.forEach($scope.courses.content, function(course) {
                    course._checked = false;
                });
                self.selectActive();
            });
        };

        // 当前学生已选课程默认选中
        self.selectActive = function() {
            var id = $stateParams.id;
            studentService.getStudentByCourse(id, function(data) {
                // 循环所有课程
                angular.forEach($scope.courses.content, function(course) {
                    if (selectCourses[$scope.params.page] !== undefined) {
                        self.courseSelectActive(selectCourses[$scope.params.page], course);
                        $scope.selectAllOrNot = selectCourses[$scope.params.page].length === $scope.courses.content.length
                    } else {
                        self.courseSelectActive(data.courseList, course);
                        $scope.selectAllOrNot = data.courseList.length === $scope.courses.content.length
                    }
                });


            });

        };

        self.courseSelectActive = function(data, targe) {
            // 循环当前学生所选的课程
            angular.forEach(data, function(checkedCourse) {
                // 判断当前学生课程与所有课程中相同的课程
                if (targe.id === checkedCourse.id) {
                    //默认选中已选课程
                    targe._checked = true;
                }
            });
        }

        // 分页时重新加载数据
        self.reloadPage = function(page) {
            selectCourses[$scope.params.page] = self.saveCourseByLeave();
            $scope.params.page = page;
            self.reload();
        };

        // 当离开分页时过滤学生课程 并返回保存数据
        self.saveCourseByLeave = function() {
            var array = [];
            angular.forEach($scope.courses.content, function(content) {
                if (content._checked) {
                    array.push(content);
                }
                self.filterStudentCourse(content);
            });
            return array;
        }

        // 过滤学生课程
        self.filterStudentCourse = function(filterCourse) {
            var isContain = self.entityContain(filterCourse, self.studentCourses);
            if (isContain) {
                self.studentCourses = self.studentCourses.filter(function(course) {
                    return course.id !== filterCourse.id;
                });
            }
        }

        // 判断实体是否包含
        self.entityContain = function(entity, entitys) {
            for (var i = 0; i < entitys.length; i++) {
                if (entity.id === entitys[i].id) {
                    return true;
                }
            }
        }

        // 进行每页大小
        self.reloadBySize = function(size) {
            $scope.params.size = size;
            self.load();
        };

        // 选课
        self.selectCourse = function() {
            var id = $stateParams.id;
            selectCourses[$scope.params.page] = self.saveCourseByLeave();
            var data = [];
            angular.forEach(selectCourses, function(tempArray) {
                angular.forEach(tempArray, function(temp) {
                    data.push(temp);
                });
            });
            angular.forEach(self.studentCourses, function(studentCourse) {
                data.push(studentCourse);
            });
            studentService.selectCourse(id, data, function() {
                $state.transitionTo('student', {}, { reload: true });
            });
        };

        // 全选
        self.select = function() {
            $scope.selectAllOrNot = !$scope.selectAllOrNot;
            angular.forEach($scope.courses.content, function(course) {
                course._checked = $scope.selectAllOrNot;
            });
        };

        //监听全部课程 _cheched 变化
        self.watchCourses = function(newValue) {
            if (newValue) {
                var count = 0;
                angular.forEach(newValue, function(value) {
                    if (value._checked) {
                        count++;
                    }
                });
                if (count != 0) {
                    $scope.selectAllOrNot = count === newValue.length;
                }
            }
        };

        $scope.select = self.select;
        $scope.selectCourse = self.selectCourse;
        $scope.reloadPage = self.reloadPage;
        $scope.reloadBySize = self.reloadBySize;
        self.init();
    });

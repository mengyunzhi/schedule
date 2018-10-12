'use strict';

describe('Controller: CourseEditCtrl', function () {

  // load the controller's module
  beforeEach(module('scheduleApp'));

  var CourseEditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CourseEditCtrl = $controller('CourseEditCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(CourseEditCtrl.awesomeThings.length).toBe(3);
  });
});

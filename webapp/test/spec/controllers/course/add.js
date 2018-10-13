'use strict';

describe('Controller: CourseAddCtrl', function () {

  // load the controller's module
  beforeEach(module('scheduleApp'));

  var CourseAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    CourseAddCtrl = $controller('CourseAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(CourseAddCtrl.awesomeThings.length).toBe(3);
  });
});

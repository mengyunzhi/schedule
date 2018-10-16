'use strict';

describe('Controller: StudentSelectcourseCtrl', function () {

  // load the controller's module
  beforeEach(module('scheduleApp'));

  var StudentSelectcourseCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StudentSelectcourseCtrl = $controller('StudentSelectcourseCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(StudentSelectcourseCtrl.awesomeThings.length).toBe(3);
  });
});

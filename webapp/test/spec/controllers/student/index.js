'use strict';

describe('Controller: StudentIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('scheduleApp'));

  var StudentIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StudentIndexCtrl = $controller('StudentIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(StudentIndexCtrl.awesomeThings.length).toBe(3);
  });
});

'use strict';

describe('Controller: SemesterEditCtrl', function () {

  // load the controller's module
  beforeEach(module('scheduleApp'));

  var SemesterEditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SemesterEditCtrl = $controller('SemesterEditCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(SemesterEditCtrl.awesomeThings.length).toBe(3);
  });
});

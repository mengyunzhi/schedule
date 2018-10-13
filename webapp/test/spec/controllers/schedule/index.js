'use strict';

describe('Controller: ScheduleIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('scheduleApp'));

  var ScheduleIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ScheduleIndexCtrl = $controller('ScheduleIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ScheduleIndexCtrl.awesomeThings.length).toBe(3);
  });
});

'use strict';

describe('Controller: ContributionIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('scheduleApp'));

  var ContributionIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ContributionIndexCtrl = $controller('ContributionIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ContributionIndexCtrl.awesomeThings.length).toBe(3);
  });
});

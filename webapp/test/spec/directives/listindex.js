'use strict';

describe('Directive: listindex', function () {

  // load the directive's module
  beforeEach(module('scheduleApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<listindex></listindex>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the listindex directive');
  }));
});

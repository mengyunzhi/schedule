'use strict';

describe('Filter: stateFilter', function () {

  // load the filter's module
  beforeEach(module('scheduleApp'));

  // initialize a new instance of the filter before each test
  var stateFilter;
  beforeEach(inject(function ($filter) {
    stateFilter = $filter('stateFilter');
  }));

  it('should return the input prefixed with "stateFilter filter:"', function () {
    var text = 'angularjs';
    expect(stateFilter(text)).toBe('stateFilter filter: ' + text);
  });

});

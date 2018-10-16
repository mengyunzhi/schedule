'use strict';

describe('Filter: operateFilter', function () {

  // load the filter's module
  beforeEach(module('scheduleApp'));

  // initialize a new instance of the filter before each test
  var operateFilter;
  beforeEach(inject(function ($filter) {
    operateFilter = $filter('operateFilter');
  }));

  it('should return the input prefixed with "operateFilter filter:"', function () {
    var text = 'angularjs';
    expect(operateFilter(text)).toBe('operateFilter filter: ' + text);
  });

});

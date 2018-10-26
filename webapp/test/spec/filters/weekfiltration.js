'use strict';

describe('Filter: weekFiltration', function () {

  // load the filter's module
  beforeEach(module('scheduleApp'));

  // initialize a new instance of the filter before each test
  var weekFiltration;
  beforeEach(inject(function ($filter) {
    weekFiltration = $filter('weekFiltration');
  }));

  it('should return the input prefixed with "weekFiltration filter:"', function () {
    var text = 'angularjs';
    expect(weekFiltration(text)).toBe('weekFiltration filter: ' + text);
  });

});

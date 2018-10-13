'use strict';

describe('Filter: isoTimeFormat', function () {

  // load the filter's module
  beforeEach(module('scheduleApp'));

  // initialize a new instance of the filter before each test
  var isoTimeFormat;
  beforeEach(inject(function ($filter) {
    isoTimeFormat = $filter('isoTimeFormat');
  }));

  it('should return the input prefixed with "isoTimeFormat filter:"', function () {
    var text = 'angularjs';
    expect(isoTimeFormat(text)).toBe('isoTimeFormat filter: ' + text);
  });

});

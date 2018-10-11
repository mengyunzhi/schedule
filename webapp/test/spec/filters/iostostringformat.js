'use strict';

describe('Filter: iosToStringFormat', function () {

  // load the filter's module
  beforeEach(module('scheduleApp'));

  // initialize a new instance of the filter before each test
  var iosToStringFormat;
  beforeEach(inject(function ($filter) {
    iosToStringFormat = $filter('iosToStringFormat');
  }));

  it('should return the input prefixed with "iosToStringFormat filter:"', function () {
    var text = 'angularjs';
    expect(iosToStringFormat(text)).toBe('iosToStringFormat filter: ' + text);
  });

});

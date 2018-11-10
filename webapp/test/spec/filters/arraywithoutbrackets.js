'use strict';

describe('Filter: arrayWithoutBrackets', function () {

  // load the filter's module
  beforeEach(module('scheduleApp'));

  // initialize a new instance of the filter before each test
  var arrayWithoutBrackets;
  beforeEach(inject(function ($filter) {
    arrayWithoutBrackets = $filter('arrayWithoutBrackets');
  }));

  it('should return the input prefixed with "arrayWithoutBrackets filter:"', function () {
    var text = 'angularjs';
    expect(arrayWithoutBrackets(text)).toBe('arrayWithoutBrackets filter: ' + text);
  });

});

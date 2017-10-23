import React from 'react';
import { render } from 'enzyme';
import Dashboard from './Dashboard';

describe('<Dashboard />', () => {
    it('should have a header called \'Mock IoT Data Generator Project\'', () => {
      // Arrange
      const expected = 'Mock IoT Data Generator Project';

      // Act
      const wrapper = render(<Dashboard />);
      const actual = wrapper.find('h1').text();

      // Assert
      expect(actual).toEqual(expected);
    });
  });

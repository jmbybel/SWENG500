import React from 'react';
import { shallow } from 'enzyme';
import HomePage from './HomePage';

describe('<HomePage />', () => {
    it('should have a header called \'Team 1 - IoT Project- SWENG500\'', () => {
      const wrapper = shallow(<HomePage />);
      const actual = wrapper.find('h1').text();
      const expected = 'Team 1 - IoT Project- SWENG500';
  
      expect(actual).toEqual(expected);
    });
  });
  
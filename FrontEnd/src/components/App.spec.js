import React from 'react';
import { render } from 'enzyme';
import App from './App';

describe('<App />', () => {
  it('should create an array of key value pairs between nav item indicies and uris', () => {
    // Arrange
    const expected = [
      {
        navItemKey: 1,
        uri: '/',
      },
      {
        navItemKey: 2,
        uri: '/create-new-device',
      },
      {
        navItemKey: 3,
        uri: '/view-devices',
      },
    ];

    // Act
    const sut = <App />;
    const actual = sut.navItemEventKeyToUriArray;
    
    // Assert
    expect(actual).toEqual(expected);
  });

  it('should have a header called \'Mock IoT Data Generator Project\'', () => {
    // Arrange
    const expected = 'Mock IoT Data Generator Project';

    // Act
    const wrapper = render(<HomePage />);
    const actual = wrapper.find('h1').text();
    
    // Assert
    expect(actual).toEqual(expected);
  });
});
  
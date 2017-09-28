import React from 'react';
import { shallow } from 'enzyme';
import App from './App';

describe('<App />', () => {
  it('render: should have a main menu', () => {
    // Arrange, Act
    const result = shallow(<App history={{}} />);
    
    // Assert
    expect(result.node.type).toEqual('section');
    expect(result.node.props.children.length).toEqual(2);
    const mainMenu = result.node.props.children[0];
    expect(mainMenu.type.name).toEqual('MainMenu');
  });
});
  
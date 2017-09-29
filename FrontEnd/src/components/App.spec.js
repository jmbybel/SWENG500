import React from 'react';
import { shallow } from 'enzyme';
import App from './App';

describe('<App />', () => {
  it('render: should have a MainMenu component', () => {
    // Arrange, Act
    const result = shallow(<App history={{}} />);
    
    // Assert
    expect(result.node.type).toEqual('section');
    expect(result.node.props.children.length).toEqual(2);
    const mainMenu = result.node.props.children[0];
    expect(mainMenu.type.name).toEqual('MainMenu');
  });

  it('render: should have a Switch component', () => {
    // Arrange, Act
    const result = shallow(<App history={{}} />);
    
    // Assert
    expect(result.node.type).toEqual('section');
    expect(result.node.props.children.length).toEqual(2);
    const swch = result.node.props.children[1];
    expect(swch.type.name).toEqual('Switch');
  });

  it('render: should have three routes defined', () => {
    // Arrange, Act
    const result = shallow(<App history={{}} />);
    
    // Assert
    expect(result.node.type).toEqual('section');
    expect(result.node.props.children.length).toEqual(2);
    const swch = result.node.props.children[1];
    expect(swch.type.name).toEqual('Switch');
    expect(swch.node.props.children.length).toEqual(3);
  });

  it('render: should have a route defined for the HomePage component', () => {
    // Arrange, Act
    const result = shallow(<App history={{}} />);
    
    // Assert
    expect(result.node.type).toEqual('section');
    expect(result.node.props.children.length).toEqual(2);
    const swch = result.node.props.children[1];
    expect(swch.type.name).toEqual('Switch');
    expect(swch.node.props.children.length).toEqual(3);
  });
});
  
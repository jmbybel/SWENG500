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

  it('render: should have two routes defined', () => {
    // Arrange, Act
    const result = shallow(<App history={{}} />);

    // Assert
    expect(result.node.type).toEqual('section');
    expect(result.node.props.children.length).toEqual(2);
    const swch = result.node.props.children[1];
    expect(swch.type.name).toEqual('Switch');
    expect(swch.props.children.length).toEqual(2);
  });

  it('render: should have a route defined for the Dashboard', () => {
    // Arrange
    const expected = '/';

    // Act
    const result = shallow(<App history={{}} />);

    // Assert
    expect(result.node.type).toEqual('section');
    expect(result.node.props.children.length).toEqual(2);
    const swch = result.node.props.children[1];
    expect(swch.type.name).toEqual('Switch');
    const homePage = swch.props.children[0];
    expect(homePage.props.path).toEqual(expected);
  });

  it('render: should have a route defined for the SensorsPage', () => {
    // Arrange
    const expected = '/sensors';

    // Act
    const result = shallow(<App history={{}} />);

    // Assert
    expect(result.node.type).toEqual('section');
    expect(result.node.props.children.length).toEqual(2);
    const swch = result.node.props.children[1];
    expect(swch.type.name).toEqual('Switch');
    const viewSensors = swch.props.children[1];
    expect(viewSensors.props.path).toEqual(expected);
  });
});

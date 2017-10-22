import React from 'react';
import PropTypes from 'prop-types';
import { Navbar, Nav, NavItem } from 'react-bootstrap';

class MainMenu extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.onSelect = this.onSelect.bind(this);
  }

  onSelect(selectedKey) {
    const {
      props: {
        navItemEventKeyToUriArray,
        onSelect,
      }
    } = this;
    const path = navItemEventKeyToUriArray[selectedKey - 1].uri;

    onSelect(selectedKey, path);
  }

  render() {
    const {
      props: {
        selectedTab,    
      },
    } = this;

    return (
      <Navbar
        inverse={true}>
        <Nav
          bsStyle="pills"
          activeKey={selectedTab}
          onSelect={this.onSelect}>
          <NavItem eventKey={1}>Home</NavItem>
          <NavItem eventKey={2}>Create New Sensor</NavItem>
          <NavItem eventKey={3}>View Sensors</NavItem>
        </Nav>
      </Navbar>
    );
  }
}

MainMenu.propTypes = {
  navItemEventKeyToUriArray: PropTypes.array,
  selectedTab: PropTypes.number,
  onSelect: PropTypes.func,
};

export default MainMenu;

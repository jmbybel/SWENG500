/* eslint-disable import/no-named-as-default */
import React from 'react';
import PropTypes from 'prop-types';
import MainMenu from './MainMenu';
import { Switch, Route } from 'react-router-dom';
import HomePage from './HomePage';
import NewDevicePage from '../containers/NewDevicePage';
import DevicesPage from '../containers/DevicesPage';

// This is a class-based component because the current
// version of hot reloading won't hot reload a stateless
// component at the top-level.
class App extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      selectedTab: 1,
    };
    this.onSelect = this.onSelect.bind(this);
  }

  onSelect(selectedTab, path) {
    const {
      props: {
        history,
      },
    } = this;

    this.setState({
      selectedTab,
    },
    () => {
      history.push(path);
    });
  }
  
  render() {
    const {
      state: {
        selectedTab,
      },
    } = this;

    return (
      <section>
        <MainMenu 
          selectedTab={selectedTab}
          onSelect={this.onSelect} />
        <Switch>
          <Route exact path="/" component={HomePage} />
          <Route exact path="/create-new-device" component={NewDevicePage} />
          <Route exact path="/view-devices" component={DevicesPage} />
        </Switch>
      </section>
    );
  }
}

App.propTypes = {
  history: PropTypes.object.isRequired,
  children: PropTypes.element,
};

export default App;

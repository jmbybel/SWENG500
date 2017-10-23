/* eslint-disable import/no-named-as-default */
import React from 'react';
import PropTypes from 'prop-types';
import MainMenu from './MainMenu';
import { Switch, Route } from 'react-router-dom';
import Dashboard from './Dashboard';
import NewSensorPage from '../containers/NewSensorPage';
import SensorsPage from '../containers/SensorsPage';

// This is a class-based component because the current
// version of hot reloading won't hot reload a stateless
// component at the top-level.
class App extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.navItemEventKeyToUriArray = [
      {
        navItemKey: 1,
        uri: '/',
      },
      {
        navItemKey: 2,
        uri: '/create-new-sensor',
      },
      {
        navItemKey: 3,
        uri: '/view-sensors',
      },
    ];
    this.state = {
      selectedTab: 1,
    };
    this.onSelect = this.onSelect.bind(this);
    this.onHistoryChanged = this.onHistoryChanged.bind(this);
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

  onHistoryChanged(path) {
    const selectedTab = this.navItemEventKeyToUriArray.find(element => element.uri === path).navItemKey;

    this.setState({
      selectedTab,
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
        <MainMenu selectedTab={selectedTab} onSelect={this.onSelect} navItemEventKeyToUriArray={this.navItemEventKeyToUriArray} />
        <Switch>
          <Route exact path="/" component={() => <Dashboard history={this.props.history} onHistoryChanged={this.onHistoryChanged} />} />
          <Route exact path="/create-new-sensor" component={() => <NewSensorPage history={this.props.history} onHistoryChanged={this.onHistoryChanged} />} />
          <Route exact path="/view-sensors" component={SensorsPage} />
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

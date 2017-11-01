import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as sensorActions from '../actions/sensorActions';
import NewSensorForm from '../components/NewSensorForm';
import SensorList from '../components/SensorList';
import {
  Tabs,
  Tab,
} from 'react-bootstrap';

class SensorsPage extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      sensor: null,
      key: 1,
    };
    this.handleSelect = this.handleSelect.bind(this);
  }

  handleSelect(key) {
    this.setState({
      key,
    });
  }
  
  fetchSelectedSensorId(sensorId) {
    //TODO actually fire off the sensorActions.getSensor (which doesn't exist yet in the server), and return the sensor. put that into the NewSensorForm and repaint it. 
    //also handleSelect(1) to reset the tabs on the Details.
  
    //let x = this.actions.getSensor(sensorId);
	alert(sensorId);
  }

  render() {
    const {
      props: {
        sensors: {
          sensors: sensorList,
        },
        sensor,
        actions: {
          saveNewSensor,
        },
      }
    } = this;

    return (
      <section>
        <SensorList selectedSensorId={this.fetchSelectedSensorId}
          sensors={sensorList} />
        <Tabs
          activeKey={this.state.key}
          onSelect={this.handleSelect}
          id={"sensorsPageTabs"}
          bsStyle={"tabs"}>
          <Tab eventKey={1} title="Properties">
            <NewSensorForm
              sensor={sensor}
              saveNewSensor={saveNewSensor} />
          </Tab>
          <Tab eventKey={2} title="Live">Live Time Series Chart</Tab>
        </Tabs>
      </section>
    );
  }
}

SensorsPage.propTypes = {
  sensors: PropTypes.object,
  sensor: PropTypes.object,
  actions: PropTypes.object.isRequired,
};

function mapStateToProps(state) {
  return {
    sensor: state.sensor,
    sensors: state.sensors,
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(sensorActions, dispatch)
  };
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SensorsPage);

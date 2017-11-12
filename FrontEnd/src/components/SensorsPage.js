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
      sensor: {
        name: '',
        initialValue: '',
        max: '',
        min: '',
        duration: '',
        interval: '',
        type: '',
        sinInterval: '',
        minInterval: '',
        maxInterval: '',
        randomInterval: '',
        urlEndpoint: '',
      },
      key: 1,
    };
    this.handleSelect = this.handleSelect.bind(this);
    this.handleDetailsClick = this.handleDetailsClick.bind(this);
  }

  handleSelect(key) {
    this.setState({
      key,
    });
  }

  handleDetailsClick(incomingSensor) {
    this.setState({
      sensor: incomingSensor,
      key: 1,//force the right-side pane to display the Details.
    });
  }

  handleStartClick(sensorId){
	alert("startEvent: " + sensorId);
  }

  handleStopClick(sensorId) {
	alert("stopEvent: " + sensorId);
  }

  handleDeleteClick(sensorId) {
	alert("We want an Are You Sure confirmation here");
	alert("deleteEvent: " + sensorId);
  }

  createNewSensorClick(component) {
    component.setState({
      sensor: {
        name: '',
        initialValue: '',
        max: '',
        min: '',
        duration: '',
        interval: '',
        type: '',
        sinInterval: '',
        minInterval: '',
        maxInterval: '',
        randomInterval: '',
        urlEndpoint: '',
      }
    });
  }

  render() {
    const {
      props: {
        sensors: {
          sensors: sensorList,
        },
        actions: {
          saveNewSensor,
        },
      },
      state: {
        sensor,
      },
    } = this;

    return (
      <section>
        <SensorList
          detailsClick={this.handleDetailsClick}
          startClick={this.handleStartClick}
          stopClick={this.handleStopClick}
          deleteClick={this.handleDeleteClick}
          createNewSensorClick={() => this.createNewSensorClick(this)}
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

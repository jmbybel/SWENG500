import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as sensorActions from '../actions/sensorActions';
import NewSensorForm from '../components/NewSensorForm';
import SensorList from '../components/SensorList';
import {
  Tabs,
  DropdownButton,
  MenuItem,
  Tab,
  Panel,
  Button,
} from 'react-bootstrap';

class SensorsPage extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      sensor: {
        _id: (((1+Math.random())*0x10000)|0) + (((1+Math.random())*0x10000)|0) + (((1+Math.random())*0x10000)|0),
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
      filterKey: "All",
    };
    this.handleSelect = this.handleSelect.bind(this);
    this.handleDetailsClick = this.handleDetailsClick.bind(this);
    this.handleFilterDropdown = this.handleFilterDropdown.bind(this);
  }

  handleFilterDropdown(entry) {
    this.setState({
      filterKey: entry,
    });
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
        _id: (((1+Math.random())*0x10000)|0) + (((1+Math.random())*0x10000)|0) + (((1+Math.random())*0x10000)|0),
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
          startSensor,
          updateSensor,
          pauseSensor,
          deleteSensor,
        },
      },
      state: {
        sensor,
      },
      createNewSensorClick,
    } = this;

    return (
      <section>
        <div>
          <div className={"filterDiv"}>
            <DropdownButton
              id="filterDropdown"
              bsStyle="primary"
              title="Show ..."
              onSelect={this.handleFilterDropdown}>
              <MenuItem eventKey="All">All</MenuItem>
              <MenuItem eventKey="Active">Active</MenuItem>
              <MenuItem eventKey="Inactive">Inactive</MenuItem>
            </DropdownButton>
            <b className={"filterSelection"}>{this.state.filterKey}</b>
          </div>
          <Button className={"newSensorButton"}
            bsStyle="primary"
            onClick={() => this.createNewSensorClick(this)}>
              {"New Sensor"}
          </Button>
        </div>
        <div className={"sensorListDiv"}>
          <Panel
            style={{height: '747px', overflow: 'auto'}}
            header={"Existing Sensors"}>
            <SensorList
              detailsClick={this.handleDetailsClick}
              startClick={startSensor}
              stopClick={pauseSensor}
              deleteClick={deleteSensor}
              sensors={sensorList} />
          </Panel>
        </div>
        <Tabs
          activeKey={this.state.key}
          onSelect={this.handleSelect}
          id={"sensorsPageTabs"}
          bsStyle={"tabs"}>
          <Tab eventKey={1} title="Properties">
            <NewSensorForm
              sensor={sensor}
              sensors={sensorList}
              updateSensor={updateSensor}
              saveNewSensor={(sensor) => {
                createNewSensorClick(this);
                sensor.type = sensor.type.toUpperCase();
                saveNewSensor(sensor);
              }} />
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

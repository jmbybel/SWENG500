import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as sensorActions from '../actions/sensorActions';
import NewSensorForm from '../components/NewSensorForm';
import SensorList from '../components/SensorList';
import Pusher from 'pusher-js';
import { BootstrapTable,TableHeaderColumn } from 'react-bootstrap-table';
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
      sensorFeed: [],
      filterKey: "All",
    };
    this.handleSelect = this.handleSelect.bind(this);
    this.handleDetailsClick = this.handleDetailsClick.bind(this);
    this.handleLiveClick = this.handleLiveClick.bind(this);
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

  handleLiveClick(incomingSensor) {
    this.setState({
      sensor: incomingSensor,
      key: 2,//force the right-side pane to display the Live Data.
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

  componentWillMount() {
    this.props.actions.getNumberOfRunningSensors();
    const pusher = new Pusher('05483fef894d660001a9', {
      cluster: 'us2',
      encrypted: true
    });
    const channel = pusher.subscribe('my-channel');
    this.pusher = pusher.bind(this);
    this.channel = channel.bind(this);
  }

  componentDidMount() {
    const {
      state: {
        sensorFeed,
      }
    } = this;

    this.channel.bind('my-event', data => {
      // get the new payload
      const newMessage = JSON.parse(data.message);
      if(newMessage.name === this.state.sensor.name)
      {
        // convert the payload from milliseconds to a date value
        newMessage.timestamp = new Date(newMessage.timestamp).toLocaleString();
        // add the payload to the beginning of the array
        sensorFeed.unshift(newMessage);
        // set the array length to 9 to remove old data
        sensorFeed.length = 9;
      }
      this.setState({
        sensorFeed
      });
    });
  }

  componentWillUnmount() {
    this.pusher.unsubscribe('my-channel');
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
        sensorFeed,
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
              liveClick={this.handleLiveClick}
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
          <Tab eventKey={2} title="Live">
          <BootstrapTable height={"400px"} data={sensorFeed} bordered={false} options={{noDataText: "No sensor selected for live data (Click Details)" }}>
            <TableHeaderColumn dataField="name" isKey={true}>Sensor Name</TableHeaderColumn>
            <TableHeaderColumn dataField="timestamp">Timestamp</TableHeaderColumn>
            <TableHeaderColumn dataField="value">Payload Value</TableHeaderColumn>
          </BootstrapTable>
          </Tab>
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

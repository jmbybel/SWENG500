import React from 'react';
import PropTypes from 'prop-types';
import { Button, ButtonGroup, Panel } from 'react-bootstrap';


class SensorList extends React.Component {
  constructor(props, context) {
    super(props, context);
    this.mapSensors = this.mapSensors.bind(this);
  }

  createNewSensorClick() {
	alert("this needs to call up to the parent, clear the sensor in state, which should repaint the UI. After that make sure we're forced back to the first tab on the right side");
  }

  mapSensors(sensors) {
    return sensors.length > 0
      ? sensors.map((sensor) => (
        <div key={sensor.id}>
          <Panel header={sensor.name}>
            System ID: {sensor.id}&nbsp;&nbsp;&nbsp;&nbsp;
            <ButtonGroup>
             <Button
               onClick={()=>this.props.detailsClick(sensor)} bsStyle="info" >
               Show/Hide Details
             </Button>
             <Button onClick={()=>this.props.startClick(sensor.id)}  bsStyle="success">Start</Button>
             <Button onClick={()=>this.props.stopClick(sensor.id)}   bsStyle="warning">Stop</Button>
             <Button onClick={()=>this.props.deleteClick(sensor.id)}   bsStyle="danger">Delete</Button>
            </ButtonGroup>
            <br/>
            Active: false<br/>
            Created On: {sensor.expiration}<br/>
          </Panel>
        </div>
        ))
      : null;
  }

  render() {
    const {
      props: {
        sensors,
      },
    } = this;

    return (
      <section className={"sensorList"}>
        <div>
        {this.mapSensors(sensors)}
        <Button onClick={()=>this.createNewSensorClick()}>New Sensor</Button>
        </div>
      </section>
    );
  }
}

SensorList.propTypes = {
  sensors: PropTypes.array,
  selectedSensorId: PropTypes.string,
};

export default SensorList;

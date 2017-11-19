import React from 'react';
import PropTypes from 'prop-types';
import {
  Button,
  ButtonGroup,
  ListGroup,
  ListGroupItem
} from 'react-bootstrap';


class SensorList extends React.Component {
  constructor(props, context) {
    super(props, context);
    this.mapSensors = this.mapSensors.bind(this);
    this.renderSingleSensor = this.renderSingleSensor.bind(this);
  }
  

  mapSensors(sensors) {
    return sensors.length > 0
      ? sensors.map((sensor, key) => (
        this.renderSingleSensor(sensor,key)
        ))
      : "No sensors currently exist. Create one!";
  }
  
  renderSingleSensor(sensor, key) {
  let renderMe = this.props.filter == "All" || (this.props.filter == "Active" && sensor.active) || (this.props.filter == "Inactive" && !sensor.active);
  if (renderMe) {
    return <ListGroupItem key={key}>
      <h4 className="list-group-item-heading">{sensor.name}</h4>
      System ID: {sensor._id}&nbsp;&nbsp;&nbsp;&nbsp;
      <ButtonGroup>
        <Button onClick={()=>this.props.detailsClick(sensor)} bsStyle="info" >
          {"Details"}
        </Button>
        <Button disabled={sensor.active} onClick={()=>this.props.startClick(sensor._id)}  bsStyle="success">Start</Button>
        <Button disabled={!sensor.active} onClick={()=>this.props.stopClick(sensor._id)}   bsStyle="warning">Stop</Button>
        <Button onClick={()=>this.props.deleteClick(sensor._id)}   bsStyle="danger">Delete</Button>
        <Button onClick={()=>this.props.liveClick(sensor)} bsStyle="primary">Live</Button>
      </ButtonGroup>
      <br/>
      Active: false<br/>
      Created On: {sensor.expiration}<br/>
    </ListGroupItem>
    }
    return;
  }

  render() {
    const {
      props: {
        sensors,
      },
    } = this;

    return (
      <ListGroup className={"sensorList"}>
        {this.mapSensors(sensors)}
      </ListGroup>
    );
  }
}

SensorList.propTypes = {
  sensors: PropTypes.array,
  selectedSensorId: PropTypes.string,
  detailsClick: PropTypes.func.isRequired,
  startClick: PropTypes.func.isRequired,
  stopClick: PropTypes.func.isRequired,
  deleteClick: PropTypes.func.isRequired,
  filter: PropTypes.string,
};

export default SensorList;

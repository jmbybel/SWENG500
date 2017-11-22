import React from 'react';
import PropTypes from 'prop-types';
import {
  Button,
  ButtonGroup,
  ListGroup,
  ListGroupItem
} from 'react-bootstrap';
import toPascalCase from 'to-pascal-case';

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
  let renderMe = this.props.filter == "All" || (this.props.filter == "Active" && String(sensor.enabled) == "true") || (this.props.filter == "Inactive" && String(!sensor.enabled) == "true");
  let floatRigthStyleForJsx = {float:"right"};

  if (renderMe) {
    return <ListGroupItem key={key}>
      <h4 title={sensor._id} className="list-group-item-heading">{sensor.name}<span style={floatRigthStyleForJsx}>ID: {sensor._id}</span></h4>
      <span>{`Active: ${toPascalCase(String(sensor.enabled))}`}</span><br/>

      <ButtonGroup>
        <Button onClick={()=>this.props.detailsClick(sensor)} bsStyle="info" >
          {"Details"}
        </Button>
        <Button disabled={String(sensor.enabled) == "true"} onClick={()=>this.props.startClick(sensor)}  bsStyle="success">Start</Button>
        <Button disabled={String(!sensor.enabled) == "true"} onClick={()=>this.props.stopClick(sensor)}   bsStyle="warning">Stop</Button>
        <Button onClick={()=>this.props.deleteClick(sensor._id)}   bsStyle="danger">Delete</Button>
        <Button onClick={()=>this.props.liveClick(sensor)} bsStyle="primary">Live</Button>
      </ButtonGroup>
      <br/>
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

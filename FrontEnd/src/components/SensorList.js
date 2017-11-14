import React from 'react';
import PropTypes from 'prop-types';
import {
  Button,
  ButtonGroup,
  Panel,
  ListGroup,
  ListGroupItem
} from 'react-bootstrap';


class SensorList extends React.Component {
  constructor(props, context) {
    super(props, context);
    this.mapSensors = this.mapSensors.bind(this);
  }

  mapSensors(sensors) {
    return sensors.length > 0
      ? sensors.map((sensor, key) => (
        <ListGroupItem key={key} header={sensor.name}>
          System ID: {sensor._id}&nbsp;&nbsp;&nbsp;&nbsp;
          <ButtonGroup>
            <Button
              onClick={()=>this.props.detailsClick(sensor)} bsStyle="info" >
              Show/Hide Details
            </Button>
            <Button onClick={()=>this.props.startClick(sensor._id)}  bsStyle="success">Start</Button>
            <Button onClick={()=>this.props.stopClick(sensor._id)}   bsStyle="warning">Stop</Button>
            <Button onClick={()=>this.props.deleteClick(sensor._id)}   bsStyle="danger">Delete</Button>
          </ButtonGroup>
          <br/>
          Active: false<br/>
          Created On: {sensor.expiration}<br/>
        </ListGroupItem>
        ))
      : "No sensors currently exist. Create one!";
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
};

export default SensorList;

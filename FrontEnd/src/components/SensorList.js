import React from 'react';
import PropTypes from 'prop-types';
import { Button, ButtonGroup, Panel } from 'react-bootstrap';


class SensorList extends React.Component {
  constructor(props, context) {
    super(props, context);
    this.showDetailsClick = this.showDetailsClick.bind(this);
    this.mapSensors = this.mapSensors.bind(this);
  }
  
  showDetailsClick (sensorId) {
    this.props.selectedSensorId(sensorId);
  }

  mapSensors(sensors) {
    return sensors.length > 0
      ? sensors.map((sensor) => (
        <div key={sensor.id}>
          <Panel header={sensor.name}>
            System ID: {sensor.id}&nbsp;&nbsp;&nbsp;&nbsp;
            <ButtonGroup>
             <Button 
               onClick={()=>this.showDetailsClick(sensor.id)} >
               Show/Hide Details
             </Button>
             <Button>Start</Button>
             <Button>Stop</Button>
             <Button>Delete</Button>
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
        <Button>New Sensor</Button>
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

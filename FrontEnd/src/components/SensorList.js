import React from 'react';
import PropTypes from 'prop-types';
import { PageHeader, Button, ButtonGroup, Panel } from 'react-bootstrap';


class SensorList extends React.Component {
  constructor(props, context) {
    super(props, context);
    this.mapSensors = this.mapSensors.bind(this);
  }

  mapSensors(sensors) {
    return sensors.length > 0
      ? sensors.map((sensor) => (
        <div key={sensor.id}>
          <Panel header={sensor.name}>
            System ID: {sensor.id}&nbsp;&nbsp;&nbsp;&nbsp;
            <ButtonGroup>
             <Button >Show/Hide Details</Button>
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
      <section>
        <PageHeader>Sensor List</PageHeader>
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
};

export default SensorList;

import React from 'react';
import PropTypes from 'prop-types';
import { PageHeader } from 'react-bootstrap';

class SensorList extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.mapSensors = this.mapSensors.bind(this);
  }

  mapSensors(sensors) {
    return sensors.length > 0
      ? sensors.map((sensor, index) => (
          <div key={index}>
            {sensor.id}
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
        {this.mapSensors(sensors)}
      </section>
    );
  }
}

SensorList.propTypes = {
  sensors: PropTypes.array,
};

export default SensorList;

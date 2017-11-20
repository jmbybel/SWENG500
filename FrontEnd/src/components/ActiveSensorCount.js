import React from 'react';
import PropTypes from 'prop-types';
import { Panel } from 'react-bootstrap';

class ActiveSensorCount extends React.Component {
  constructor(props, context) {
    super(props, context);

  }

  render() {
    const {
      props: {
        numRunningSensors: {
          count,
        }
      },
    } = this;

  return (
    <section className={"activeSensorCount"}>
      <Panel header="Active Sensors">
        {`The current number of actively running sensors is: ${count}`}
      </Panel>
    </section>
  );
  }
}

ActiveSensorCount.propTypes = {
  numRunningSensors: PropTypes.object,
};

export default ActiveSensorCount;

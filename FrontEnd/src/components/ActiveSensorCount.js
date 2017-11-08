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
        numRunningSensors,
      },
    } = this;

  return (
    <section className={"actionSensorCount"}>
      <Panel header="Active Sensors">
        {numRunningSensors}
      </Panel>
    </section>
  );
  }
}

ActiveSensorCount.propTypes = {
  numRunningSensors: PropTypes.string,
};

export default ActiveSensorCount;

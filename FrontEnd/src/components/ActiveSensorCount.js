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
    <section className={"actionSensorCount"}>
      <Panel header="Active Sensors">
        {count}
      </Panel>
    </section>
  );
  }
}

ActiveSensorCount.propTypes = {
  numRunningSensors: PropTypes.object,
};

export default ActiveSensorCount;

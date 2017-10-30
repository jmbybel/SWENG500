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
        theCount,
      },
    } = this;
  
  return (
    <Panel header="Active Sensors">
      {theCount}
    </Panel>
  );
  }
}

ActiveSensorCount.propTypes = {
  theCount: PropTypes.number,
};
  
export default ActiveSensorCount;
import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as sensorActions from '../actions/sensorActions';
import { PageHeader } from 'react-bootstrap';
import ActiveSensorCount from '../components/ActiveSensorCount';
import LiveDataFeed from '../components/LiveDataFeed';

//TODO dummy values for live data feed til we can get pushed data
const rows = [{
  name: 'Sample Sensor 1',
  timestamp: 'Nov 1, 2017 4:12:04 AM',
  type: 'Random',
  payload: '67.889696'
},{
  name: 'Sample Sensor 2',
  timestamp: 'Nov 2, 2017 2:23:02 PM',
  type: 'Sin',
  payload: '36.0'
},{
  name: 'Sample Sensor 3',
  timestamp: 'Nov 2, 2017 2:43:01 PM',
  type: 'Ramp',
  payload: '109.2'
}];

class Dashboard extends React.Component {
  constructor(props, context) {
    super(props, context);
    props.actions.getNumberOfRunningSensors();
  }

  render() {
    const {
      props: {  
        sensors: {
          numRunningSensors,
        },
      },
    } = this;
   
    return (
      <section>
        <PageHeader>Mock IoT Data Generator Project</PageHeader>
        <ActiveSensorCount numRunningSensors={numRunningSensors}/>
        <LiveDataFeed sensors={rows} />
      </section>
    );
  }
}

Dashboard.propTypes = {
  sensors: PropTypes.object,
  sensor: PropTypes.object,
  actions: PropTypes.object.isRequired,
};

function mapStateToProps(state) {
  return {
    sensor: state.sensor,
    sensors: state.sensors,
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(sensorActions, dispatch)
  };
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Dashboard);

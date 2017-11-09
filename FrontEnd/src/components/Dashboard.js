import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as sensorActions from '../actions/sensorActions';
import { PageHeader } from 'react-bootstrap';
import ActiveSensorCount from '../components/ActiveSensorCount';
import LiveDataFeed from '../components/LiveDataFeed';
import sensorApi from '../api/sensorApi';
import Pusher from 'pusher-js';


class Dashboard extends React.Component {
  constructor(props, context) {
    super(props, context);
    this.state = {
      sensors: ''
    }
    props.actions.getNumberOfRunningSensors();
  }

  
  componentDidMount() {
    this.getSensors().then(result => this.setState({
      sensors: result
    }))
  }

  getSensors() {
    return sensorApi.getPayloads()
  }

  render() {
    const {
      props: {
        sensors: {
          numRunningSensors,
        },
      },
    } = this;

    var pusher = new Pusher('05483fef894d660001a9', {
      cluster: 'us2',
      encrypted: true
    });
    
    var channel = pusher.subscribe('my-channel');
    channel.bind('my-event', function(data) {
      //alert(data.message);
    });
    
    return (
      <section>
        <PageHeader>
          <span>
            {"Mock IoT Data Generator Project"}
          </span>
        </PageHeader>
        <ActiveSensorCount
            numRunningSensors={numRunningSensors}/>
        <LiveDataFeed
          sensors={this.state.sensors} />
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

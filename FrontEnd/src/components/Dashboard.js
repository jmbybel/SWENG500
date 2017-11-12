import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as sensorActions from '../actions/sensorActions';
import { PageHeader, Panel } from 'react-bootstrap';
import ActiveSensorCount from '../components/ActiveSensorCount';
import LiveDataFeed from '../components/LiveDataFeed';
import Pusher from 'pusher-js';

class Dashboard extends React.Component {
  constructor(props, context) {
    super(props, context);
    this.state = {
      sensorFeed: [],
    };
  }

  componentWillMount() {
    this.props.actions.getNumberOfRunningSensors();
    const pusher = new Pusher('05483fef894d660001a9', {
      cluster: 'us2',
      encrypted: true
    });
    const channel = pusher.subscribe('my-channel');
    this.pusher = pusher.bind(this);
    this.channel = channel.bind(this);
  }

  componentDidMount() {
    const {
      state: {
        sensorFeed,
      }
    } = this;

    this.channel.bind('my-event', data => {
      const newMessage = JSON.parse(data.message);
      let newSensorFeed = null;

      if (sensorFeed.length >= 20) {
        newSensorFeed = sensorFeed.slice(sensorFeed.length - 20, sensorFeed.length);
      }
      else {
        newSensorFeed = sensorFeed;
      }

      newSensorFeed.push(newMessage);
      this.setState({
        sensorFeed: newSensorFeed
      });
    });
  }

  componentWillUnmount() {
    this.pusher.unsubscribe('my-channel');
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
        <PageHeader>
          <span>
            {"Mock IoT Data Generator Project"}
          </span>
        </PageHeader>
        <ActiveSensorCount
            numRunningSensors={numRunningSensors}/>
        <Panel className={"liveDataFeed"}>
          <LiveDataFeed
            sensorFeed={this.state.sensorFeed} />
        </Panel>
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

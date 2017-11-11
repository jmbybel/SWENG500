import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as sensorActions from '../actions/sensorActions';
import { PageHeader } from 'react-bootstrap';
import ActiveSensorCount from '../components/ActiveSensorCount';
import LiveDataFeed from '../components/LiveDataFeed';
import Pusher from 'pusher-js';


class Dashboard extends React.Component {
  constructor(props, context) {
    super(props, context);
    this.state = {
      sensorFeed: []
    };

    this.stripEndQuotes = this.stripEndQuotes.bind(this);
  }

  componentDidMount() {
    //this.getSensors().then(result => this.setState({
      //sensors: result
    //}))
    const {
      state: {
        sensorFeed
      }
    } = this;

    const pusher = new Pusher('05483fef894d660001a9', {
      cluster: 'us2',
      encrypted: true
    });

    const channel = pusher.subscribe('my-channel');
    channel.bind('my-event', data => {
      const test = JSON.parse(data.message);
      //this.stripEndQuotes(data.message);
      sensorFeed.push(test);
      // const newArray = sensorFeed.concat(test);
      // console.log(newArray);
      this.setState({ sensorFeed });

      //this.setState({sensors: this.state.sensors.concat(data.message)})
    });
  }

  onComponentWillMount() {
    this.props.actions.getNumberOfRunningSensors();
  }

  stripEndQuotes(s){ let t=s.length; if (s.charAt(0)=='"') s=s.substring(1,t--); if (s.charAt(--t)=='"') s=s.substring(0,t); return s; }

  /*
  getSensors() {
    return sensorApi.getPayloads()
  }
  */

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
        <LiveDataFeed
          sensorFeed={this.state.sensorFeed} />
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

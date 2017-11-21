import React from 'react';
import PropTypes from 'prop-types';
import NewSensorTextInput from './NewSensorTextInput';
import { connect } from 'react-redux';
import {bindActionCreators} from 'redux';
import * as sensorActions from '../actions/sensorActions';
import { PageHeader, Panel, Button } from 'react-bootstrap';
import ActiveSensorCount from '../components/ActiveSensorCount';
import LiveDataFeed from '../components/LiveDataFeed';
import Pusher from 'pusher-js';

class Dashboard extends React.Component {
  constructor(props, context) {
    super(props, context);
    
    this.state = {
      sensorFeed: [],
    };
    this.destinationIPKeypress = this.destinationIPKeypress.bind(this);
  }

  componentWillMount() {
    this.props.actions.getNumberOfRunningSensors();
    this.props.actions.getDestinationIP();
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
      // get the new payload
      const newMessage = JSON.parse(data.message);
      // convert the payload from milliseconds to a date value
      newMessage.timestamp = new Date(newMessage.timestamp).toLocaleString();
      // add the payload to the beginning of the array
      sensorFeed.unshift(newMessage);
      // set the array length to 9 to remove old data
      sensorFeed.length = 9;

      this.setState({
        sensorFeed
      });
    });
  }

  componentWillUnmount() {
    this.pusher.unsubscribe('my-channel');
  }

  destinationIPKeypress(field, value) {
    // const {
    //   state: {
    //     sensor,
    //   },
    // } = this;

    //   this.setState({
    //     sensor: Object.assign(
    //       {},
    //       sensor,
    //       { [field]: value }
    //     )
    //   });
    console.log("nothing for now." + field + value);
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
        <div className={"upperHalfDashboard"}>
          <ActiveSensorCount
              numRunningSensors={numRunningSensors}/>
          <div
            className={'destinationIPDiv'}>
            <span
              className={'labelSpan'}>
              {"Destination IP"}
            </span>
            <NewSensorTextInput
                name={'destinationIP'}
                value={""}
                onChange={this.newSensorKeypress} />
            <Button
              className={'destinationIPSaveButton'}
              bsStyle="primary"
              onClick={this.save}>
              {"Save"}
            </Button>
          </div>
        </div>
        <Panel className={"liveDataFeed"} header="Live Data Feed for All Sensors">
          <LiveDataFeed
            sensorFeed={this.state.sensorFeed}
            noDataMessage={numRunningSensors.count === 0 ? "No Sensors are running" : "Waiting for payloads.."} />
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

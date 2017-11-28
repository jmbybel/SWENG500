import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import {bindActionCreators} from 'redux';
import * as sensorActions from '../actions/sensorActions';
import { PageHeader, Panel } from 'react-bootstrap';
import ActiveSensorCount from '../components/ActiveSensorCount';
import DestinationIPSection from '../components/DestinationIPSection';
import LiveDataFeed from '../components/LiveDataFeed';
import Pusher from 'pusher-js';

class Dashboard extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      sensorFeed: [],
      destinationIP: '',
    };
    this.destinationIPKeypress = this.destinationIPKeypress.bind(this);
    this.setDestinationIP = this.setDestinationIP.bind(this);
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
      },
      props: {
        sensors: {
          destinationIP: {
            destinationIPfromServer: {
              ip,
            }
          },
        }
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
        sensorFeed,
      });
    });

    /* eslint react/no-did-mount-set-state: 0 */
    this.setState({
      destinationIP: ip,
    });
  }

  componentWillReceiveProps(nextProps) {
    const {
      sensors: {
        destinationIP: {
          destinationIPfromServer: {
            ip,
          }
        },
      }
    } = nextProps;

    this.props.actions.getNumberOfRunningSensors();
    this.setState({
      destinationIP: ip,
    });
  }

  componentWillUnmount() {
    this.pusher.unsubscribe('my-channel');
  }

  destinationIPKeypress(field, value) {
    this.setState({
      destinationIP: value,
    });
  }

  setDestinationIP() {
    const {
      props: {
        actions: {
          setDestinationIP,
        },
      },
      state: {
        destinationIP,
      }
    } = this;

    setDestinationIP(destinationIP);
  }

  render() {
    const {
      props: {
        sensors: {
          numRunningSensors,
        },
      },
      state: {
        destinationIP,
      }
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
          <DestinationIPSection
            name={"destinationIP"}
            value={destinationIP}
            onChange={(name, value) => this.destinationIPKeypress(name, value)}
            save={() => this.setDestinationIP()} />
        </div>
        <Panel className={"liveDataFeed"} header="Live Data Feed for All Sensors">
          <LiveDataFeed
            sensorFeed={this.state.sensorFeed}
            noDataMessage={numRunningSensors.count === 0 ? "No sensors are currently running" : "Waiting for payloads ..."} />
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

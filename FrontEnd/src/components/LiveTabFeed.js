import React from 'react';
import PropTypes from 'prop-types';
import Pusher from 'pusher-js';
import { BootstrapTable,TableHeaderColumn } from 'react-bootstrap-table';

class LiveTabFeed extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      sensorFeed: [],
    };
    this.messageHandler = this.messageHandler.bind(this);
    this.bindDataSource = this.bindDataSource.bind(this);
  }

  componentWillMount() {
    const pusher = new Pusher('05483fef894d660001a9', {
      cluster: 'us2',
      encrypted: true
    });
    const channel = pusher.subscribe('my-channel');
    this.pusher = pusher.bind(this);
    this.channel = channel.bind(this);
  }

  componentDidMount() {
    this.bindDataSource(this.props);
  }

  componentWillReceiveProps(nextProps) {
    this.setState({
        sensorFeed:[]
    },
    this.bindDataSource(nextProps)
  );
}

  componentWillUnmount() {
    this.pusher.unsubscribe('my-channel');
  }

  messageHandler(data, sensorId) {
    const {
      state: {
        sensorFeed,
      }
    } = this;

    // get the new payload
    const newMessage = JSON.parse(data.message);

    if (newMessage.id === sensorId) {
        newMessage.timestamp = new Date(newMessage.timestamp).toLocaleString();
        if (sensorFeed.some((element) => {return element.timestamp == newMessage.timestamp;}) === false) {
          // convert the payload from milliseconds to a date value
          // add the payload to the beginning of the array
          sensorFeed.unshift(newMessage);
          // set the array length to 17 to remove old data
          sensorFeed.length = 17;
          this.setState({
              sensorFeed
          });
      }
    }
  }

  bindDataSource(props) {
    this.channel.unbind();
    this.channel.bind('my-event', data => this.messageHandler(data, props.sensorId));
  }

  render() {
    const {
      state: {
        sensorFeed,
        noDataMessage,
      },
    } = this;

    return (
      <section className={"liveTabFeed"}>
       <BootstrapTable className={"liveTabFeedTable"} height={"720px"} data={sensorFeed} bordered={false} options={{noDataText: noDataMessage }}>
          <TableHeaderColumn dataField="name" isKey={true}>Sensor Name</TableHeaderColumn>
          <TableHeaderColumn dataField="timestamp">Timestamp</TableHeaderColumn>
          <TableHeaderColumn dataField="value">Payload Value</TableHeaderColumn>
        </BootstrapTable>
      </section>
    );
  }
}

LiveTabFeed.propTypes = {
    sensorId: PropTypes.number,
    noDataMessage: PropTypes.string,
  };

export default LiveTabFeed;

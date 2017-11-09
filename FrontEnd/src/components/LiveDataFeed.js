import React from 'react';
import PropTypes from 'prop-types';
import { BootstrapTable,TableHeaderColumn } from 'react-bootstrap-table';

class LiveDataFeed extends React.Component {
  constructor(props, context) {
    super(props, context);
  }

  render() {
    const {
        props: {
          sensors,
        },
      } = this;

    return (
      <section className={"liveDataFeed"}>
        <BootstrapTable data={sensors} bordered={false} options={{noDataText: 'No Sensors are running' }}>
          <TableHeaderColumn dataField='name' isKey={true}>Sensor Name</TableHeaderColumn>
          <TableHeaderColumn dataField='timestamp'>Timestamp</TableHeaderColumn>
          <TableHeaderColumn dataField='type'>Type</TableHeaderColumn>
          <TableHeaderColumn dataField='value'>Payload Value</TableHeaderColumn>
        </BootstrapTable>
      </section>
    );
  }
}

LiveDataFeed.propTypes = {
  sensors: PropTypes.any,
};

export default LiveDataFeed;

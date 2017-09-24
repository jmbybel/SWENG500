import React from 'react';
import PropTypes from 'prop-types';
import { PageHeader } from 'react-bootstrap';

class DeviceList extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.mapDevices = this.mapDevices.bind(this);
  }

  mapDevices(devices) {
    return devices.length > 0
      ? devices.map((device, index) => (
          <div key={index}>
            {device.device.name}
          </div>
        ))
      : null;
  }

  render() {
    const {
      props: {
        devices,
      },
    } = this;

    return (
      <section>
        <PageHeader>Device List</PageHeader>
        {this.mapDevices(devices)}
      </section>
    );
  }
}

DeviceList.propTypes = {
  devices: PropTypes.array,
};

export default DeviceList;

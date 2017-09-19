import React from 'react';
import PropTypes from 'prop-types';

class DeviceList extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.mapDevices = this.mapDevices.bind(this);
  }

  mapDevices(devices) {
    return devices.length > 0
      ? devices.map((device, index) => (
          <tr key={index}>
            <td>{device.device.name}</td>
          </tr>
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
      <div>
        <table>
          <thead>
            <tr>
              <th>Name</th>
            </tr>
          </thead>
          <tbody>
            {this.mapDevices(devices)}
          </tbody>
        </table>
      </div>
    );
  }
}

DeviceList.propTypes = {
  devices: PropTypes.array,
};

export default DeviceList;

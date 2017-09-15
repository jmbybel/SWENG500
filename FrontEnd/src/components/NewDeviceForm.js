import React from 'react';
import PropTypes from 'prop-types';
import NewDeviceTextInput from './NewDeviceTextInput';

class NewDeviceForm extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      newDeviceName: props.newDeviceName,
    };
    this.mapDevices = this.mapDevices.bind(this);
    this.newDeviceKeypress = this.newDeviceKeypress.bind(this);
    this.save = this.save.bind(this);
  }

  mapDevices(devices) {
    return devices
      ? devices.map((device, index) => (
          <tr key={index}>
            <td>{device.name}</td>
          </tr>
        ))
      : null;
  }

  newDeviceKeypress(name, value) {
    this.setState({
      newDeviceName: value,
    });
  }

  save() {
    this.props.saveNewDevice(this.state.newDeviceName);
  }

  render() {
    const {
      props: {
        devices,
      },
      state: {
        newDeviceName,
      },
    } = this;

    return (
      <div>
        <h2>New Device Form</h2>
        <table>
          <tbody>
            <tr>
              <td><label htmlFor="newDeviceName">New Device Name</label></td>
              <td><NewDeviceTextInput name="newDeviceName" value={newDeviceName} onChange={this.newDeviceKeypress} /></td>
            </tr>
          </tbody>
        </table>
        <br/>
        <input type="submit" value="Save" onClick={this.save}/>
        <br />
        <br />
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

NewDeviceForm.propTypes = {
  devices: PropTypes.array,
  newDeviceName: PropTypes.string,
  saveNewDevice: PropTypes.func.isRequired,
};

export default NewDeviceForm;

import React from 'react';
import PropTypes from 'prop-types';
import NewDeviceTextInput from './NewDeviceTextInput';

class NewDeviceForm extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      device: {
        name: '',
      }
    };
    this.newDeviceKeypress = this.newDeviceKeypress.bind(this);
    this.save = this.save.bind(this);
  }

  newDeviceKeypress(name, value) {
    this.setState({
      device: {
        name: value,
      },
    });
  }

  save() {
    this.props.saveNewDevice(this.state.device);
  }

  render() {
    const {
      state: {
        device,
      },
    } = this;

    return (
      <div>
        <h2>New Device Form</h2>
        <table>
          <tbody>
            <tr>
              <td><label htmlFor="device">New Device Name</label></td>
              <td><NewDeviceTextInput name="device" value={device.name} onChange={this.newDeviceKeypress} /></td>
            </tr>
          </tbody>
        </table>
        <br/>
        <input type="submit" value="Save" onClick={this.save}/>
      </div>
    );
  }
}

NewDeviceForm.propTypes = {
  device: PropTypes.object,
  saveNewDevice: PropTypes.func.isRequired,
};

export default NewDeviceForm;

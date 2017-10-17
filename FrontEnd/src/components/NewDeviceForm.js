import React from 'react';
import PropTypes from 'prop-types';
import NewDeviceTextInput from './NewDeviceTextInput';
import NewDeviceSensorTypeDropdown from './NewDeviceSensorTypeDropdown';
import NewDeviceRandomIntervalDropdown from './NewDeviceRandomIntervalDropdown';
import {
  PageHeader,
  Button,
} from 'react-bootstrap';

class NewDeviceForm extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      device: {
        name: '',
        initialValue: 0,
        max: 0,
        min: 0,
        duration: 0,
        interval: 0,
        type: '',
        sinInterval: 0,
        minInterval: 0,
        maxInterval: 0,
        randomInterval: '',
      }
    };
    this.newDeviceKeypress = this.newDeviceKeypress.bind(this);
    this.dropdownOnSelect = this.dropdownOnSelect.bind(this);
    this.save = this.save.bind(this);
  }

  newDeviceKeypress(name, value) {
    this.setState({
      device: {
        name: value,
      },
    });
  }

  dropdownOnSelect(eventKey, event) {
    console.dir(eventKey);
    console.dir(event);
  }

  save() {
    const {
      props: {
        saveNewDevice,
        onHistoryChanged,
        history,
      },
    } = this;

    saveNewDevice(this.state.device);
    history.push('/view-devices');
    onHistoryChanged('/view-devices');
  }

  render() {
    const {
      state: {
        device,
      },
    } = this;

    return (
      <section
        className={'newDeviceForm'}>
        <PageHeader>
          New Device Form
        </PageHeader>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Name
          </span>
          <NewDeviceTextInput
            value={device.name}
            onChange={this.newDeviceKeypress} />
        </div>
        {/* Start new fields  */}
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Initial Value
          </span>
          <NewDeviceTextInput
            value={device.initialValue}
            onChange={this.newDeviceKeypress} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Max Value
          </span>
          <NewDeviceTextInput
            value={device.max}
            onChange={this.newDeviceKeypress} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Min Value
          </span>
          <NewDeviceTextInput
            value={device.min}
            onChange={this.newDeviceKeypress} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Duration in Milliseconds
          </span>
          <NewDeviceTextInput
            value={device.duration}
            onChange={this.newDeviceKeypress} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Interval in Milliseconds
          </span>
          <NewDeviceTextInput
            value={device.interval}
            onChange={this.newDeviceKeypress} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Sensor Type
          </span>
          <NewDeviceSensorTypeDropdown
            id={'sensorTypeDropdown'}
            onSelect={this.dropdownOnSelect} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Sin interval
          </span>
          <NewDeviceTextInput
            value={device.sinInterval}
            onChange={this.newDeviceKeypress} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Minimum Interval
          </span>
          <NewDeviceTextInput
            value={device.min}
            onChange={this.newDeviceKeypress} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Maximum Interval
          </span>
          <NewDeviceTextInput
            value={device.maxInterval}
            onChange={this.newDeviceKeypress} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Random Interval
          </span>
          <NewDeviceRandomIntervalDropdown
            id={'randomIntervalDropdown'}
            onSelect={this.dropdownOnSelect} />
        </div>

        <Button
          className={'saveButton'}
          bsStyle="primary"
          onClick={this.save}>
          Save
        </Button>
      </section>
    );
  }
}

NewDeviceForm.propTypes = {
  device: PropTypes.object,
  history: PropTypes.object,
  saveNewDevice: PropTypes.func.isRequired,
};

export default NewDeviceForm;

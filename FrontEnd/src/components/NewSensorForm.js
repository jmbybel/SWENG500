import React from 'react';
import PropTypes from 'prop-types';
import NewSensorTextInput from './NewSensorTextInput';
import NewSensorSensorTypeDropdown from './NewSensorSensorTypeDropdown';
import NewSensorRandomIntervalDropdown from './NewSensorRandomIntervalDropdown';
import {
  PageHeader,
  Button,
} from 'react-bootstrap';

class NewSensorForm extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      sensor: {
        name: '',
        initialValue: null,
        maxValue: null,
        minValue: null,
        duration: null,
        interval: null,
        type: '',
        sinInterval: null,
        minInterval: null,
        maxInterval: null,
        randomInterval: '',
      }
    };
    this.newSensorKeypress = this.newSensorKeypress.bind(this);
    this.dropdownOnSelect = this.dropdownOnSelect.bind(this);
    this.save = this.save.bind(this);
  }

  newSensorKeypress(field, value) {
    const {
      state: {
        sensor,
      },
    } = this;
    // sensor[field] = value;

    this.setState({
      sensor: Object.assign(
        {},
        sensor,
        { [field]: value }
      )
    });
  }

  dropdownOnSelect(eventKey, event) {
    console.dir(eventKey);
    console.dir(event);
  }

  save() {
    const {
      props: {
        saveNewSensor,
        onHistoryChanged,
        history,
      },
    } = this;

    saveNewSensor(this.state.sensor);
    history.push('/view-sensors');
    onHistoryChanged('/view-sensors');
  }

  render() {
    const {
      state: {
        sensor,
      },
    } = this;

    return (
      <section
        className={'newSensorForm'}>
        <PageHeader>
          New Sensor Form
        </PageHeader>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Name
          </span>
          <NewSensorTextInput
            name={'name'}
            value={sensor.name}
            onChange={this.newSensorKeypress} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Initial Value
          </span>
          <NewSensorTextInput
            name={'initialValue'}
            value={sensor.initialValue}
            onChange={this.newSensorKeypress} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Max Value
          </span>
          <NewSensorTextInput
            name={'maxValue'}
            value={sensor.maxValue}
            onChange={this.newSensorKeypress} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Min Value
          </span>
          <NewSensorTextInput
            name={'minValue'}
            value={sensor.minValue}
            onChange={this.newSensorKeypress} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Duration in Milliseconds
          </span>
          <NewSensorTextInput
            name={'duration'}
            value={sensor.duration}
            onChange={this.newSensorKeypress} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Interval in Milliseconds
          </span>
          <NewSensorTextInput
            name={'interval'}
            value={sensor.interval}
            onChange={this.newSensorKeypress} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Sensor Type
          </span>
          <NewSensorSensorTypeDropdown
            id={'sensorTypeDropdown'}
            onSelect={this.dropdownOnSelect} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Sin interval
          </span>
          <NewSensorTextInput
            name={'sinInterval'}
            value={sensor.sinInterval}
            onChange={this.newSensorKeypress} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Minimum Interval
          </span>
          <NewSensorTextInput
            name={'minInterval'}
            value={sensor.min}
            onChange={this.newSensorKeypress} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Maximum Interval
          </span>
          <NewSensorTextInput
            name={'maxInterval'}
            value={sensor.maxInterval}
            onChange={this.newSensorKeypress} />
        </div>
        <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            Random Interval
          </span>
          <NewSensorRandomIntervalDropdown
            id={'randomIntervalDropdown'}
            onSelect={this.dropdownOnSelect} />
        </div>
          <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            URL Endpoint
          </span>
          <NewSensorTextInput
            id={'urlEndpoint'}
            value={sensor.urlEndpoint}
            onChange={this.newSensorKeypress} />
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

NewSensorForm.propTypes = {
  sensor: PropTypes.object,
  history: PropTypes.object,
  saveNewSensor: PropTypes.func.isRequired,
};

export default NewSensorForm;

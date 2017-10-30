import React from 'react';
import PropTypes from 'prop-types';
import NewSensorTextInput from './NewSensorTextInput';
import NewSensorSensorTypeDropdown from './NewSensorSensorTypeDropdown';
import NewSensorRandomIntervalDropdown from './NewSensorRandomIntervalDropdown';
import {
  Button,
} from 'react-bootstrap';

class NewSensorForm extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.state = {
      sensor: {
        name: '',
        initialValue: '',
        maxValue: '',
        minValue: '',
        duration: '',
        interval: '',
        type: '',
        sinInterval: '',
        minInterval: '',
        maxInterval: '',
        randomInterval: '',
        urlEndpoint: '',
      },
      btnRandomTitle: 'Select ...',
      btnTypeTitle: 'Select ...',
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

      this.setState({
        sensor: Object.assign(
          {},
          sensor,
          { [field]: value }
        )
      });
    
  }

  dropdownOnSelect(eventKey) {
    const {
      state: {
        sensor,
      },
    } = this;
    if(eventKey === 'True' || eventKey === 'False')
    {
      this.setState({
        sensor: Object.assign(
          {},
          sensor,
          { ['randomInterval']: eventKey }
        ),
        btnRandomTitle: eventKey
      });
    }
    else 
    {
      this.setState({
        sensor: Object.assign(
          {},
          sensor,
          { ['type']: eventKey }
        ),
        btnTypeTitle: eventKey
      });
    }
  }

  save() {
    const {
      props: {
        saveNewSensor,
        onHistoryChanged,
        history,
      },
    } = this;

    if(this.state.sensor.name === '' )
    {
      alert('Please enter a valid Sensor Name');
    }
    else if (this.state.sensor.initialValue.match(/[a-z]/i) || this.state.sensor.initialValue === '')
    {
      alert('Please enter a valid number for initial value');
    } 
    else if (this.state.sensor.maxValue.match(/[a-z]/i) || this.state.sensor.maxValue === '')
    {
      alert('Please enter a valid number for max value');
    } 
    else if (this.state.sensor.minValue.match(/[a-z]/i) || this.state.sensor.minValue === '')
    {
      alert('Please enter a valid number for min value');
    } 
    else if (this.state.sensor.duration.match(/[a-z]/i) || this.state.sensor.duration === '')
    {
      alert('Please enter a valid number for duration');
    } 
    else if (this.state.sensor.interval.match(/[a-z]/i) || this.state.sensor.interval === '')
    {
      alert('Please enter a valid number for interval');
    } 
    else if (this.state.sensor.type === '')
    {
      alert('Please select a sensor type');
    } 
    else if (this.state.sensor.type === 'Sin' && (this.state.sensor.sinInterval.match(/[a-z]/i) || this.state.sensor.sinInterval === ''))
    {
      alert('Please enter a valid number for sin interval');
    } 
    else if (this.state.sensor.randomInterval === '')
    {
      alert('Please select if you want random interval on or off');
    } 
    else if (this.state.sensor.randomInterval === 'True' && (this.state.sensor.maxInterval.match(/[a-z]/i) || this.state.sensor.maxInterval === ''))
    {
      alert('Please enter a valid number for max random interval');
    } 
    else if (this.state.sensor.randomInterval === 'True' && (this.state.sensor.minInterval.match(/[a-z]/i) || this.state.sensor.minInterval === ''))
    {
      alert('Please enter a valid number for min random interval');
    } 
    else
    {
      saveNewSensor(this.state.sensor);
      history.push('/view-sensors');
      onHistoryChanged('/view-sensors');
    }
  }

  render() {
    const {
      state: {
        sensor,
      },
    } = this;
    const newSensorFormStyle = {
      maxWidth: '50%',
      padding: '20px',
    };

    return (
      <section
        className={'newSensorForm'}
        style={newSensorFormStyle}>
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
            value={this.state.btnTypeTitle}
            onSelect={this.dropdownOnSelect}  />
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
            value={this.state.btnRandomTitle}
            onSelect={this.dropdownOnSelect} />
        </div>
          <div
          className={'input'}>
          <span
            className={'labelSpan'}>
            URL Endpoint
          </span>
          <NewSensorTextInput
            name={'urlEndpoint'}
            value={sensor.urlEndpoint}
            placeholder={'18.216.43.18:8081/contentListener'}
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

import React from 'react';
import PropTypes from 'prop-types';
import NewDeviceTextInput from './NewDeviceTextInput';
import { 
  PageHeader, 
  Button,
  Label,
} from 'react-bootstrap';

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
    const {
      props: {
        saveNewDevice,
        history,
      },
    } = this;

    saveNewDevice(this.state.device);
    history.push('/view-devices');
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
            New Device Name
          </span>
          <NewDeviceTextInput
            value={device.name}
            onChange={this.newDeviceKeypress} />
        </div>
        <Button
          className={'saveButton'}
          bsStyle="primary"
          onClick={this.save}>
          Save
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

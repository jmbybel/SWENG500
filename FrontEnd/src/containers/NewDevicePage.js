import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as deviceActions from '../actions/deviceActions';
import NewDeviceForm from '../components/NewDeviceForm';

export const NewDevicePage = (props) => {
  return (
    <NewDeviceForm
      devices={props.devices}
      saveNewDevice={props.actions.saveNewDevice}
      newDeviceName={props.newDeviceName} />
  );
};

NewDevicePage.propTypes = {
  devices: PropTypes.array,
  actions: PropTypes.object.isRequired,
  newDeviceName: PropTypes.string,
};

function mapStateToProps(state) {
  return {
    devices: state.devices,
    newDeviceName: state.newDeviceName
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(deviceActions, dispatch)
  };
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NewDevicePage);

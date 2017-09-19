import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as deviceActions from '../actions/deviceActions';
import NewDeviceForm from '../components/NewDeviceForm';

export const NewDevicePage = (props) => {
  return (
    <NewDeviceForm
      device={props.device}
      history={props.history}
      saveNewDevice={props.actions.saveNewDevice}
      onHistoryChanged={props.onHistoryChanged} />
  );
};

NewDevicePage.propTypes = {
  device: PropTypes.object,
  history: PropTypes.object,
  actions: PropTypes.object.isRequired,
};

function mapStateToProps(state) {
  return {
    device: state.device,
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

import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as deviceActions from '../actions/deviceActions';
import DeviceList from '../components/DeviceList';

export const DevicesPage = (props) => {
  return (
    <DeviceList
      devices={props.devices.devices} />
  );
};

DevicesPage.propTypes = {
  devices: PropTypes.object,
  actions: PropTypes.object.isRequired,
};

function mapStateToProps(state) {
  return {
    devices: state.devices,
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
)(DevicesPage);

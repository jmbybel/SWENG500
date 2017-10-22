import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as sensorActions from '../actions/sensorActions';
import SensorList from '../components/SensorList';

export const SensorsPage = (props) => {
  return (
    <SensorList
      sensors={props.sensors.sensors} />
  );
};

SensorsPage.propTypes = {
  sensors: PropTypes.object,
  actions: PropTypes.object.isRequired,
};

function mapStateToProps(state) {
  return {
    sensors: state.sensors,
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(sensorActions, dispatch)
  };
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SensorsPage);

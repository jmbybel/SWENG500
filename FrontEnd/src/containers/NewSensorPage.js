import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import * as sensorActions from '../actions/sensorActions';
import NewSensorForm from '../components/NewSensorForm';

export const NewSensorPage = (props) => {
  return (
    <NewSensorForm
      sensor={props.sensor}
      history={props.history}
      saveNewSensor={props.actions.saveNewSensor}
      onHistoryChanged={props.onHistoryChanged} />
  );
};

NewSensorPage.propTypes = {
  sensor: PropTypes.object,
  history: PropTypes.object,
  actions: PropTypes.object.isRequired,
};

function mapStateToProps(state) {
  return {
    sensor: state.sensor,
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
)(NewSensorPage);

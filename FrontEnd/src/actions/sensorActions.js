import * as types from '../constants/ActionTypes';
import sensorApi from '../api/sensorApi';

export function loadSensorsSuccess(sensors) {
  return {type: types.LOAD_DEVICES_SUCCESS, sensors};
}

export function saveNewSensorSuccess(sensor) {
  return {type: types.SAVE_NEW_DEVICE, sensor};
}

export function loadSensors() {
  // make async call to api, handle promise, dispatch action when promise is resolved
  return function(dispatch) {
    return sensorApi.getAllSensors().then(sensors => {
      dispatch(loadSensorsSuccess(sensors));
    }).catch(error => {
      throw(error);
    });
  };
}

export function saveNewSensor(newSensor) {
  return function (dispatch) {
    return sensorApi.createSensor(newSensor).then(responseSensor => {
      dispatch(saveNewSensorSuccess(responseSensor));
      return responseSensor;
    }).catch(error => {
      throw(error);
    });
  };
}

import * as types from '../constants/ActionTypes';
import sensorApi from '../api/sensorApi';

export function getNumberOfRunningSensorsSuccess(numRunningSensors) {
	return {type: types.GET_NUMBER_OF_RUNNING_SENSORS, numRunningSensors};
}

export function loadSensorsSuccess(sensors) {
  return {type: types.LOAD_DEVICES_SUCCESS, sensors};
}

export function getSensorSuccess(sensor) {
	return {type: types.GET_SENSOR, sensor};
}

export function startSensorSuccess(sensorId) {
  return {type: types.START_SENSOR, sensorId};
}

export function pauseSensorSuccess(sensorId) {
  return {type: types.PAUSE_SENSOR, sensorId};
}

export function saveNewSensorSuccess(sensor) {
  return {type: types.SAVE_NEW_DEVICE, sensor};
}

export function updateSensorSuccess(sensor) {
  return {type: types.UPDATE_SENSOR, sensor};
}

export function getNumberOfRunningSensors() {
  return function (dispatch) {
    return sensorApi.getNumberOfRunningSensors().then(responseSensorCount => {
      dispatch(getNumberOfRunningSensorsSuccess(responseSensorCount));
      return responseSensorCount;
    }).catch(error => {
      throw(error);
    });
  };
}

export function loadSensors() {
  return function(dispatch) {
    return sensorApi.getAllSensors().then(sensors => {
      dispatch(loadSensorsSuccess(sensors));
    }).catch(error => {
      throw(error);
    });
  };
}

export function getSensor(sensorId) {
  return function (dispatch) {
    return sensorApi.getSensor(sensorId).then(responseSensor => {
      dispatch(getSensorSuccess(responseSensor));
      return responseSensor;
    }).catch(error => {
      throw(error);
    });
  };
}

export function startSensor(sensorId) {
  return function (dispatch) {
    return sensorApi.startSensor(sensorId).then(responseSensorId => {
      dispatch(startSensorSuccess(responseSensorId));
      return responseSensorId;
    }).catch(error => {
      throw(error);
    });
  };
}

export function pauseSensor(sensorId) {
  return function (dispatch) {
    return sensorApi.pauseSensor(sensorId).then(responseSensorId => {
      dispatch(pauseSensorSuccess(responseSensorId));
      return responseSensorId;
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

export function updateSensor(sensor) {
  return function (dispatch) {
    return sensorApi.updateSensor(sensor).then(responseSensor => {
      dispatch(updateSensorSuccess(responseSensor));
      return responseSensor;
    }).catch(error => {
      throw(error);
    });
  };
}

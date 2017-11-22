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

export function deleteSensorSuccess(sensorId) {
  return {type: types.DELETE_SENSOR, sensorId};
}

export function getDestinationIPSuccess(sensorIP) {
  return {type: types.GET_DESTINATION_IP, sensorIP};
}

export function setDestinationIPSuccess(sensorIP) {
  return {type: types.SET_DESTINATION_IP, sensorIP};
}

export function getDestinationIP() {
  return function(dispatch) {
    return sensorApi.getDestinationIP().then(destinationIP => {
      dispatch(getDestinationIPSuccess(destinationIP));
    }).catch(error => {
      throw(error);
    });
  };
}

export function setDestinationIP(destinationIP) {
  return function(dispatch) {
    return sensorApi.setDestinationIP(destinationIP).then(destinationIP => {
      dispatch(setDestinationIPSuccess(destinationIP));
    }).catch(error => {
      throw(error);
    });
  };
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

export function startSensor(sensor) {
  return function (dispatch) {
    return sensorApi.startSensor(sensor).then(responseSensor => {
      dispatch(startSensorSuccess(responseSensor));
      return responseSensor;
    }).catch(error => {
      throw(error);
    });
  };
}

export function pauseSensor(responseSensor) {
  return function (dispatch) {
    return sensorApi.pauseSensor(responseSensor).then(responseSensor => {
      dispatch(pauseSensorSuccess(responseSensor));
      return responseSensor;
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

export function deleteSensor(sensorId) {
  return function (dispatch) {
    return sensorApi.deleteSensor(sensorId).then(responseSensorId => {
      dispatch(deleteSensorSuccess(responseSensorId));
      return responseSensorId;
    }).catch(error => {
      throw(error);
    });
  };
}

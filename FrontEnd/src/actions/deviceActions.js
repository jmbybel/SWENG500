import * as types from '../constants/actionTypes';
import deviceApi from '../api/deviceApi';

export function loadDevicesSuccess(devices) {
  return {type: types.LOAD_DEVICES_SUCCESS, devices};
}

export function saveNewDeviceSuccess(newDevice) {
  return {type: types.SAVE_NEW_DEVICE, newDevice};
}

export function loadDevices() {
  // make async call to api, handle promise, dispatch action when promise is resolved
  return function(dispatch) {
    return deviceApi.getAllDevices().then(devices => {
      dispatch(loadDevicesSuccess(devices));
    }).catch(error => {
      throw(error);
    });
  };
}

export function saveNewDevice(newDevice) {
  return function (dispatch) {
    return deviceApi.createDevice(newDevice).then(responseDevice => {
      dispatch(saveNewDeviceSuccess(responseDevice));
      return responseDevice;
    }).catch(error => {
      throw(error);
    });
  };
}

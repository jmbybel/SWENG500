import * as types from '../constants/ActionTypes';
import deviceApi from '../api/deviceApi';

export function loadDevicesSuccess(devices) {
  return {type: types.LOAD_DEVICES_SUCCESS, devices};
}

export function saveNewDeviceSuccess(device) {
  return {type: types.SAVE_NEW_DEVICE, device};
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

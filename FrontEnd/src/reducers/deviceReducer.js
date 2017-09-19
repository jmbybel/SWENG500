import { combineReducers } from 'redux'
import { LOAD_DEVICES_SUCCESS, SAVE_NEW_DEVICE } from '../constants/ActionTypes'
import initialState from './initialState';

const devices = (state = initialState.devices, action) => {
  switch (action.type) {
    case SAVE_NEW_DEVICE:
      return [
        ...state.filter(device => device.device.name !== action.device.device.name),
        Object.assign({}, action.device)
      ];
    case LOAD_DEVICES_SUCCESS:
      return Object.assign([], state, action.devices);
    default:
      return state;
  }
}

const byName = (state = {}, action) => {
  switch (action.type) {
    case LOAD_DEVICES_SUCCESS: {
      return {
        ...state,
        ...action.devices.reduce((obj, device) => {
          obj[device.name] = device;
          return obj;
        }, {})
      };
    }
    default: {
      const { name } = action;
      if (name) {
        return {
          ...state,
          [name]: devices(state[name], action)
        };
      }
      return state;
    }
  }
};

export default combineReducers({
  byName,
  devices
});

export const getDevice = (state, name) =>
  state.byName[name];

export const getAllDevices = (state) =>
  state.devices.map(name => getDevice(state, name));

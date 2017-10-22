import { combineReducers } from 'redux'
import { LOAD_DEVICES_SUCCESS, SAVE_NEW_DEVICE } from '../constants/ActionTypes'
import initialState from './initialState';

const sensors = (state = initialState.sensors, action) => {
  switch (action.type) {
    case SAVE_NEW_DEVICE:
      return [
        ...state.filter(sensor => sensor.sensor.name !== action.sensor.sensor.name),
        Object.assign({}, action.sensor)
      ];
    case LOAD_DEVICES_SUCCESS:
      return Object.assign([], state, action.sensors);
    default:
      return state;
  }
}

const byName = (state = {}, action) => {
  switch (action.type) {
    case LOAD_DEVICES_SUCCESS: {
      return {
        ...state,
        ...action.sensors.reduce((obj, sensor) => {
          obj[sensor.name] = sensor;
          return obj;
        }, {})
      };
    }
    default: {
      const { name } = action;
      if (name) {
        return {
          ...state,
          [name]: sensors(state[name], action)
        };
      }
      return state;
    }
  }
};

export default combineReducers({
  byName,
  sensors
});

export const getSensor = (state, name) =>
  state.byName[name];

export const getAllSensors = (state) =>
  state.sensors.map(name => getSensor(state, name));

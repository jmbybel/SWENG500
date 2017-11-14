import { combineReducers } from 'redux';
import {
  LOAD_DEVICES_SUCCESS,
  SAVE_NEW_DEVICE,
  GET_NUMBER_OF_RUNNING_SENSORS,
  START_SENSOR,
  DELETE_SENSOR,
} from '../constants/ActionTypes';
import initialState from './initialState';

const sensors = (state = initialState.sensors, action) => {
  switch (action.type) {
    case SAVE_NEW_DEVICE:
      return [
        ...state.filter(sensor => sensor.id !== action.sensor.id),
        Object.assign({}, action.sensor)
      ];
    case LOAD_DEVICES_SUCCESS: {
      const sensorList = JSON.parse(action.sensors);
      return Object.assign([], state, sensorList);
    }
    case DELETE_SENSOR: {
      return state.filter(e => e ._id !== action.sensorId._id);
    }
    default:
      return state;
  }
};

const byId = (state = {}, action) => {
  switch (action.type) {
    case LOAD_DEVICES_SUCCESS: {
      const sensorList = JSON.parse(action.sensors);
      return {
        ...state,
        ...sensorList.reduce((obj, sensor) => {
          obj[sensor] = sensor;
          return obj;
        }, {})
      };
    }
    default: {
      const { id } = action;
      if (id) {
        return {
          ...state,
          [id]: sensors(state[id], action)
        };
      }
      return state;
    }
  }
};

const numRunningSensors = (state = initialState.numRunningSensors, action) => {
  switch (action.type) {
    case GET_NUMBER_OF_RUNNING_SENSORS: {
      const runningSensorCount = JSON.parse(action.numRunningSensors);
      return {
        ...state,
        ...runningSensorCount,
      };
    }
    default: {
      return state;
    }
  }
};

const startSensor = (state = {}, action) => {
  switch (action.type) {
    case START_SENSOR: {
      const { id } = action;
      if (id) {
        return {
          ...state,
          [id]: sensors(state[id], action)
        };
      }
      return state;
    }
    default: {
      return state;
    }
  }
};

export default combineReducers({
  sensors,
  byId,
  numRunningSensors,
  startSensor,
});

export const getSensor = (state, id) =>
  state.byId[id];

export const getAllSensors = (state) =>
  state.sensors.map(id => getSensor(state, id));

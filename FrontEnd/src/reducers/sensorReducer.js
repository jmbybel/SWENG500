import { combineReducers } from 'redux';
import {
  LOAD_DEVICES_SUCCESS,
  SAVE_NEW_DEVICE,
  GET_NUMBER_OF_RUNNING_SENSORS,
  START_SENSOR,
  PAUSE_SENSOR,
  DELETE_SENSOR,
  GET_DESTINATION_IP,
  SET_DESTINATION_IP,
} from '../constants/ActionTypes';
import initialState from './initialState';

const sensors = (state = initialState.sensors, action) => {
  switch (action.type) {
    case SAVE_NEW_DEVICE:
      return [
        ...state.filter(sensor => sensor._id !== action.sensor._id),
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
      const { _id } = action;
      if (_id) {
        return {
          ...state,
          [_id]: sensors(state[_id], action)
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
      const { _id } = action;
      if (_id) {
        return {
          ...state,
          [_id]: sensors(state[_id], action)
        };
      }
      return state;
    }
    default: {
      return state;
    }
  }
};

const stopSensor = (state = {}, action) => {
  switch (action.type) {
    case PAUSE_SENSOR: {
      const { _id } = action;
      if (_id) {
        return {
          ...state,
          [_id]: sensors(state[_id], action)
        };
      }
      return state;
    }
    default: {
      return state;
    }
  }
};

const getDestinationIP = (state = {}, action) => {
  switch (action.type) {
    case GET_DESTINATION_IP: {
      const { sensorIP } = action;
      if (sensorIP) {
        return {
          ...state,
          ...sensorIP,
        };
      }
      return state;
    }
    default: {
      return state;
    }
  }
};

const setDestinationIP = (state = {}, action) => {
  switch (action.type) {
    case SET_DESTINATION_IP: {
      const { sensorIP } = action;
      if (sensorIP) {
        return {
          ...state,
          ...sensorIP,
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
  stopSensor,
  getDestinationIP,
  setDestinationIP,
});

export const getSensor = (state, _id) =>
  state.byId[_id];

export const getAllSensors = (state) =>
  state.sensors.map(_id => getSensor(state, _id));

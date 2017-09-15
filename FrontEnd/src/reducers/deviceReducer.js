import * as types from '../constants/actionTypes';
import initialState from './initialState';

export default function deviceReducer(state = initialState.devices, action) {
  switch(action.type) {
    case types.LOAD_DEVICES_SUCCESS:
        return Object.assign([], state, action.cats);
    case types.SAVE_NEW_DEVICE:
      return [
        ...state.filter(device => device !== action.device),
        Object.assign({}, action.device)
      ];
    default: 
      return state;
  }
}

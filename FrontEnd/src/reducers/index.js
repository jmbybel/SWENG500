import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';
import sensors from './sensorReducer';

const rootReducer = combineReducers({
    sensors,
    routing: routerReducer
});

export default rootReducer;

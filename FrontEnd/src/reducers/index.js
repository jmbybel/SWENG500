import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';
import devices from './deviceReducer';

const rootReducer = combineReducers({
    devices,
    routing: routerReducer
});

export default rootReducer;

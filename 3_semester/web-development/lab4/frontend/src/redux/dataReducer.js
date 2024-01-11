// src/redux/reducers/dataReducer.js

import initialState from './initialState';
import { SET_X_VALUE, SET_Y_VALUE, SET_R_VALUE, SET_SESSIONID, SET_NEED_TO_BE_UPDATED, SET_RESULT } from './dataActions';

const dataReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_X_VALUE:
            return {
                ...state,
                x: action.payload,
            };
        case SET_Y_VALUE:
            return {
                ...state,
                y: action.payload,
            };
        case SET_R_VALUE:
            return {
                ...state,
                r: action.payload,
            };
        case SET_SESSIONID:
            return {
                ...state,
                sessionID: action.payload,
            };
        case SET_NEED_TO_BE_UPDATED:
            return {
                ...state,
                needToBeUpdated: action.payload,
            };
        case SET_RESULT:
            return {
                ...state,
                result: action.payload,
            };
        default:
            return state;
    }
};

export default dataReducer;

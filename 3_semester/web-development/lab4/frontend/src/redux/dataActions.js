// src/redux/actions/dataActions.js

// Action types
export const SET_X_VALUE = 'SET_X_VALUE';
export const SET_Y_VALUE = 'SET_Y_VALUE';
export const SET_R_VALUE = 'SET_R_VALUE';
export const SET_RESULT = 'SET_RESULT';

export const SET_SESSIONID = 'SET_SESSIONID';

// Action creators
export const setXValue = (x) => ({
    type: SET_X_VALUE,
    payload: x,
});

export const setYValue = (y) => ({
    type: SET_Y_VALUE,
    payload: y,
});

export const setRValue = (r) => ({
    type: SET_R_VALUE,
    payload: r,
});


export const setSessionID = (sessionID) => ({
    type: SET_SESSIONID,
    payload: sessionID,
});

export const SET_NEED_TO_BE_UPDATED = 'SET_NEED_TO_BE_UPDATED';

export const setNeedToBeUpdated = (value) => ({
    type: SET_NEED_TO_BE_UPDATED,
    payload: value,
});

export const setResult = (value) => ({
    type: SET_RESULT,
    payload: value,
});
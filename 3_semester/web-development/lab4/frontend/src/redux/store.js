// src/redux/store.js

import { createStore, combineReducers } from 'redux';
import dataReducer from './dataReducer';

const rootReducer = combineReducers({
    data: dataReducer,
    // Add other reducers if needed
});

const store = createStore(rootReducer);

export default store;

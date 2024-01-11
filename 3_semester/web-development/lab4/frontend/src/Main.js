import {useNavigate} from 'react-router-dom';
import {useEffect, useRef, useState} from "react";
import TableRow from "./TableRow";
import {Graph} from "./Graph"

import { useDispatch } from 'react-redux';
import {setXValue, setYValue, setRValue, setSessionID, setNeedToBeUpdated, setResult} from './redux/dataActions';
import { useSelector } from 'react-redux';

const Main = () => {
    const sessionID = localStorage.getItem("sessionID")
    let navigate = useNavigate();
    const dispatch = useDispatch();
    const ntbu = useSelector((state) => state.data.needToBeUpdated);
    const [results, setResults] = useState([])

    // const [x, setX] = useState("1")
    const x = useSelector((state) => state.data.x);
    const handleXChange = (e) => {
        // setX(e.target.value)
        dispatch(setXValue(e.target.value));
    }

    const handleRChange = (e) => {
        // setR(e.target.value)
        dispatch(setRValue(e.target.value));
    }

    const handleYPlus = () => {
        if (y < 3) {
            dispatch(setYValue(parseInt(y+1, 10)));
        }
    }
    const handleYMinus = () => {
        if (y > -5) {
            dispatch(setYValue(parseInt(y-1, 10)));
        }
    }

    const handleCheck = () => {

        fetch('/lab4/app/check-hit', {
            method: 'POST',
            headers: {
                'Authorization': sessionID,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                x: x,
                y: y,
                r: r,
            })
        })
            .then(response => response.json())
            .then(data => {
                dispatch(setResult(data.result));
                dispatch(setNeedToBeUpdated(true));
            })
            .catch(error => {
                console.log(error)
                alert("Unknown error")
            });
    }

    // const [y, setY] = useState(0)
    const y = useSelector((state) => state.data.y);
    // const [r, setR] = useState("1")
    const r = useSelector((state) => state.data.r);
    const loadResults = () => {
        fetch('/lab4/app/check-hit', {
            headers: {
                'Authorization': sessionID
            }
        })
            .then(response => response.json())
            .then(data => {
                setResults(data)

            })
            .catch(error => {
                console.log(error)
            });
    }

    useEffect(() => {
        loadResults()
    }, [ntbu, dispatch]);

    useEffect(() => {

        if (sessionID === null) {
            navigate('/')
        }
        loadResults()
    }, []);

    const graphRef = useRef();

    return (
        <div className="wrapper">
            <div className="window">
                <div className="title-bar">
                    <button aria-label="Close" className="close"></button>
                    <h1 className="title">Assignment #4</h1>
                    <button aria-label="Resize" className="resize"></button>
                </div>
                <div className="separator"></div>
                <div className="details-bar">
                    <span>{localStorage.getItem("username")}</span>
                    <span>
                        <button className="btn" onClick={()=>{
                            localStorage.clear();
                            navigate('/')
                        }}>Logout</button>
                    </span>
                </div>
                <div className="window-pane">
                    <div className="graph">
                        <Graph r={r}/>
                    </div>

                    <div className="input-row">
                        <label htmlFor="">X:</label>
                        <div className="field-row">
                            <input id="x-3" name="x" type="radio" value="-3" checked={(String(parseInt(x, 10)) === "-3")} onChange={handleXChange}/>
                            <label htmlFor="x-3">-3</label>
                            <input id="x-2" name="x" type="radio" value="-2" checked={(String(parseInt(x, 10)) === "-2")} onChange={handleXChange}/>
                            <label htmlFor="x-2">-2</label>
                            <input id="x-1" name="x" type="radio" value="-1" checked={(String(parseInt(x, 10)) === "-1")} onChange={handleXChange}/>
                            <label htmlFor="x-1">-1</label>
                            <input id="x0" name="x" type="radio" value="0" checked={(String(parseInt(x, 10)) === "0")} onChange={handleXChange}/>
                            <label htmlFor="x0">0</label>
                            <input id="x1" name="x" type="radio" value="1" checked={(String(parseInt(x, 10)) === "1")} onChange={handleXChange}/>
                            <label htmlFor="x1">1</label>
                            <input id="x2" name="x" type="radio" value="2" checked={(String(parseInt(x, 10)) === "2")} onChange={handleXChange}/>
                            <label htmlFor="x2">2</label>
                            <input id="x3" name="x" type="radio" value="3" checked={(String(parseInt(x, 10)) === "3")} onChange={handleXChange}/>
                            <label htmlFor="x3">3</label>
                            <input id="x4" name="x" type="radio" value="4" checked={(String(parseInt(x, 10)) === "4")} onChange={handleXChange}/>
                            <label htmlFor="x4">4</label>
                            <input id="x5" name="x" type="radio" value="5" checked={(String(parseInt(x, 10)) === "5")} onChange={handleXChange}/>
                            <label htmlFor="x5">5</label>
                        </div>
                    </div>

                    <div className="input-row">
                        <label htmlFor="">R:</label>
                        <div className="field-row">
                            <input id="r1" name="r" type="radio" value="1" checked={(r === "1")} onChange={handleRChange}/>
                            <label htmlFor="r1">1</label>
                            <input id="r2" name="r" type="radio" value="2" checked={(r === "2")} onChange={handleRChange}/>
                            <label htmlFor="r2">2</label>
                            <input id="r3" name="r" type="radio" value="3" checked={(r === "3")} onChange={handleRChange}/>
                            <label htmlFor="r3">3</label>
                            <input id="r4" name="r" type="radio" value="4" checked={(r === "4")} onChange={handleRChange}/>
                            <label htmlFor="r4">4</label>
                            <input id="r5" name="r" type="radio" value="5" checked={(r === "5")} onChange={handleRChange}/>
                            <label htmlFor="r5">5</label>
                        </div>
                    </div>

                    <div className="input-row">
                        <label htmlFor="">Y:</label>
                        <div>
                            <div className="y-slider btn">
                                <div className="y-slider-minus" onClick={handleYMinus}>-</div>
                                <div className="y-slider-value">{parseInt(y, 10)}</div>
                                <div className="y-slider-plus" onClick={handleYPlus}>+</div>
                            </div>

                            <button className="btn"  onClick={handleCheck}>Check</button>
                        </div>

                    </div>
                </div>
                    <div className="table">
                        <TableRow header/>
                        {results.slice(0).reverse().map((r)=>{
                            return <TableRow  x={r.request.x} y={r.request.y} r={r.request.r} result={r.result} exAt={r.executedAt} exTime={r.executionTime}/>
                        })}
                    </div>



            </div>

        </div>
    )
}

export  default Main
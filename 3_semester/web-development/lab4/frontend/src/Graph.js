import React, {forwardRef, useEffect, useRef} from "react";
import Coordinates from "./Coordinates";
import { useDispatch } from 'react-redux';
import {setXValue, setYValue, setRValue, setSessionID, setNeedToBeUpdated, setResult} from './redux/dataActions';
import { useSelector } from 'react-redux';

const Graph = (props) => {
    const canvasRef = useRef(null);
    const dispatch = useDispatch()

    const ntbu = useSelector((state) => state.data.needToBeUpdated);
    const r = useSelector((state) => state.data.r);
    const x = useSelector((state) => state.data.x);
    const y = useSelector((state) => state.data.y);
    const result = useSelector((state) => state.data.result);
    const sessionID = localStorage.getItem("sessionID")

    useEffect(()=>{
        const canvas = canvasRef.current;
        const ctx = canvas.getContext('2d');

        const centerX = canvas.clientWidth/2
        const centerY = canvas.clientHeight/2
        const coordinates = new Coordinates()
        coordinates.setGraphR(r)

        ctx.beginPath()
        ctx.clearRect(0, 0,  canvas.clientWidth,  canvas.clientHeight);

        console.log(coordinates.graphR, coordinates.canvasR)
        ctx.fillStyle = "lightgray"
        ctx.fillRect(centerX, centerY, -coordinates.canvasR, -coordinates.canvasR)

        ctx.beginPath();
        ctx.arc(centerX, centerY, coordinates.canvasR, 0 * Math.PI, 0.5 * Math.PI);
        ctx.lineTo(centerX, centerY); // Connect the arc to the center to close the shape
        ctx.closePath();
        ctx.fill();

        ctx.beginPath();
        ctx.lineTo(centerX, centerY);
        ctx.lineTo(centerX, centerY - coordinates.canvasR);
        ctx.lineTo(centerX + coordinates.canvasR/2, centerY );
        ctx.fill()

        drawAxes()
    }, [r, dispatch])
    const drawAxes = () => {
        const canvas = canvasRef.current;
        const ctx = canvas.getContext('2d');
        ctx.beginPath();
        ctx.strokeStyle = 'black';

        ctx.moveTo(0, canvas.clientHeight / 2);
        ctx.lineTo(canvas.clientWidth, canvas.clientHeight / 2);

        ctx.moveTo(canvas.clientWidth / 2, 0);
        ctx.lineTo(canvas.clientWidth / 2, canvas.clientHeight);

        ctx.stroke();



    };

    const handleCanvasClick = (event) => {
        const canvas = canvasRef.current;
        const ctx = canvas.getContext('2d');
        const coordinates = new Coordinates()
        coordinates.setCanvasX(event.clientX - canvas.getBoundingClientRect().left)
        coordinates.setCanvasY(event.clientY - canvas.getBoundingClientRect().top)

        req(coordinates.graphX, coordinates.graphY, (result)=>{
            drawDot(coordinates.canvasX, coordinates.canvasY, result)
            dispatch(setXValue(coordinates.graphX))
            dispatch(setYValue(coordinates.graphY))
            dispatch(setResult(result))
            dispatch(setNeedToBeUpdated(!ntbu));
            // dispatch(setNeedToBeUpdated(!ntbu));

        })

    };

    const drawDot = (x,y, status=false) => {
        const canvas = canvasRef.current;
        const ctx = canvas.getContext('2d');
        ctx.beginPath();
        ctx.arc(x, y, 4, 0, 2 * Math.PI);
        ctx.strokeStyle = 'black';
        ctx.fillStyle = (status) ? 'black' : 'white'
        ctx.lineWidth = 1;
        ctx.fill();
        ctx.stroke()
        ctx.closePath();
    }

    useEffect(() => {
        if (ntbu) {
            console.log("@@@")
            const coordinates = new Coordinates()
            coordinates.setGraphX(x)
            coordinates.setGraphY(y)
            drawDot(coordinates.canvasX, coordinates.canvasY, result)
            dispatch(setNeedToBeUpdated(!ntbu));
        }
    }, [ntbu, dispatch]);

    useEffect(() => {
        const canvas = canvasRef.current;
        const ctx = canvas.getContext('2d');

        drawAxes();
    }, []);

    const req = (x, y, callback) => {
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
                callback(data.result)

            })
            .catch(error => {
                alert("Unknown error")
            });
    }

    return (
    <canvas ref={canvasRef} width="300" height="300" onClick={handleCanvasClick}></canvas>
    )
}

export {Graph}
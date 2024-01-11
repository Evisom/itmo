import {createBrowserRouter} from "react-router-dom";

const TableRow = (props) => {
    const [hh, mm, ss] = new Date(props.exAt).toLocaleTimeString().split(':');
    if (props.header) {
        return <div className="table-row">
            <div className="table-cell">X</div>
            <div className="table-cell">Y</div>
            <div className="table-cell">R</div>
            <div className="table-cell">result</div>
            <div className="table-cell">exec. at</div>
            <div className="table-cell">exec. time</div>
        </div>
    } else {
        return <div className="table-row">
            <div className="table-cell">{parseFloat(props.x).toFixed(2)}</div>
            <div className="table-cell">{parseFloat(props.y).toFixed(2)}</div>
            <div className="table-cell">{props.r}</div>
            <div className="table-cell">{ (props.result) ? "✓" : "𐄂"}</div>
            <div className="table-cell">{hh}:{mm}:{ss.slice(0, 3)}</div>
            <div className="table-cell">{props.exTime}</div>
        </div>
}}

export default TableRow
class Coordinates {

    canvasWidth = 300
    canvasHeight = 300
    k = 25

    canvasX;
    canvasY;
    graphX;
    graphY;
    graphR;
    canvasR;
    setCanvasX(x) {
        this.canvasX = x
        this.graphX = (x - this.canvasWidth/2)/this.k
    }

    setCanvasY(y) {
        this.canvasY = y
        this.graphY = -(y - this.canvasHeight/2)/this.k
    }

    setGraphX(x) {
        this.graphX = x
        this.canvasX = x * this.k + this.canvasWidth/2
    }

    setGraphY(y) {
        this.graphY = y
        this.canvasY = -y * this.k + this.canvasHeight/2
    }

    setCanvasR(r) {
        this.canvasR = r
        this.graphR = r/this.k
    }

    setGraphR(r) {
        this.graphR = r
        this.canvasR = r*this.k
    }
}

export default Coordinates
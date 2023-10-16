package model;

import java.math.BigDecimal;
import java.time.Instant;
import java.lang.*;
import java.time.Duration;
import java.time.LocalTime;
public class Result {
    float x;
    float y;
    float r;
    String time;

    public int execTime;
    boolean inArea;

    public Result(String x, String y, String r, long timestamp) {
        this.x = Float.parseFloat(x);
        this.y = Float.parseFloat(y);
        this.r = Float.parseFloat(r);
        this.time = java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.inArea = graph1() || graph2() || graph3() || graph4();
        this.execTime = (int) (System.nanoTime() - timestamp);
    }

    private boolean graph1 () {
        if (this.x >= 0 & this.y >= 0) {
            return Math.pow(this.x, 2) < (Math.pow(this.r, 2) - Math.pow(this.y, 2));
        } else {
            return false;
        }
    }

    private boolean graph2 () {
        if (this.x <= 0 & this.y >= 0) {
            return (Math.abs(this.x) <= this.r) & (this.y <= this.r);
        } else {
            return false;
        }
    }

    private boolean graph3 () {
        if (this.x <= 0 & this.y <= 0) {
            return (this.y <= (-0.5 * this.r - this.x)) & (x > (-0.5 * this.r - this.y));
        } else {
            return false;
        }
    }

    private boolean graph4 () {
        return false;
    }

    public String[] arrayRepresentation() {
        return new String[]{String.valueOf(this.time), String.valueOf(this.execTime), String.valueOf(this.x), String.valueOf(this.y), String.valueOf(this.r), String.valueOf(this.inArea)};
    }
}

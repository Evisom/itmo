package app.model.request;

import java.util.Objects;


// Data Transfer Object
// Объект запроса check-hit
public class CheckHitRequestDTO {
    private double x;
    private double y;
    private double r;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

}

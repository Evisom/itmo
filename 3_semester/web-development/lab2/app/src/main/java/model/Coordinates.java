package model;

public class Coordinates {
    private float x;
    private float y;
    private float r;
    private boolean valid;

    public Coordinates(String x, String y, String r) {
        try {
            this.x = Integer.parseInt(x);
            this.x = Integer.parseInt(y);
            this.x = Integer.parseInt(r);
            this.valid = true;
        } catch (Exception e) {
            this.valid = false;
        }
    }

    public boolean isValid() {
        return this.valid;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getR() {
        return this.r;
    }
}
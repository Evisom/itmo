package app.util;


// тут проверяем попадание точки в область
public class AreaResultChecker {
    public static boolean getResult(double x, double y, double r) {

        if (x >= 0 && y >= 0) {
            if (y <= -2*x + r)
                return true;
        }

        if (x <= 0 && y >= 0) {
            if (y <= r && x >= -r)
                return true;
        }

        if (x >= 0 && y <= 0) {
            if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2))
                return true;
        }
        return false;
    }
}

package beans;

public class AreaResultChecker {
    public static boolean getResult(double x, double y, double r) {
        // check 1st square -- 1/4 circle
        if (x >= 0 && y >= 0) {
            return false;
//            if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2))
//                return true;
        }
        // check 2nd square -- square
        if (x <= 0 && y >= 0) {
            if (-2*x <= r && y <= r) {
                return true;
            }
        }
        // check 3rd square -- triangle
        if (x <= 0 && y <= 0) {
            if (-2* x - y <= r)
                return true;
        }

        if (x >= 0 && y <= 0) {
            if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2))
                return true;
        }
        // check 4th square -- always false
        // also if all checks above fails...
        return false;
    }
}

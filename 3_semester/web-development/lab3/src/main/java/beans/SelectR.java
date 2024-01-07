package beans;

import java.io.Serializable;

public enum SelectR implements Serializable {
    P1(1.0),
    P2(2.0),
    P3(3.0),
    P4(4.0),
    P5(5.0),
    UNSELECTED(1.0);
    private final Double value;
    SelectR(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
}

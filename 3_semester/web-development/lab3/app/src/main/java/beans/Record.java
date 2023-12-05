package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name="Record", eager = true)
@RequestScoped
public class Record implements Serializable {
    @ManagedProperty(value = "#{x}")
    private String x = "hello world";
    private float y = 2.5F;
    private float r = 3F;
    private String uuid = "some-uuid";
    private int timestamp = 24324;

    public void Print() {
        System.out.println(this.x);
        System.out.println(this.y);
        System.out.println(this.r);
        System.out.println(this.timestamp);
    }
}

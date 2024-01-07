package beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Named
@Entity
@Table(name = "results", schema = "s369074")
@SessionScoped
public class CheckAreaBean implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "x")
    private double x;
    @Column(name = "y")
    private double y;
    @Column(name = "r")
    private double r;
    @Column(name = "result")
    private boolean result;
    @Column(name = "executed_at")
    private LocalDateTime executedAt;
    @Column(name = "exec_time")
    private long execTime;

    public CheckAreaBean() {
        super();
    }

    @Column(name = "x")
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Column(name = "y")
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Column(name = "r")
    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Column(name = "result")
    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Column(name = "executed_at")
    public LocalDateTime getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(LocalDateTime executedAt) {
        this.executedAt = executedAt;
    }

    @Column(name = "exec_time")
    public long getExecTime() {
        return execTime;
    }

    public void setExecTime(long execTime) {
        this.execTime = execTime;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}

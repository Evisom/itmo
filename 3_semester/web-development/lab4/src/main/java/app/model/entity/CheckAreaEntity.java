package app.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


// Сущность проверки попадания
@Entity
@Table(name="result_lab4", schema = "s369074")
public class CheckAreaEntity implements Serializable {
    private long id;
    private UserEntity ownerID;
    private double x;
    private double y;
    private double r;
    private boolean result;
    private LocalDateTime executedAt;
    private long executionTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id")
    public UserEntity getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(UserEntity ownerID) {
        this.ownerID = ownerID;
    }

    @Column
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Column
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Column
    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Column
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

    @Column(name = "execution_time")
    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

}

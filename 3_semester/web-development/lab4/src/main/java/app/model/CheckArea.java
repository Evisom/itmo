package app.model;

import app.model.entity.CheckAreaEntity;
import app.model.request.CheckHitRequestDTO;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;

public class CheckArea implements Serializable {
    private CheckHitRequestDTO request;
    private boolean result;
    private Instant executedAt;
    private long executionTime;

    public CheckArea() {
        super();
    }

    public CheckHitRequestDTO getRequest() {
        return request;
    }

    public void setRequest(CheckHitRequestDTO request) {
        this.request = request;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Instant getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(Instant executedAt) {
        this.executedAt = executedAt;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }


    // метод позволяющий получить CheckArea из Entity
    public static CheckArea fromEntity(CheckAreaEntity entity) {
        final CheckArea result = new CheckArea();
        final CheckHitRequestDTO requestDTO = new CheckHitRequestDTO();
        requestDTO.setX(entity.getX());
        requestDTO.setY(entity.getY());
        requestDTO.setR(entity.getR());
        result.setRequest(requestDTO);
        result.setResult(entity.isResult());
        result.setExecutedAt(entity.getExecutedAt().atZone(ZoneId.systemDefault()).toInstant());
        result.setExecutionTime(entity.getExecutionTime());
        return result;
    }
}

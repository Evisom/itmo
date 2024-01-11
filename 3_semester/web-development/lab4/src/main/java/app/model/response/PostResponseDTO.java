package app.model.response;

import java.util.Objects;


// Data Transfer Object
// объект ответа на POST запрос
public class PostResponseDTO {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
